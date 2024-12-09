package fte102.jpgame.celdas;

import fte102.jpgame.celdas.Especie;

public class Cadaver extends Especie {

  public Cadaver(int x, int y, float pesoPasado) {
    super(x, y, "cadaver");
    peso = pesoPasado;
    decadenciaPeso = 1;
    edadMax = 10;
    color = "#563635";

    comportamientos = new String[maxComportamientos];
    comportamientos[0] = "envejecer";
    comportamientos[1] = "hambre";
    comportamientos[2] = "morir";
  }
  
}
