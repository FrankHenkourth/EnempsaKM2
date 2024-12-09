package fte102.jpgame.comportamientos;

import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Planta;

public class Reforestacion extends Comportamiento {
  protected int plantaMax;
  protected int probabilidad;

  public Reforestacion() {
    super("reforestacion");
    plantaMax = 100;
    probabilidad = 100;
  }

  public void run(ControlComportamientos CComp, ControlCeldas CCeld) {
    if (CCeld.probabilidad(probabilidad) && CCeld.getCeldas("planta", "").length <= plantaMax) {
      Coordenada[] vacios = CCeld.getCeldas("vacio", "");

      if (vacios.length > 0) {
        Coordenada coord = vacios[CCeld.numeroAleatorio(0, vacios.length - 1)];
        Planta planta = new Planta(coord.getX(), coord.getY());
        CCeld.setCelda(planta);
      }
    }
  }
}
