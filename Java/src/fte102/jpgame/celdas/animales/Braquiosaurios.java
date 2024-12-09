package fte102.jpgame.celdas.animales;

import fte102.jpgame.celdas.Animal;


public class Braquiosaurios extends Animal {
 public Braquiosaurios(int x, int y, char sexo) {
   super(x, y, "braquiosaurios", sexo);
   
   alimentacion = "hervivoro";
   
   prioridadesComer = new String[] {
     "planta"
   };
   
   panico = true;
   defenderEnManada = true;
   priorizarAtaque = false;
   fuerzaDeAtaque = 0;
   fuerzaDeDefenza = 25;
   pesoInicialHuevo = 30;
   peso = 50;
   pesoMin = 15;
   pesoMax = 80;
   edadMax = 30;
   edadAdulta = 12;
   vision = 2;
   alcance = 1;
   color = "#aaaa00";
   
   prioridadComportamientos = new String[] {
      "comer", "aparearse"
   };
   
 }
}
