package fte102.jpgame.comportamientos.enfermedades;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.comportamientos.Enfermedad;
import fte102.jpgame.celdas.Animal;

public class Dinofobia extends Enfermedad {

  private String colorTmp;

  public Dinofobia() {
    super("dinofobia");
    color = "#dcb8f1";
    duracion = 20;
    propagacion = 2;
    probabilidadSurguir = 0;
    probabilidadInfeccion = 100;
  }

  public void ejecucionInicial(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
    colorTmp = animal.getColor();
    animal.setColor("#dd8822");
  }

  public void ejecucionDurante(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
    // Code...
  }

  public void ejecucionFinal(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
    animal.setColor(colorTmp);
  }
}
