package cl.loccet.model;

public class Dias {
    public static final String LUNES = "LUNES";
    public static final String MARTES = "MARTES";
    public static final String MIERCOLES = "MIERCOLES";
    public static final String JUEVES = "JUEVES";
    public static final String VIERNES = "VIERNES";
    public static final String SABADO = "SABADO";
    public static final String DOMINGO = "DOMINGO";

    public static String getNameDay(int day) {
        switch (day) {
            case 1: return LUNES;
            case 2: return MARTES;
            case 3: return MIERCOLES;
            case 4: return JUEVES;
            case 5: return VIERNES;
            case 6: return SABADO;
            case 7: return DOMINGO;
        }
        return null;
    }

    public static Integer getDay(String dayName) {
        switch (dayName) {
            case LUNES: return 1;
            case MARTES: return 2;
            case MIERCOLES: return 3;
            case JUEVES: return 4;
            case VIERNES: return 5;
            case SABADO: return 6;
            case DOMINGO: return 7;
        }

        return 0;
    }
}