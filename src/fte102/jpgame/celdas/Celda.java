package fte102.jpgame.celdas;

import fte102.jpgame.Coordenada;


public abstract class Celda extends Coordenada {

  protected String id;
  protected boolean seleccionada;

  public Celda(int x, int y, String id) {
    super(x, y);
    this.id = id;
    seleccionada = false;
  }

  public String getID() {
    return id;
  }

  public boolean estaSeleccionada() {
    return seleccionada;
  }

  public void seleccionar() {
    seleccionada = true;
  }
  
  public void deseleccionar() {
    seleccionada = false;
  }
}
