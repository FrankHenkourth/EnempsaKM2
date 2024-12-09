package fte102.jpgame;

import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.*;
import fte102.jpgame.celdas.CeldaVacia;
import java.util.ArrayList;
import java.util.Random;

public class ControlCeldas {

  private Celda[][] celdas;
  private int maxCeldas;
  private int cantCeldas;

  public ControlCeldas(int maxCeldas) {
    this.maxCeldas = maxCeldas;
    cantCeldas = maxCeldas * maxCeldas;
    celdas = new Celda[maxCeldas][maxCeldas];
    fillEmpty();
  }
  
  public void setCeldas(Celda[][] celdasu) {
     celdas = celdasu;
  }

  public void fillEmpty() {
    for (int x = 0; x < maxCeldas; x++) {
      for (int y = 0; y < maxCeldas; y++) {
        celdas[x][y] = new CeldaVacia(x, y);
      }
    }
  }

  public Coordenada[] getCeldas(String incluir, String excluir) {
    Coordenada[] resultado;
    
    ArrayList<Coordenada> coordenadas = new ArrayList<>();

    for (int x = 0; x < maxCeldas; x++) {
      for (int y = 0; y < maxCeldas; y++) {
        if (celdas[x][y].getID().equals(incluir) && excluir.equals("")
            || !celdas[x][y].getID().equals(excluir) && incluir.equals("")) {
          coordenadas.add(celdas[x][y].getCoordenada());
        }
      }
    }

    resultado = new Coordenada[coordenadas.size()];
    coordenadas.toArray(resultado);
    
    return resultado;
  }
  
  public Coordenada[] verZonaCircularDesde(Coordenada coord, int radio) {
   Coordenada[] resultado;
   
   int x = coord.getX();
   int y = coord.getY();
        
   int r = radio * radio;
   int y1 = y - radio;
   int y2 = y + radio;
   int x1 = x - radio;
   int x2 = x + radio;

   ArrayList<Coordenada> coordenadas = new ArrayList<>();
   
   for (int i = x1; i <= x2; i++) {
    for (int j = y1; j <= y2; j++) {
      if (i >= 0 && i < maxCeldas && j >= 0 && j < maxCeldas) {
         int dx = i - x;
         int dy = j - y;
         if (dx * dx + dy * dy <= r) coordenadas.add(celdas[i][j].getCoordenada());
       }
      }
     }
     
    resultado = new Coordenada[coordenadas.size()];
    coordenadas.toArray(resultado);
    
    return resultado;
 }
  
  public Coordenada[] verZonaDesde(Coordenada coord, int visibilidad, String filtrado, boolean excludeCenter) {
    Coordenada[] resultado;
    int x1 = 0;
    int y1 = 0;
    int x = coord.getX();
    int y = coord.getY();
    
    ArrayList<Coordenada> coordenadas = new ArrayList<>();

    for (int i = -visibilidad; i <= visibilidad; i++) {
      for (int j = -visibilidad; j <= visibilidad; j++) {
        if (excludeCenter && i == 0 && j == 0) continue;

        x1 = x + i;
        y1 = y + j;

        if (x1 >= 0 && x1 < maxCeldas && y1 >= 0 && y1 < maxCeldas) {
          if (celdas[x1][y1].getID().equals(filtrado) || filtrado.equals("")) {
            coordenadas.add(new Coordenada(x1, y1));
          }
        }
      }
    }


    resultado = new Coordenada[coordenadas.size()];
    coordenadas.toArray(resultado);
    
    return resultado;
  }
  
  public Coordenada[] getZonaCondicionada(Coordenada coord, int visibilidad, String prioridades[], String condiciones[]) {
     
    Coordenada[] resultado = new Coordenada[0];
    int x1 = 0;
    int y1 = 0;
    int x = coord.getX();
    int y = coord.getY();
    boolean valido = true;
    
    ArrayList<Coordenada> coordenadas[] = (prioridades.length == 0) ? new ArrayList[1] : new ArrayList[prioridades.length];
    
    for(int i=0;i<coordenadas.length;i++) {
       coordenadas[i] = new ArrayList<>();
    }


    
    for (int i = -visibilidad; i <= visibilidad; i++) {
      for (int j = -visibilidad; j <= visibilidad; j++) {
        if (i == 0 && j == 0) continue;

        x1 = x + i;
        y1 = y + j;

        if (x1 >= 0 && x1 < maxCeldas && y1 >= 0 && y1 < maxCeldas) {

          for(int p = 0; p < prioridades.length; p++) {
             if(celdas[x1][y1].getID().equals(prioridades[p])) {
                valido = true;
                
                 if(celdas[x1][y1] instanceof Animal) {
                   Animal animal = (Animal) celdas[x1][y1];
                   Celda celdab = celdas[x1][y1];
                   
                  for(String condicion : condiciones) {
                     if(valido && condicion.equals("no_velociraptor")) {
                        if(!animal.getTipo().equals("velociraptor")) valido = false;
                     }
                     
                     if(valido && condicion.equals("huevo_si_no_es_madre")) {
                        if(animal.getSexo() == 'F' && animal.estaApareado() && animal.getProteger() != null) {
                           Celda celda = getCelda(animal.getProteger());
                           if(celda instanceof Huevo) valido = false;
                        } 
                     }
                     
                     if(valido && condicion.equals("animal_peligro_extincion")) {
                        if(animal.getSexo() == 'F' && animal.estaApareado() && animal.getProteger() != null) {
                           Celda celda = getCelda(animal.getProteger());
                           if(celda instanceof Huevo || celda instanceof Animal) valido = false;
                        } 
                     }
                     
                     if(valido && condicion.equals("otra_especie")) {
                        Celda celda = getCelda(coord);
                        if(celda instanceof Especie) {
                           Especie especie = (Especie) celda;
                           if(especie.getTipo().equals(animal.getTipo())) valido = false;
                        }
                        
                     }
                     
                     if(valido && condicion.equals("tiranosaurios_caso_extinsion")) {
                        if(celdab instanceof Especie) {
                           Especie especie = (Especie) celdab;
                           boolean enPeligoExtinsion = ((especie.getPesoMin() - especie.getPesoMax())/4 > especie.getPeso());
                           if(!enPeligoExtinsion) valido = false;
                        }
                     }
                     
                     if(celdab instanceof Animal) {
                           Animal animalb = (Animal) celdab;
                           if(animalb.isAlimento_cazado()) {
                              for(Coordenada c : animalb.getMuertes()) {
                                 if(c.getX() != x1 || c.getY() != y1) valido = false;
                              }
                           }
                     }
                     
                     //if(!valido) break;
                  }
                }
                
                if(valido) coordenadas[p].add(new Coordenada(x1, y1));
             }
           }
          if(prioridades.length == 0) coordenadas[0].add(new Coordenada(x1, y1));
        }
      }
    }
    
    for(int i=0;i<coordenadas.length;i++) {
       if(coordenadas[i].size() > 0) {
          resultado = new Coordenada[coordenadas[i].size()];
          coordenadas[i].toArray(resultado);
          break;
       }
    }
    
    return resultado;
  }
  

  public int[] ordenAleatorio(int maxId) {
    int[] b = new int[maxId];
    for (int i = 0; i < maxId; i++) b[i] = i;
    for (int i = 0; i < maxId; i++) {
      int r = numeroAleatorio(0, maxId - 1);
      int t = b[i];
      b[i] = b[r];
      b[r] = t;
    }
    return b;
  }

  public int numeroAleatorio(int v1, int v2) {
    int min = 0, max = 0;

    if (v1 >= 0 && v2 >= 0) {
      if (v1 == v2) return v1;
      if (v1 > v2) {
        min = v2;
        max = v1;
      } else {
        min = v1;
        max = v2;
      }
      return new Random().nextInt((max - min) + 1) + min;
    }
    return 0;
  }

  public boolean probabilidad(int por) {
    if (por < 1) return false;
    if (por > 100 || numeroAleatorio(1, 100) < por) return true;
    return false;
  }
  
  public Coordenada coordenadaAleatoria() {
     return new Coordenada(numeroAleatorio(0, this.maxCeldas), numeroAleatorio(0, this.maxCeldas));
  }

  public boolean celdaValida(Celda celda) {
    return (coordenadaValida(celda.getX(), celda.getY()));
  }

  public boolean coordenadaValida(int x, int y) {
    return (x >= 0 && x < maxCeldas && y >= 0 && y < maxCeldas);
  }

  public boolean coordenadaValida(Coordenada coord) {
    if (coord == null) return false;
    return (coord.getX() >= 0
        && coord.getX() < maxCeldas
        && coord.getY() >= 0
        && coord.getY() < maxCeldas);
  }

  public void eliminarCelda(int x, int y) {
    if (coordenadaValida(x, y)) {
      celdas[x][y] = new CeldaVacia(x, y);
    }
  }

  public void eliminarCelda(Celda celda) {
    if (celdaValida(celda)) {
      int x = celda.getX();
      int y = celda.getY();
      celdas[x][y] = new CeldaVacia(x, y);
    }
  }

  public Celda getCelda(int x, int y) {
    if (coordenadaValida(x, y)) {
      return celdas[x][y];
    }
    return null;
  }

  public Celda getCelda(Coordenada coord) {
    if (coordenadaValida(coord)) {
      return celdas[coord.getX()][coord.getY()];
    }
    return null;
  }

  public Celda[][] getCeldas() {
    return celdas;
  }

  public void deseleccionarTodo() {
    for (int x = 0; x < maxCeldas; x++) {
      for (int y = 0; y < maxCeldas; y++) {
        if (celdas[x][y].estaSeleccionada()) {
          celdas[x][y].deseleccionar();
        }
      }
    }
  }

  public void setCelda(Celda celda) {
    if (celdaValida(celda)) {
      this.celdas[celda.getX()][celda.getY()] = celda;
    }
  }
}
