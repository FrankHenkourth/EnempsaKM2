package fte102.jpgame.comportamientos;

import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.ControlCeldas;
import fte102.jpgame.celdas.Especie;

public class Crecer extends Comportamiento {

    public Crecer() {
        super("crecer");
    }

    public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie) {
        if (especie.getPeso() > especie.getPesoMax()) {
            especie.setPeso(especie.getPesoMax());
        }
        if (especie.getPeso() < especie.getPesoMax()) {
            especie.setPeso(especie.getPeso() + especie.getAumentoPeso());
        }
    }
}
