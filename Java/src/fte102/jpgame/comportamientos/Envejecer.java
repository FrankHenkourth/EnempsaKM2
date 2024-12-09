package fte102.jpgame.comportamientos;

import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.ControlCeldas;
import fte102.jpgame.celdas.Especie;

public class Envejecer extends Comportamiento {

  public Envejecer() {
    super("envejecer");
  }

  public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie) {
    especie.addEdad(1);
  }
}
