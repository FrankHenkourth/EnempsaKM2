package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Animal;
import fte102.jpgame.celdas.Cadaver;
import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.CeldaContigua;
import fte102.jpgame.celdas.CeldaVacia;
import fte102.jpgame.celdas.Especie;
import fte102.jpgame.celdas.Huevo;
import fte102.jpgame.comportamientos.Comportamiento;
import java.util.ArrayList;

public class IA extends Comportamiento {

   public IA() {
      super("ia");
   }

   public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie) {
      if (especie instanceof Animal) {

         Animal animal = (Animal) especie;

         if (animal.isAlimento_cazado()) {
            ArrayList<Coordenada> muertes = animal.getMuertes();
            for (int x = 0; x < muertes.size(); x++) {
               Celda celda = CCeld.getCelda(muertes.get(x));
               if (!(celda instanceof Cadaver)) {
                  animal.remMuerte(x);
               }
            }
         }

         float ataque = animal.getPeso() * 2;
         float defensa = animal.getPeso() / 2;
         int edad_max = (int) (animal.getPesoMax() - animal.getPesoMin() + 5);
         float comer = animal.getPeso() / 5 + 1;

         if (ataque != animal.getFuerzaDeAtaque()) {
            animal.setFuerzaDeAtaque(ataque);
         }
         if (defensa != animal.getFuerzaDeDefensa()) {
            animal.setFuerzaDeDefensa(defensa);
         }
         if(comer != animal.getComer()) {
            animal.setComer(comer);
         }
         if (edad_max != animal.getEdadMax()) {
            animal.setEdadMax(edad_max);
         }

         Coordenada[] coords_vison = CCeld.verZonaDesde(animal.getCoordenada(), animal.getVision(), "vacio", true);
         Coordenada[] coords_animal_vision = CCeld.verZonaDesde(animal.getCoordenada(), animal.getVision(), "animal", true);

         Coordenada[] coords_comer = CCeld.getZonaCondicionada(animal.getCoordenada(), animal.getVision(), animal.getPrioridadesComer(), animal.getCondicionesComer());
         Coordenada[] coords_ataque = CCeld.getZonaCondicionada(animal.getCoordenada(), animal.getVision(), animal.getPrioridadesAtaque(), animal.getCondicionesAtaque());

       
         System.out.println(coords_comer.length);
         //System.out.println(coords_ataque.length);

         // Dependiendo el animal va la prioridad
         // Si no hay comida se mueve
         if (animal.estaApareado()) {
            if (animal.getSexo() == 'M' || animal.getProteger() == null) {
               animal.desaparear();
            } else {
               Celda contigua = CCeld.getCelda(animal.getProteger());

               if (contigua instanceof Huevo || contigua instanceof Animal) {
                  Coordenada[] rangoMovimiento = CCeld.verZonaDesde(animal.getProteger(), animal.getVision(), "vacio", true);
                  Coordenada[] rangoComerA = CCeld.verZonaDesde(animal.getProteger(), animal.getVision(), "planta", true);

                  if (animal.getFuerzaDeAtaque() > 0 && !animal.tienePanico()) {
                     Coordenada[] rangoAtaqueA = CCeld.verZonaDesde(animal.getProteger(), animal.getVision(), "animal", true);
                     if (rangoAtaqueA.length > 0) {
                        CComp.run("atacar", CCeld, especie, rangoAtaqueA[CCeld.numeroAleatorio(0, rangoAtaqueA.length - 1)]);
                     }
                  }

                  if (rangoMovimiento.length > 0) {
                     CComp.run("mover", CCeld, especie, rangoMovimiento[CCeld.numeroAleatorio(0, rangoMovimiento.length - 1)]);
                  }
               }

               if (contigua instanceof CeldaContigua) {
                  Huevo huevo = new Huevo(contigua.getX(), contigua.getY(), animal.getTipo(), animal.getNacerHuevo(), animal.getPesoInicialHuevo());
                  if (contigua.estaSeleccionada()) {
                     huevo.seleccionar();
                  }

                  CCeld.setCelda(huevo);
               } else if (contigua instanceof Huevo) {
                  // Es automático el Huevo es su propio comportamiento
               } else if (contigua instanceof Animal) {
                  Animal hijo = (Animal) contigua;

                  if (hijo.esAdulto()) {
                     animal.desaparear();
                     hijo.desaparear();
                  } else {
                     Coordenada[] moverContigua = CCeld.verZonaDesde(animal.getCoordenada(), 1, "vacio", true);
                     Coordenada[] comerContigua = CCeld.verZonaDesde(hijo.getCoordenada(), 1, hijo.getAlimentacion() == "hervivoro" ? "planta" : "cadaver", true);

                     if (moverContigua.length > 0) {
                        Coordenada mov = moverContigua[CCeld.numeroAleatorio(0, moverContigua.length - 1)];
                        CComp.run("mover", CCeld, hijo, mov);
                        animal.setProteger(mov);
                     } else if (comerContigua.length > 0) {
                        CComp.run("comer", CCeld, hijo, comerContigua[CCeld.numeroAleatorio(0, comerContigua.length - 1)]);
                     } else {
                        // ...
                     }
                  }
               } else {
                  // ..
               }

               if (contigua instanceof CeldaVacia || contigua instanceof Cadaver) {
                  animal.desaparear();
               }

            }
         }
         
         boolean accionAlguna = false;

         for (String p : animal.getPrioridadComportamientos()) {
               
            if (p.equals("aparearse") && !accionAlguna && !animal.estaApareado() && coords_animal_vision.length > 0) {
               for (Coordenada coordAnimal : coords_animal_vision) {
                  Animal animal_objetivo = (Animal) CCeld.getCelda(coordAnimal);
                  if (!animal_objetivo.estaApareado() && !animal.estaApareado()) {
                     if (animal_objetivo.getTipo() == animal.getTipo()) {
                        if (animal_objetivo.esAdulto() && animal.esAdulto()) {
                           if (animal_objetivo.getSexo() == 'M' && animal.getSexo() == 'F') {
                              Coordenada[] celdasVacias = CCeld.verZonaDesde(animal.getCoordenada(), 1, "vacio", true);
                              if (celdasVacias.length > 0) {
                                 animal_objetivo.aparear();
                                 Coordenada coord = celdasVacias[CCeld.numeroAleatorio(0, celdasVacias.length - 1)];
                                 CeldaContigua contigua = new CeldaContigua(coord.getX(), coord.getY());
                                 CCeld.setCelda(contigua);
                                 animal.aparear(contigua.getCoordenada());
                                 accionAlguna = true;
                              }
                           } else if (animal_objetivo.getSexo() == 'F' && animal.getSexo() == 'M') {
                              Coordenada[] celdasVacias = CCeld.verZonaDesde(animal_objetivo.getCoordenada(), 1, "vacio", true);
                              if (celdasVacias.length > 0) {
                                 animal.aparear();
                                 Coordenada coord = celdasVacias[CCeld.numeroAleatorio(0, celdasVacias.length - 1)];
                                 CeldaContigua contigua = new CeldaContigua(coord.getX(), coord.getY());
                                 CCeld.setCelda(contigua);
                                 animal_objetivo.aparear(contigua.getCoordenada());
                                 accionAlguna = true;
                              }
                           }
                        }
                     }
                  }
               }
            }
            
            if(accionAlguna) break;

            
            if (!accionAlguna && !animal.estaApareado() && animal.esAdulto()) {
               if (p.equals("atacar") && coords_ataque.length > 0) {
                  CComp.run("atacar", CCeld, especie, coords_ataque[CCeld.numeroAleatorio(0, coords_ataque.length - 1)]);
                  accionAlguna = true;
                  break;
               }


               if (p.equals("comer") && coords_comer.length > 0 && animal.getPeso() < animal.getPesoMax()) {
                  CComp.run("comer", CCeld, especie, coords_comer[CCeld.numeroAleatorio(0, coords_comer.length - 1)]);
                  accionAlguna = true;
                  break;
               }
            }

         }

         if (!accionAlguna) {
            CComp.run("mover", CCeld, especie, coords_vison[CCeld.numeroAleatorio(0, coords_vison.length - 1)]);
         }

      }

   }
}
