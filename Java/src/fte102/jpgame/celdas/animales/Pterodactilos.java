package fte102.jpgame.celdas.animales;

import fte102.jpgame.celdas.Animal;


public class Pterodactilos extends Animal {
 public Pterodactilos(int x, int y, char sexo) {
   super(x, y, "pterodactilos", sexo);
   
   alimentacion = "carnivoro";
   pesoInicialHuevo = 40;
   peso = 15;
   pesoMin = 3;
   pesoMax = 15;
   edadMax = 17;
   edadAdulta = 6;
   vision = 10;
   alcance = 5;
   color = "#bbbb88";
   panico = false;
   
   
   prioridadesComer = new String[] {
     "restos_huevo", "cadaver"
   };
   
   condicionesAtaque = new String[] {
       "no_pterodactilos", "no_velociraptor"
   };
   
 }
}
