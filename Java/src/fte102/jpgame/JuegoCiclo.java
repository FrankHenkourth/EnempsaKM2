package fte102.jpgame;

import java.util.Timer;
import java.util.TimerTask;

import fte102.jpgame.Juego;
import javax.swing.JLabel;

public class JuegoCiclo {

    private Timer temporizador;
    private boolean detenido;
    private int ciclo = 0;
    private int velocidad;
    private Juego juego;
    private JLabel cicloInfo;

    public JuegoCiclo(Juego juego, int velocidad, JLabel cicloInfo) {
        temporizador = new Timer();
        this.juego = juego;
        this.velocidad = (int) velocidad / 2;
        this.cicloInfo = cicloInfo;
        detenido = true;
        ciclo = 0;
    }

    public void iniciar() {
        detenido = false;

        TimerTask tarea
                = new TimerTask() {
            @Override
            public void run() {
                if (detenido) {
                    this.cancel();
                } else {
                    juego.ciclo(ciclo);
                    ciclo++;
                    cicloInfo.setText("" + ciclo);
                }
            }
        };

        temporizador.schedule(tarea, velocidad, velocidad);
    }

    public void detener() {
        this.detenido = true;
    }

    public boolean estaDetenido() {
        return detenido;
    }

    public int getCiclo() {
        return ciclo;
    }
}
