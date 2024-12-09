package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.Coordenada;
import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.comportamientos.Enfermedad;
import fte102.jpgame.celdas.Animal;

public abstract class Enfermedad extends Comportamiento implements Cloneable {

   protected int alcance;
   protected int alcanceMax;
   protected int propagacion;
   protected boolean propagando;
   protected Coordenada origen;
   protected int duracion;
   protected int ciclosAfeccion;
   protected int ciclosPasados;
   protected int probabilidadSurguir;
   protected int probabilidadInfeccion;
   protected String color;

   public Enfermedad(String id) {
      super(id);
      propagacion = 1; // Cada 1 Ciclo se propaga
      ciclosPasados = 0;
      duracion = 100;
      probabilidadSurguir = 1;
      probabilidadInfeccion = 10;
      propagando = false;
      origen = null;
      color = "#234567";
   }

   // Al Contraer la enfermedad
   public void ejecucionInicial(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
   }

   // Al pasar un ciclo
   public void ejecucionDurante(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
   }

   // Al sanar la enfermedad, antes de eliminarse del organismo
   public void ejecucionFinal(ControlComportamientos CComp, ControlCeldas CCeld, Animal animal) {
   }

   public void pasarCiclo() {
      if (ciclosPasados < duracion) {
         ciclosPasados++;
      }
      if (alcance < alcanceMax && ciclosPasados % propagacion == 0) {
         alcance++;
      }
   }

   public boolean finalizada() {
      return (ciclosPasados >= duracion);
   }

   public void setAlcance(int alcance, int alcanceMax) {
      if (alcance >= alcanceMax) {
         this.alcance = alcanceMax;
         this.alcanceMax = alcanceMax;
         propagando = false;
      } else {
         this.alcance = alcance;
         this.alcanceMax = alcanceMax;
         propagando = true;
      }
   }

   public void setAlcance(int alcance) {
      if (alcance >= alcanceMax) {
         this.alcance = alcanceMax;
         propagando = false;
      } else {
         this.alcance = alcance;
         propagando = true;
      }
   }

   public boolean estaPropagandose() {
      return propagando;
   }

   public int getAlcance() {
      return alcance;
   }

   public int getAlcanceMax() {
      return alcanceMax;
   }

   public void setOrigen(Coordenada origen) {
      this.origen = origen;
   }

   public Coordenada getOrigen() {
      return origen;
   }

   public int getProbabilidadSurguir() {
      return probabilidadSurguir;
   }

   public int getProbabilidadInfeccion() {
      return probabilidadInfeccion;
   }

   public int getCiclosPasados() {
      return ciclosPasados;
   }

   public int getDuracion() {
      return duracion;
   }

   public String getColor() {
      return color;
   }
}
