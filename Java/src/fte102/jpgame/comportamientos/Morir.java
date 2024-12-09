package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.CeldaContigua;
import fte102.jpgame.celdas.Especie;
import fte102.jpgame.celdas.RestosHuevo;
import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.celdas.Animal;
import fte102.jpgame.celdas.Cadaver;
import fte102.jpgame.celdas.Huevo;

public class Morir extends Comportamiento {

  public Morir() {
    super("morir");
  }

  @Override
  public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie) {
    if (especie.getPeso() <= especie.getPesoMin() ||
        especie.getEdadMax() > 0 && especie.getEdad() >= especie.getEdadMax()) {
      if (especie instanceof Animal) {
        Animal animal = (Animal) especie;

        if (animal.estaApareado()) {
          Celda contigua = CCeld.getCelda(animal.getProteger());
          if (contigua instanceof CeldaContigua) {
            CCeld.eliminarCelda(contigua);
          }
        }

        Cadaver cadaver = new Cadaver(especie.getX(), especie.getY(), especie.getPeso());
        if (animal.estaSeleccionada()) cadaver.seleccionar();
        CCeld.setCelda(cadaver);
      } else if (especie instanceof Huevo) {
        RestosHuevo restos = new RestosHuevo(especie.getX(), especie.getY(), especie.getPeso());
        if (especie.estaSeleccionada()) restos.seleccionar();
        CCeld.setCelda(restos);
      } else CCeld.eliminarCelda(especie);
    } else {
    }
  }
}
