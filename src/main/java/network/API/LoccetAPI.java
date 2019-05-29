package network.API;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import network.NetworkManager;
import network.URLRequestConvertible;
import org.asynchttpclient.util.HttpConstants;

public enum LoccetAPI implements URLRequestConvertible {
    LOGIN,
    GET_CONSTRUCTORA,
    GET_PROYECTOS,
    GET_TRABAJADORES_PROYECTOS,
    GET_TRABAJADORES_CONSTRUCTORA,
    GET_HORARIOS_TRABAJADORES,
    GET_ASISTENCIAS_TRABAJADORES,
    GET_FASES_PROYECTOS,
    GET_TAREAS_FASES,
    GET_MATERIALES_PROYECTOS,
    GET_REGISTRO_MATERIALES;

    public String baseURL() {
        switch (NetworkManager.enviroment) {
            case PRODUCTION:
                return "";
            case DEVELOPMENT:
                return "https://djmte8zah9.execute-api.us-east-1.amazonaws.com/dev/";
        }

        return "";
    }

    public String path() {
        switch (this) {
            case LOGIN:
                return "login";
            case GET_CONSTRUCTORA:
                return "loccet-getconstructora";
            case GET_PROYECTOS:
                return "loccet-getproyectos";
            case GET_TRABAJADORES_PROYECTOS:
                return "loccet-gettrabajadoresproyectos";
            case GET_TRABAJADORES_CONSTRUCTORA:
                return "loccet-gettrabajadores";
            case GET_HORARIOS_TRABAJADORES:
                return "loccet-gethorariostrabajadores";
            case GET_ASISTENCIAS_TRABAJADORES:
                return "loccet-getasistenciastrabajadores";
            case GET_FASES_PROYECTOS:
                return "loccet-getfasesproyectos";
            case GET_TAREAS_FASES:
                return "loccet-gettareasfases";
            case GET_MATERIALES_PROYECTOS:
                return "loccet-getmaterialesproyectos";
            case GET_REGISTRO_MATERIALES:
                return "loccet-getregistrosmateriales";
        }

        return "";
    }

    @Override
    public String method() {
        return HttpConstants.Methods.POST;
    }

    @Override
    public HttpHeaders headers() {
        HttpHeaders headers;
        headers = new DefaultHttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        return headers;
    }
}
