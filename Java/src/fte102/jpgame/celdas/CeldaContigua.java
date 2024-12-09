package fte102.jpgame.celdas;

import fte102.jpgame.celdas.Especie;


public class CeldaContigua extends Especie {
 public CeldaContigua(int x, int y) {
   super(x, y, "contigua");
   peso = 100;
   edad = 0;
   edadMax = 3;
   color = "#283845";
   
   comportamientos = new String[maxComportamientos];
   comportamientos[0] = "envejecer";
   comportamientos[1] = "morir";
 }
}
