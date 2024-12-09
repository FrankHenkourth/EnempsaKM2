package fte102.jpgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.util.ArrayList;

import fte102.jpgame.Coordenada;
import fte102.jpgame.Juego;
import fte102.jpgame.celdas.Celda;
import fte102.jpgame.celdas.Especie;
import fte102.jpgame.comportamientos.Enfermedad;
import java.awt.BasicStroke;

public class PanelRender extends JPanel {

    private Juego juego;
    private int alto, ancho, anchoCelda, anchoMax, altoMax, cantCeldas;

    public PanelRender(Juego juego) {
        this.juego = juego;
        alto = juego.getAlto();
        ancho = juego.getAncho();
        anchoCelda = juego.getAnchoCelda();
        cantCeldas = juego.getCantCeldas();
        anchoMax = ancho * cantCeldas + 1;
        altoMax = alto * cantCeldas + 1;

        Dimension dim = new Dimension(alto, ancho);
        setSize(alto, ancho);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);

        setBackground(Color.decode("#ffffff"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(Color.WHITE);

        ArrayList<Enfermedad> enfs = juego.getEnfermedades();

        for (int i = 0; i < enfs.size(); i++) {
            Enfermedad enf = enfs.get(i);
            if (enf.getOrigen() != null) {
                Coordenada[] coords = juego.CCeldVer(enf.getOrigen(), enf.getAlcance());
                for (Coordenada coord : coords) {
                    dibujarRCuadrado(g2d, coord.getX() * anchoCelda, coord.getY() * anchoCelda, enf.getColor());
                }

            }
        }

        dibujarCuadricula(g2d);

        int x = 0;
        int y = 0;
        boolean sel = false;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Celda[] celdas : juego.getCeldas()) {
            for (Celda celda : celdas) {
                if (celda.estaSeleccionada()) {
                    x = celda.getX() * anchoCelda;
                    y = celda.getY() * anchoCelda;
                    sel = true;
                }

                if (celda instanceof Especie) {
                    Especie especie = (Especie) celda;
                    if (especie.getID() == "contigua") {
                        dibujarSCirculo(g2d, especie.getX(), especie.getY(), especie.getColor());
                    } else {
                        dibujarCirculo(g2d, especie.getX(), especie.getY(), especie.getColor());
                    }
                }

            }
        }

        if (sel) {
            dibujarCuadrado(g2d, x, y, "#f02f20");
        }

        repaint();
    }

    public void dibujarCuadricula(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i <= ancho; i += anchoCelda)g2d.drawLine(i, 0, i, ancho);
        for (int i = 0; i <= ancho; i += anchoCelda)g2d.drawLine(0, i, ancho, i);
    }

    public void dibujarCuadrado(Graphics2D g2d, int x, int y, String color) {
        g2d.setColor(Color.decode(color));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(x, y, anchoCelda, anchoCelda);
    }

    public void dibujarRCuadrado(Graphics2D g2d, int x, int y, String color) {
        g2d.setColor(Color.decode(color));
        g2d.fillRect(x, y, anchoCelda, anchoCelda);
    }

    public void dibujarCirculo(Graphics2D g2d, int x, int y, String color) {
        g2d.setColor(Color.decode(color));
        g2d.fillOval(x * anchoCelda + 3, y * anchoCelda + 3, anchoCelda - 6, anchoCelda - 6);
    }

    public void dibujarSCirculo(Graphics2D g2d, int x, int y, String color) {
        g2d.setColor(Color.decode(color));
        g2d.drawOval(x * anchoCelda + 3, y * anchoCelda + 3, anchoCelda - 6, anchoCelda - 6);
    }

}
