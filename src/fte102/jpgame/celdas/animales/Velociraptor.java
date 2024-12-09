package fte102.jpgame.celdas.animales;

import fte102.jpgame.celdas.Animal;


public class Velociraptor extends Animal {
 public Velociraptor(int x, int y, char sexo) {
   super(x, y, "velociraptor", sexo);
   
   alimentacion = "carnivoro";
   pesoInicialHuevo = 30;
   peso = 20;
   pesoMin = 8;
   pesoMax = 20;
   edadMax = 15;
   edadAdulta = 5;
   vision = 4;
   alcance = 2;
   color = "#bbaa00";
   
   ataqueEnManada = true;
   alimento_cazado = true;
   
   prioridadComportamientos = new String[] {
      "atacar", "aparearse", "comer"
   };
   
   prioridadesAtaque = new String[] {
     "huevo", "animal"
   };
   
   condicionesAtaque = new String[] {
     "pterodactilos", "otra_especie", "tiranosaurios_caso_extinsion"
   };
   
   prioridadesComer = new String[] {
     "restos_huevo", "cadaver"
   };
   
 }
}
