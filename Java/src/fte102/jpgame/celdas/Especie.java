package fte102.jpgame.celdas;

import fte102.jpgame.celdas.Celda;

public abstract class Especie extends Celda {

   protected char sexo;
   protected String tipo;
   protected float peso;
   protected int edad;
   protected int edadMax;
   protected float pesoMin;
   protected float pesoMax;
   protected float aumentoPeso;
   protected float decadenciaPeso;
   protected String color;
   protected String[] comportamientos;
   protected int maxComportamientos;

   protected float pesoInicial;

   public Especie(int x, int y, String id) {
      super(x, y, id);
      tipo = id;
      maxComportamientos = 12;
      comportamientos = new String[this.maxComportamientos];
      peso = 0;
      pesoInicial = 0;

      edad = 0;
      edadMax = 0;
      pesoMin = 0;
      pesoMax = 0;
      aumentoPeso = 0;
      decadenciaPeso = 0;
      color = "#555555";
   }

   public int buscarComportamiento(String compID) {
      for (int id = 0; id < comportamientos.length; id++) {
         if (comportamientos[id] == compID) {
            return id;
         }
      }
      return -1;
   }

   public boolean existeComportamiento(String compID) {
      return buscarComportamiento(compID) == -1 ? false : true;
   }

   public int buscarIDVacio() {
      for (int id = 0; id < comportamientos.length; id++) {
         if (comportamientos[id] == "") {
            return id;
         }
      }
      return -1;
   }

   public void agregarComportamiento(String compID) {
      int id = buscarComportamiento(compID);
      if (id == -1) {
         int idVacio = buscarIDVacio();
         if (idVacio >= 0) {
            comportamientos[idVacio] = compID;
         }
      }
   }

   public void eliminarComportamiento(String compID) {
      int id = buscarComportamiento(compID);
      if (id != -1) {
         comportamientos[id] = "";
      }
   }

   public String[] getComportamientos() {
      return comportamientos;
   }

   public void addEdad(int edad) {
      this.edad += edad;
   }

   public float getPeso() {
      return peso;
   }

   public int getEdad() {
      return edad;
   }

   public int getEdadMax() {
      return edadMax;
   }

   public float getPesoMin() {
      return pesoMin;
   }

   public float getPesoMax() {
      return pesoMax;
   }

   public float getAumentoPeso() {
      return aumentoPeso;
   }

   public float getDecadenciaPeso() {
      return decadenciaPeso;
   }

   public void setPeso(float peso) {
      this.peso = peso;
   }

   public void setMinPeso(float pesoMin) {
      this.pesoMin = pesoMin;
   }

   public void setMaxPeso(float pesoMax) {
      this.pesoMax = pesoMax;
   }

   public void setEdad(int edad) {
      this.edad = edad;
   }

   public void setEdaax(int edadMax) {
      this.edadMax = edadMax;
   }

   public void setAumentoPeso(float aumentoPeso) {
      this.aumentoPeso = aumentoPeso;
   }

   public void setDecadenciaPeso(float decadenciaPeso) {
      this.decadenciaPeso = decadenciaPeso;
   }

   public String getColor() {
      return color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public String getTipo() {
      return tipo;
   }

   public char getSexo() {
      return sexo;
   }

}
