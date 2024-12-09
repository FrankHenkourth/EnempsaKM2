package fte102.jpgame.comportamientos;

import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.Cadaver;
import fte102.jpgame.Coordenada;
import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.celdas.Animal;


public class Atacar extends Comportamiento {
 public Atacar() {
   super("atacar");
 }
 public void run(ControlComportamientos CComp, ControlCeldas CCeld, Celda celda, Coordenada coord) {
  if (CCeld.coordenadaValida(coord) && celda instanceof Animal) {
   Celda celda2 = CCeld.getCelda(coord);
   if(celda2 instanceof Animal) {
     Animal atacante = (Animal) celda;
     Animal presa = (Animal) celda2;
     
     float fuerzaAtaque = atacante.getFuerzaDeAtaque();
     float fuerzaDefensa = presa.getFuerzaDeDefensa();
     
     if(presa.estaApareado() && presa.getSexo() == 'F') fuerzaDefensa *= 2;
     
     if(presa.defiendeEnManada()) {
        Coordenada[] coords = CCeld.verZonaDesde(presa.getCoordenada(), presa.getVision(), "animal", true);
        for(Coordenada co : coords) {
           Celda c = CCeld.getCelda(co);
           if(c instanceof Animal) {
              Animal a = (Animal) c;
              if(a.getTipo() == presa.getTipo()) {
                 fuerzaDefensa += presa.getFuerzaDeDefensa();
              }
           }
        }
     }
     
   if(atacante.atacaEnManada()) {
        Coordenada[] coords = CCeld.verZonaDesde(atacante.getCoordenada(), atacante.getVision(), "animal", true);
        for(Coordenada co : coords) {
           Celda c = CCeld.getCelda(co);
           if(c instanceof Animal) {
              Animal a = (Animal) c;
              if(a.getTipo() == atacante.getTipo()) {
                 fuerzaAtaque += atacante.getFuerzaDeAtaque();
              }
           }
        }
     }
     
     if(fuerzaAtaque > fuerzaDefensa) {
       Cadaver cadaver = new Cadaver(presa.getX(), presa.getY(), presa.getPeso());
       CCeld.setCelda(cadaver);
       if(atacante.isAlimento_cazado()) atacante.addMuerte(coord);
     } else {
       float perdidaPorAtaque = (fuerzaAtaque == fuerzaDefensa) ? -1 : fuerzaDefensa - fuerzaAtaque;
       float perdidaPorDefensa = perdidaPorAtaque;
       
       atacante.setPeso(atacante.getPeso()-perdidaPorAtaque);
       presa.setPeso(presa.getPeso()-perdidaPorDefensa);
     }
     
   }
   }
  }
}
