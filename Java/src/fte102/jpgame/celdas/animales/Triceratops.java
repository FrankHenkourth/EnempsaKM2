package fte102.jpgame.celdas.animales;

import fte102.jpgame.celdas.Animal;


public class Triceratops extends Animal {
 public Triceratops(int x, int y, char sexo) {
   super(x, y, "triceratops", sexo);
   
   alimentacion = "hervivoro";
   
   prioridadesComer = new String[] {
     "planta"
   };
   
   panico = true;
   defenderEnManada = true;
   priorizarAtaque = false;
   
   pesoInicialHuevo = 30;
   peso = 30;
   pesoMin = 10;
   pesoMax = 30;
   edadMax = 25;
   edadAdulta = 10;
   vision = 6;
   alcance = 3;
   color = "#aabb00";
   
   prioridadComportamientos = new String[] {
       "aparearce", "comer"
   };
   
 }
}
