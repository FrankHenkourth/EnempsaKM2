package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Animal;
import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.Especie;
import fte102.jpgame.comportamientos.Comportamiento;

public class Comer extends Comportamiento {

  public Comer() {
    super("comer");
  }

  public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie, Coordenada coord) {
    if (CCeld.coordenadaValida(coord)) {
      if (especie instanceof Animal) {
        Animal agresor = (Animal) especie;

        Celda celda_objetivo = CCeld.getCelda(coord);

        if (celda_objetivo instanceof Especie) {
          Especie objetivo = (Especie) celda_objetivo;
          
          float cant = agresor.getComer();
          
          if (cant > 0) {
            agresor.setPeso(agresor.getPeso()+cant);
            objetivo.setPeso(objetivo.getPeso()-cant);
          }
        }

      }
    }

  }

}