package fte102.jpgame.celdas;

import fte102.jpgame.celdas.Especie;


public class Planta extends Especie {
  public Planta(int x, int y) {
    super(x, y, "planta");
    peso = pesoInicial;
    peso = 20;
    pesoMin = 5;
    pesoMax = 1000;
    aumentoPeso = 10;
    color = "#00bb00";
    
    comportamientos = new String[maxComportamientos];
    comportamientos[0] = "crecer";
    comportamientos[1] = "morir";
  }
  
}
