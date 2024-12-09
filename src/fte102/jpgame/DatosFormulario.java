
package fte102.jpgame;

import java.util.ArrayList;


public class DatosFormulario {
    private ArrayList<String> textos;
    private ArrayList<Integer> numeros;
    private ArrayList<Boolean> boleanos;
  
    private int numero;
    private String texto;

    public DatosFormulario() {
        textos = new ArrayList<>();
        numeros = new ArrayList<>();
        boleanos = new ArrayList<>();
        int numero = 0;
        texto = "";
    }

    public ArrayList<String> getTextos() {
        return textos;
    }

    public void setTextos(ArrayList<String> textos) {
        this.textos = textos;
    }

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }

    public ArrayList<Boolean> getBoleanos() {
        return boleanos;
    }

    public void setBoleanos(ArrayList<Boolean> boleanos) {
        this.boleanos = boleanos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
