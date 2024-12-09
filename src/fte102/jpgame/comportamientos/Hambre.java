package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.ControlCeldas;
import fte102.jpgame.celdas.Especie;

public class Hambre extends Comportamiento {

  public Hambre() {
    super("hambre");
  }

  public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie) {

    if (especie.getDecadenciaPeso() > 0) {
      if (especie.getPeso() < 0) {
        especie.setPeso(0);
      } else {
        especie.setPeso(especie.getPeso()-especie.getDecadenciaPeso());
      }
    }
  }
}
