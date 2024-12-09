package fte102.jpgame.comportamientos.enfermedades;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.comportamientos.Enfermedad;
import fte102.jpgame.celdas.Animal;

public class Rabia extends Enfermedad {

  private String colorTmp;

  public Rabia() {
    super("rabia");
    color = "#b8f0f1";
    duracion = 12;
    propagacion = 1;
    probabilidadSurguir = 10;
    probabilidadInfeccion = 100;
  }

  public void ejecucionInicial(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
    colorTmp = animal.getColor();
    animal.setColor("#ff5522");
  }

  public void ejecucionDurante(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
    // Code...
  }

  public void ejecucionFinal(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
    animal.setColor(colorTmp);
    
  }
}
