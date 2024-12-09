package fte102.jpgame;

import fte102.jpgame.Coordenada;
import fte102.jpgame.celdas.Animal;
import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.Especie;
import fte102.jpgame.comportamientos.*;
import fte102.jpgame.comportamientos.Comportamiento;
import fte102.jpgame.comportamientos.Enfermedad;
import fte102.jpgame.comportamientos.enfermedades.*;
import java.util.ArrayList;

public class ControlComportamientos {
  private Comportamiento[] comportamientos;
  private Enfermedad[] enfermedades;
  private Comportamiento[] fenomenos;
  private ArrayList<Enfermedad> enfermedadesGlobales;

  public ControlComportamientos() {
    comportamientos =
        new Comportamiento[] {
          new Envejecer(),
          new Hambre(),
          new Crecer(),
          new Comer(),
          new Mover(),
          new Morir(),
          new IA(),
          new Nacer(),
          new Proteger()
        };

    enfermedades = new Enfermedad[] {new Rabia(), new Dinofobia()};

    fenomenos = new Comportamiento[] {new Reforestacion()};

    enfermedadesGlobales = new ArrayList<>();
  }

  public boolean enfermedadActiva(String enfID) {
    boolean activa = false;
    for (int x = 0; x < enfermedadesGlobales.size(); x++) {
      if (enfermedadesGlobales.get(x).getID().equals(enfID)) {
        activa = true;
        break;
      }
    }
    return activa;
  }

  public ArrayList<Enfermedad> getEnfermedades() {
    return enfermedadesGlobales;
  }
  
  public void setEnfermedades(ArrayList<Enfermedad> enfs) {
     enfermedadesGlobales = enfs;
  }

  public void generarFenomenos(ControlCeldas CCeld) {

    // Probabilidas de que un animal se infecte al estar en el area infectada
    for (int x = 0; x < enfermedadesGlobales.size(); x++) {
      if (enfermedadesGlobales.get(x).finalizada()
          || enfermedadesGlobales.get(x).getOrigen() == null) enfermedadesGlobales.remove(x);
      else {
        Enfermedad enfermedad = enfermedadesGlobales.get(x);
        Coordenada[] coords =
            CCeld.verZonaCircularDesde(enfermedad.getOrigen(), enfermedad.getAlcance());

        for (Coordenada coord : coords) {
          Celda celda = CCeld.getCelda(coord);

          if (celda instanceof Animal) {
            Animal animal = (Animal) celda;

            if (!animal.tieneEnfermedad(enfermedad.getID())
                && CCeld.probabilidad(enfermedad.getProbabilidadInfeccion())) {
              Enfermedad enf = new Dinofobia();

              if (enfermedad.getID().equals("rabia")) enf = new Rabia();

              animal.addEnfermedad(enf);

              enf.ejecucionInicial(this, CCeld, animal);
              enf.pasarCiclo();
            }
          }
        }

        // Desarrollo de la enfermedad: propagacion y paso de ciclos
        enfermedad.pasarCiclo();
      }
    }

    // probabilidad de que surga una(s) nueva(s) enfermedad(s) global(es)
    for (Enfermedad enf : enfermedades) {
      if (CCeld.probabilidad(enf.getProbabilidadSurguir())) {
        Enfermedad enfermedad;
        Coordenada origen = CCeld.coordenadaAleatoria();
        int alcanceMax = CCeld.numeroAleatorio(3, 7);

        if (enf.getID().equals("rabia")) enfermedad = new Rabia();
        else enfermedad = new Dinofobia();

        enfermedad.setOrigen(origen);
        enfermedad.setAlcance(1, alcanceMax);
        enfermedadesGlobales.add(enfermedad);
      }
    }

    // Ejecutar todos los fenómenos
    for (Comportamiento comp : fenomenos) {
      comp.run(this, CCeld);
    }
  }

  public int buscar(String compID) {
    for (int id = 0; id < comportamientos.length; id++) {
      if (comportamientos[id].getID().equals(compID)) return id;
    }
    return -1;
  }

  public int buscarE(String enfID) {
    for (int id = 0; id < enfermedades.length; id++) {
      if (enfermedades[id].getID().equals(enfID)) return id;
    }
    return -1;
  }

  public boolean existe(String compID) {
    return (buscar(compID) == -1);
  }

  public void run(String compID, ControlCeldas CCeld, Especie especie) {
    int id = buscar(compID);
    if (id != -1) {
      comportamientos[id].run(this, CCeld, especie);
    }
  }

  public void ejecutarEnfermedad(String enfID, ControlCeldas CCeld, Animal animal) {
    int id = buscar(enfID);
    if (id != -1) {
      comportamientos[id].run(this, CCeld, animal);
    }
  }

  public void run(String compID, ControlCeldas CCeld, Especie especie, Coordenada coord) {
    int id = buscar(compID);
    if (id != -1) {
      comportamientos[id].run(this, CCeld, especie, coord);
    }
  }
}
