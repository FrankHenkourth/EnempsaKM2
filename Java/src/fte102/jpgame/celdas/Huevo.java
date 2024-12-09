package fte102.jpgame.celdas;

import fte102.jpgame.celdas.Especie;

public class Huevo extends Especie {

  private String tipo;

  public Huevo(int x, int y, String tipo, int nacerEn, float pesoInicial) {
    super(x, y, "huevo");
    this.tipo = tipo;
    edadMax = nacerEn;
    peso = pesoInicial;
    pesoMin = 5;
    color = "#81F0E5";
    
    comportamientos = new String[maxComportamientos];
    comportamientos[0] = "envejecer";
    comportamientos[1] = "morir";
    comportamientos[2] = "nacer";
    
  }
  
  public String getTipo() {
    return tipo;
  }
  
}
