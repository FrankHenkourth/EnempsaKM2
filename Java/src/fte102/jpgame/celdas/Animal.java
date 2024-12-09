package fte102.jpgame.celdas;

import java.util.ArrayList;

import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Especie;
import fte102.jpgame.comportamientos.Enfermedad;


public abstract class Animal extends Especie {
  
  protected int alcance;
  protected int vision;
  protected float comer;
  protected float defensa;
  protected boolean apareado;
  protected Coordenada proteger;
  protected String[] prioridades;
  protected int maxPrioridades;
  protected int edadAdulta;
  
  protected float fuerzaDeAtaque;
  protected float fuerzaDeDefenza;
 
  protected boolean avisarPeligro;
  protected boolean defenderEnManada;
  protected boolean panico;
  protected boolean pelearPorAparearse;
  protected  boolean bajoAtaque;
  protected  boolean atacando;
  protected boolean ataqueEnManada;
  protected boolean priorizarAtaque;
  protected boolean alimento_cazado;
  protected String alimentacion;
  
  protected String[] prioridadesAtaque;
  protected String[] condicionesAtaque;
  protected String[] prioridadesComer;
  protected String[] condicionesComer;
  protected String[] prioridadComportamientos;
  
  protected ArrayList<Coordenada> muertes;
  
  protected float pesoDeExtincion;
  protected float pesoDePreservacion;
  
  protected ArrayList<Enfermedad> enfermedades;
  
  protected int nacerHuevo;
  protected float pesoInicialHuevo;
  
  public Animal(int x, int y, String tipo, char sexo) {
    super(x, y, "animal");
    this.tipo = tipo;
    this.sexo = sexo;
    
    decadenciaPeso = 1;
    
    /****************/  
    alimentacion = "hervivoro";
    
    condicionesComer = new String[0];
    condicionesAtaque = new String[0];
    prioridadesAtaque = new String[0];
    prioridadesComer = new String[0];
    prioridadComportamientos = new String[] {
       "aparearse", "comer", "atacar"
    };
    
    muertes = new ArrayList<>();
    avisarPeligro = false;
    defenderEnManada = false;
    ataqueEnManada = false;
    pelearPorAparearse = false;
    alimento_cazado = false;
    bajoAtaque = false;
    atacando = false;
    panico = false;
    nacerHuevo = 7;
    /*****************/
    
    edad = 2;
    edadMax = 200;
    edadAdulta = 30;
    apareado = false;
    maxPrioridades = 8;
    prioridades = new String[maxPrioridades];
    enfermedades = new ArrayList<>();
    vision = 1;
    alcance = 1;
    
    fuerzaDeAtaque = 1;
    fuerzaDeDefenza = 1;
    pesoInicialHuevo = 20;
    pesoDeExtincion = 10;
    pesoDePreservacion = 50;
    
    peso = 10;
    
    pesoMin = 10;
    pesoMax = 1000;
    defensa = 5;
    comer = 40;

    comportamientos = new String[maxComportamientos];
    comportamientos[0] = "ia";
    comportamientos[1] = "envejecer";
    comportamientos[2] = "hambre";
    comportamientos[3] = "morir";
    
  }

   public String[] getPrioridadComportamientos() {
      return prioridadComportamientos;
   }

   public boolean isAlimento_cazado() {
      return alimento_cazado;
   }
  
  public void addEnfermedad(Enfermedad enf) {
    if(!tieneEnfermedad(enf.getID())) enfermedades.add(enf);
  }
  
  public ArrayList<Enfermedad> getEnfermedades() {
    return enfermedades;
  }
  
  public boolean tieneEnfermedad(String enfID) {
    boolean laTiene = false;
    for(int x = 0; x < enfermedades.size(); x++) {
      if(enfermedades.get(x).getID().equals(enfID)) {
        laTiene = true;
        break;
      }
    }
    return laTiene;
  }
  
  public void addMuerte(Coordenada coord) {
     muertes.add(coord);
  }
  
  public ArrayList<Coordenada> getMuertes() {
     return muertes;
  }
  
  public void remMuerte(int id) {
     if(muertes.size() > id) muertes.remove(id);
  }
  
  public void aparear(Coordenada proteger) {
    apareado = true;
    this.proteger = proteger;
  }
  
  public void aparear() {
    apareado = true;
    this.proteger = null;
  }
  
  public void desaparear() {
    apareado = false;
    proteger = null;
  }
  
  public boolean estaApareado() {
    return apareado;
  }
  
  public Coordenada getProteger() {
    return proteger;
  }
  
  public void setProteger(Coordenada coord) {
    proteger = coord;
  }
  
  public String getPrioridad(int id) {
    if(id >= 0 && id < maxPrioridades) return prioridades[id];
    return "";
  }
  
  public void setEdadAdulta(int edadAdulta) {
    this.edadAdulta = edadAdulta;
  }
  
  public int getEdadAdulta() {
    return edadAdulta;
  }
  
  public boolean esAdulto() {
    return (edad >= edadAdulta);
  }

  public float getDefensa() {
    return defensa;
  }

  public float getComer() {
    return comer;
  }
  
  public float getFuerzaDeAtaque() {
    return fuerzaDeAtaque;
  }
  
  public float getFuerzaDeDefensa() {
    return fuerzaDeDefenza;
  }

  public int getVision() {
    return vision;
  }

  public int getAlcance() {
    return alcance;
  }
  
  public int getNacerHuevo() {
    return nacerHuevo;
  }
  
  public float getPesoInicialHuevo() {
    return pesoInicialHuevo;
  }
  
  public String getAlimentacion() {
    return alimentacion;
  }

  public void setDefensa(float defensa) {
    this.defensa = defensa;
  }

  public void setComer(float comer) {
    this.comer = comer;
  }

  public void setVision(int vision) {
    this.vision = vision;
  }

  public void setAlcance(int alcance) {
    this.alcance = alcance;
  }
  
  public void setFuerzaDeAtaque(float fuerzaDeAtaque) {
    this.fuerzaDeAtaque = fuerzaDeAtaque;
  }
  
  public void setEdadMax(int edadMax) {
      this.edadMax = edadMax;
  }
  
  public void setFuerzaDeDefensa(float fuerzaDeDefensa) {
    this.fuerzaDeDefenza = fuerzaDeDefenza;
  }
  
  public boolean tienePanico() {
     return panico;
  }
  
  public String[] getCondicionesComer() {
    return condicionesComer;
  }
  
  public String[] getPrioridadesComer() {
    return prioridadesComer;
  }
  
  public String[] getCondicionesAtaque() {
    return condicionesAtaque;
  }
  
  public String[] getPrioridadesAtaque() {
    return prioridadesAtaque;
  }
  
  public boolean defiendeEnManada() {
     return defenderEnManada;
  }
  
   public boolean atacaEnManada() {
     return ataqueEnManada;
  }
  
}
