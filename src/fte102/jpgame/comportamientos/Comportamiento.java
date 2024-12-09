package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Especie;

public abstract class Comportamiento {

  protected String id;

  public Comportamiento(String id) {
    this.id = id;
  }

  public void run(ControlComportamientos BM, ControlCeldas CM) {
    // code..
  }

  public void run(ControlComportamientos BM, ControlCeldas CM, Especie especie) {
    // code..
  }

  public void run(ControlComportamientos BM, ControlCeldas CM, Especie especie, Coordenada coord) {
    // code..
  }

  public String getID() {
    return id;
  }
}
