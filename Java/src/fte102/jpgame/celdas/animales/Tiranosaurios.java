package fte102.jpgame.celdas.animales;

import fte102.jpgame.celdas.Animal;


public class Tiranosaurios extends Animal {
 public Tiranosaurios(int x, int y, char sexo) {
   super(x, y, "tiranosaurios", sexo);
   
   alimentacion = "carnivoro";
   pesoInicialHuevo = 30;
   peso = 50;
   pesoMin = 20;
   pesoMax = 50;
   edadMax = 35;
   edadAdulta = 14;
   vision = 6;
   alcance = 3;
   color = "#bbbb33";
   
   pelearPorAparearse = true;
   
   prioridadComportamientos = new String[] {
      "aparearce", "comer", "ataque"
   };
   

   prioridadesAtaque = new String[] {
     "animal", "huevo"
   };
   
   condicionesAtaque = new String[] {
     "no_pterodactilos", "huevo_si_no_es_madre", "animal_peligro_extincion", "otra_especie"
   };
   
   prioridadesComer = new String[] {
     "cadaver", "restos_huevo"
   };
   
 }
}
