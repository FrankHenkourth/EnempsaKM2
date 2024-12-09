package fte102.jpgame.comportamientos;

import fte102.jpgame.ControlCeldas;
import fte102.jpgame.ControlComportamientos;
import fte102.jpgame.celdas.Animal;

import fte102.jpgame.celdas.CeldaVacia;
import fte102.jpgame.celdas.Huevo;
import fte102.jpgame.celdas.animales.*;
import fte102.jpgame.celdas.animales.Velociraptor;
import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.celdas.Especie;

public class Nacer extends Comportamiento {

  public Nacer() {
    super("nacer");
  }

  public void run(ControlComportamientos CComp, ControlCeldas CCeld, Especie especie) {
    if (especie instanceof Huevo) {
      Huevo huevo = (Huevo) especie;

      if (huevo.getEdad() >= huevo.getEdadMax() - 1) {
        char sexo = (CCeld.numeroAleatorio(1, 3) == 2 ? 'F' : 'M');
        Animal animal;
        boolean tipo_animal = false;

        switch (huevo.getTipo()) {
          case "velociraptor":
            animal = new Velociraptor(huevo.getX(), huevo.getY(), sexo);
            tipo_animal = true;
            break;
          case "braquiosaurios":
            animal = new Braquiosaurios(huevo.getX(), huevo.getY(), sexo);
            tipo_animal = true;
            break;
          case "pterodactilos":
            animal = new Pterodactilos(huevo.getX(), huevo.getY(), sexo);
            tipo_animal = true;
            break;
          case "tiranosaurios":
            animal = new Braquiosaurios(huevo.getX(), huevo.getY(), sexo);
            tipo_animal = true;
            break;
          case "triceratops":
            animal = new Triceratops(huevo.getX(), huevo.getY(), sexo);
            tipo_animal = true;
            break;
          default:
            animal = new Velociraptor(huevo.getX(), huevo.getY(), sexo);
            tipo_animal = false;
        }

        if (tipo_animal) {
          if (huevo.estaSeleccionada()) animal.seleccionar();
          CCeld.setCelda(animal);
        } else {
          CeldaVacia vacia = new CeldaVacia(huevo.getX(), huevo.getY());
          if (huevo.estaSeleccionada()) vacia.seleccionar();
          CCeld.setCelda(vacia);
        }
      }
    }
  }
}
