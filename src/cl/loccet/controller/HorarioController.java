package cl.loccet.controller;

import cl.loccet.base.Controller;

public class HorarioController extends Controller {

    private static class Dia {
        public static final int LUNES = 0;
        public static final int MARTES = 1;
        public static final int MIERCOLES = 2;
        public static final int JUEVES = 3;
        public static final int VIERNES = 4;
        public static final int SABADO = 5;
        public static final int DOMINGO = 6;
    }

    public void agregarHorario(int dia) {
        switch (dia) {
            case Dia.LUNES:
                System.out.println("Lunes");
                break;
        }
    }
}
