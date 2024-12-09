package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Celda;
import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.celdas.CeldaVacia;
import fte102.jpgame.celdas.Especie;

public class Mover extends Comportamiento {

  public Mover() {
    super("mover");
  }

  @Override
  public void run(
      ControlComportamientos CComp, ControlCeldas CCeld, Especie especie, Coordenada coord) {
    if (CCeld.coordenadaValida(coord) && CCeld.celdaValida(especie)) {
      Celda objetivo = CCeld.getCelda(coord);

      if (objetivo instanceof CeldaVacia) {
        int x = especie.getX();
        int y = especie.getY();
        especie.setXY(coord.getX(), coord.getY());
        objetivo.setXY(x, y);
        if (objetivo.estaSeleccionada()) objetivo.deseleccionar();

        CCeld.setCelda(especie);
        CCeld.setCelda(objetivo);
      }
    }
  }
}
