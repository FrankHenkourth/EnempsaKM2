package fte102.jpgame;

/* FTE102 - JPGAME*/
import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Animal;
import fte102.jpgame.celdas.Celda;

import fte102.jpgame.celdas.Especie;
import fte102.jpgame.celdas.Planta;
import fte102.jpgame.celdas.animales.*;
import fte102.jpgame.comportamientos.Enfermedad;
import fte102.jpgame.Archivo;
import fte102.jpgame.celdas.Cadaver;
import fte102.jpgame.celdas.CeldaContigua;
import fte102.jpgame.celdas.Huevo;
import fte102.jpgame.celdas.RestosHuevo;
import fte102.jpgame.comportamientos.enfermedades.Dinofobia;
import fte102.jpgame.comportamientos.enfermedades.Rabia;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Juego {

   private ControlCeldas CCeld;
   private ControlComportamientos CComp;
   private int anchoCelda, cantCeldas, ancho, alto;
   private JuegoCiclo juegoCiclo;
   private boolean detenido = false;
   private JTextArea infoCelda;
   private Coordenada seleccion;
   private boolean seleccionado;
   private String nombrePartida;
   private Archivo archivo;
   private ArrayList<Celda[][]> ciclos;
   private ArrayList<ArrayList<Enfermedad>> ciclosE;
   private int cicloActual;
   private JLabel infoCiclo;

   public Juego(int anchoCelda, int maxCeldas, JTextArea infoCelda, JLabel infoCiclo, String nombrePartida) {
      this.infoCelda = infoCelda;
      this.alto = anchoCelda * maxCeldas;
      this.ancho = alto;
      this.anchoCelda = anchoCelda;
      this.nombrePartida = nombrePartida;
      this.infoCiclo = infoCiclo;

      archivo = new Archivo(nombrePartida + ".jspg");

      cantCeldas = maxCeldas;
      seleccion = new Coordenada(0, 0);
      seleccionado = false;
      cicloActual = 0;

      CCeld = new ControlCeldas(cantCeldas);
      CComp = new ControlComportamientos();
      ciclos = new ArrayList<>();
      ciclosE = new ArrayList<>();
   }

   public void ir_a_ciclo(int c) {
      if (c >= 0 && c < ciclos.size()) {
         ciclo(c);
      }
   }

   public void pasar_ciclo() {
      ciclo(cicloActual + 1);
   }

   public void anterior_ciclo() {
      if (ciclos.size() > 0) {
         ir_a_ciclo(cicloActual - 1);
      }
   }

   public int getUltimoCiclo() {
      return (ciclos.size() == 0) ? 0 : ciclos.size() - 1;
   }

   public void cargaInicial() {
      Coordenada[] points
         = new Coordenada[]{
            new Coordenada(0, 0),
            new Coordenada(0, cantCeldas - 1),
            new Coordenada(cantCeldas - 1, 0),
            new Coordenada(cantCeldas - 1, cantCeldas - 1),
            new Coordenada((int) cantCeldas / 2, (int) cantCeldas / 2)
         };

      for (int x = 0; x < points.length; x++) {
         Coordenada[] zona_vision = CCeld.verZonaDesde(points[x], ((int) cantCeldas / 4), "vacio", true);

         if (zona_vision.length > 0) {
            int[] orden = CCeld.ordenAleatorio(zona_vision.length);
            int maxId = (int) orden.length / 100;

            if (maxId > 0) {
               for (int k = 0; k < maxId; k++) {
                  char sexo = (CCeld.numeroAleatorio(1, 3) == 2 ? 'F' : 'M');
                  Animal animal;

                  if (x == 0) {
                     animal = new Velociraptor(zona_vision[orden[k]].getX(), zona_vision[orden[k]].getY(), sexo);
                  } else if (x == 1) {
                     animal = new Braquiosaurios(zona_vision[orden[k]].getX(), zona_vision[orden[k]].getY(), sexo);
                  } else if (x == 2) {
                     animal = new Tiranosaurios(zona_vision[orden[k]].getX(), zona_vision[orden[k]].getY(), sexo);
                  } else if (x == 3) {
                     animal = new Triceratops(zona_vision[orden[k]].getX(), zona_vision[orden[k]].getY(), sexo);
                  } else if (x == 4) {
                     animal = new Pterodactilos(zona_vision[orden[k]].getX(), zona_vision[orden[k]].getY(), sexo);
                  } else {
                     continue;
                  }

                  if (x >= 0 && x <= 4) {
                     animal.setEdad(animal.getEdadAdulta());
                     CCeld.setCelda(animal);
                  }
               }

            }

            Planta planta = new Planta(CCeld.numeroAleatorio(0, cantCeldas - 1), CCeld.numeroAleatorio(0, cantCeldas - 1));
            Planta planta1 = new Planta(CCeld.numeroAleatorio(0, cantCeldas - 1), CCeld.numeroAleatorio(0, cantCeldas - 1));
            Planta planta2 = new Planta(CCeld.numeroAleatorio(0, cantCeldas - 1), CCeld.numeroAleatorio(0, cantCeldas - 1));
            Planta planta3 = new Planta(CCeld.numeroAleatorio(0, cantCeldas - 1), CCeld.numeroAleatorio(0, cantCeldas - 1));
            CCeld.setCelda(planta);
            CCeld.setCelda(planta1);
            CCeld.setCelda(planta2);
            CCeld.setCelda(planta3);
         }
      }

   }

   public void ciclo(int c) {
      try {
         if (c >= ciclos.size()) {
            Coordenada[] coords = CCeld.getCeldas("", "vacio");
            int[] orden = CCeld.ordenAleatorio(coords.length);

            int seresVivos = 0;

            for (int x : orden) {
               Celda celda = CCeld.getCelda(coords[x]);

               if (celda instanceof Especie) {

                  Especie especie = (Especie) celda;

                  for (String compID : especie.getComportamientos()) {
                     CComp.run(compID, CCeld, especie);
                     if (especie instanceof Animal) {
                        seresVivos++;
                     }
                  }

                  if (especie instanceof Animal) {
                     Animal animal = (Animal) especie;
                     ArrayList<Enfermedad> enfermedades = animal.getEnfermedades();

                     for (int i = 0; i < enfermedades.size(); i++) {
                        if (!enfermedades.get(i).finalizada()) {
                           enfermedades.get(i).ejecucionDurante(CComp, CCeld, animal);
                           enfermedades.get(i).pasarCiclo();
                        } else {
                           enfermedades.get(i).ejecucionFinal(CComp, CCeld, animal);
                           enfermedades.remove(i);
                        }
                     }
                  }
               }
            }

            boolean sel = false;

            for (Celda[] celdas : CCeld.getCeldas()) {
               for (Celda celda : celdas) {
                  if (celda.estaSeleccionada()) {
                     seleccion.setXY(celda.getX(), celda.getY());
                     sel = true;
                     break;
                  }
               }
               if (sel) {
                  break;
               }
            }

            CComp.generarFenomenos(CCeld);

            informacionSeleccion();

            if (seresVivos == 0) {
               detenido = true;
            }

            ciclos.add(clonarCeldas());
            ciclosE.add(clonarEnfermedades());
            cicloActual = ciclos.size() - 1;
         } else {
            CCeld.setCeldas(ciclos.get(c));
            CComp.setEnfermedades(ciclosE.get(c));
            cicloActual = c;
            informacionSeleccion();
         }

         infoCiclo.setText("" + cicloActual);
      } catch (Error e) {
         detenido = true;
         e.printStackTrace();
      }
   }

   public void addCelda(int id) {
      if (seleccionado) {
         switch (id) {
            case 0:
               CCeld.setCelda(new Planta(seleccion.getX(), seleccion.getY()));
               break;
            case 1:
               CCeld.setCelda(new Velociraptor(seleccion.getX(), seleccion.getY(), 'F'));
               break;
            case 2:
               CCeld.setCelda(new Braquiosaurios(seleccion.getX(), seleccion.getY(), 'M'));
               break;
            case 3:
               CCeld.setCelda(new Pterodactilos(seleccion.getX(), seleccion.getY(), 'M'));
               break;
            case 4:
               CCeld.setCelda(new Tiranosaurios(seleccion.getX(), seleccion.getY(), 'M'));
               break;
            case 5:
               CCeld.setCelda(new Triceratops(seleccion.getX(), seleccion.getY(), 'M'));
               break;
         }
      }
   }

   public Celda[][] clonarCeldas() {
      Celda[][] celdas = CCeld.getCeldas();
      Celda[][] celdasu = new Celda[celdas.length][celdas.length];
      for (int x = 0; x < celdas.length; x++) {
         for (int y = 0; y < celdas.length; y++) {
            celdasu[x][y] = celdas[x][y];
         }
      }

      return celdasu;
   }

   public ArrayList<Enfermedad> clonarEnfermedades() {
      ArrayList<Enfermedad> enfs = CComp.getEnfermedades();
      ArrayList<Enfermedad> enfsu = new ArrayList<>();
      for (int x = 0; x < enfs.size(); x++) {
         Enfermedad enf = enfs.get(x);
         if (enf instanceof Rabia) {
            Rabia rabia = new Rabia();
            rabia.setAlcance(enf.getAlcance(), enf.getAlcanceMax());
            rabia.setOrigen(enf.getOrigen());
            enfsu.add(rabia);
         }
         if (enf instanceof Dinofobia) {
            Dinofobia dinofobia = new Dinofobia();
            dinofobia.setAlcance(enf.getAlcance(), enf.getAlcanceMax());
            dinofobia.setOrigen(enf.getOrigen());
            enfsu.add(dinofobia);

         }
      }
      return enfsu;
   }

   public int getCicloActual() {
      return cicloActual;
   }

   public void informacionSeleccion() {
      if (seleccionado) {
         Celda celda = CCeld.getCelda(seleccion);
         String info = "";
         info += "Seleccionada: [" + celda.getX() + ", " + celda.getY() + "]\n";
         info += "Identificador: " + celda.getID() + "\n";

         if (celda instanceof Especie especie) {
            info += "Peso: " + especie.getPeso() + "\n";
            info += "PesoMax: " + especie.getPesoMax() + "\n";
            info += "PesoMin: " + especie.getPesoMin() + "\n";

            info += "Edad: " + especie.getEdad() + "\n";
            info += "EdadMax: " + especie.getEdadMax() + "\n";

            if (celda instanceof Animal animal) {
               info += "Vision: " + animal.getVision() + "\n";
               info += "Alcance: " + animal.getAlcance() + "\n";
               info += "Tipo: " + animal.getTipo() + "\n";
               info += "Sexo: " + animal.getSexo() + "\n";
               if (animal.getSexo() == 'F') {
                  info += "Apareada: " + animal.estaApareado() + "\n";
               }
               info += "Alimentacion: " + animal.getAlimentacion() + "\n";
               info += "Fuerza de Ataque: " + animal.getFuerzaDeAtaque() + "\n";
               info += "Fuerza de Defensa:  " + animal.getFuerzaDeDefensa() + "\n";
            }

         }

         infoCelda.setText(info);

      }
   }

   public void seleccionar(int x, int y) {
      if (CCeld.coordenadaValida(x, y)) {
         Celda cel = CCeld.getCelda(x, y);
         cel.seleccionar();
         seleccion.setXY(x, y);
         if (!seleccionado) {
            seleccionado = true;
         }
         informacionSeleccion();
      }
   }

   public void deseleccionarTodo() {
      seleccionado = false;
      CCeld.deseleccionarTodo();
   }

   public Celda getCelda(Coordenada coord) {
      return CCeld.getCelda(coord);
   }

   public Celda getCelda(int x, int y) {
      return CCeld.getCelda(x, y);
   }

   public Celda[][] getCeldas() {
      return CCeld.getCeldas();
   }

   public ArrayList<Enfermedad> getEnfermedades() {
      return CComp.getEnfermedades();
   }

   public Coordenada[] CCeldVer(Coordenada coord, int alcance) {
      return CCeld.verZonaCircularDesde(coord, alcance);
   }

   public int getAlto() {
      return alto;
   }

   public int getAncho() {
      return ancho;
   }

   public int getAnchoCelda() {
      return anchoCelda;
   }

   public int getCantCeldas() {
      return cantCeldas;
   }

   public boolean estaDetenido() {
      return detenido;
   }
}
