package fte102.jpgame;

import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.Especie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Archivo {

    private String nombre;

    public Archivo(String nombre) {
        this.nombre = nombre;
    }

    public String leer() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void guardar(String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
            bw.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void agregarAdjunto(String archivoAdjunto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre, true))) {
            bw.write("\n[Adjunto]: " + archivoAdjunto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void guardarDatos(String nombreProyecto, int cantCeldas, Celda[][] celdas) {
        String datos = "P," + nombreProyecto + "," + cantCeldas +",";
        String cel = "";
        String cels = "";
        String selecc = "";
        
        for(Celda[] celdas1 : celdas) {
            for(Celda celda : celdas1) {
                cel = "" + celda.getX() + "," + celda.getY() + ",";
                
                if(celda.estaSeleccionada()) selecc = "" + celda.getX() + "," + celda.getY();
                
                if(celda instanceof Especie) {
                    
                }
                
            }
            cels += cel + "\n";
        }
        
        datos += selecc + "\n" + cels + "\n";
    }
    
    public void validar() {
    
    }
    
    public void convertir() {
    
    }

    public boolean existe() {
        File archivo = new File(nombre);
        return archivo.exists();
    }
}