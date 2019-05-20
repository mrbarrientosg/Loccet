package network;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.util.HttpConstants;

public enum LoccetAPI implements URLRequestConvertible  {
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

    private HttpHeaders _headers;

    public String baseURL() {
        switch (NetworkManager.enviroment) {
            case PRODUCTION:
                return "";
            case DEVELOPMENT:
                return "https://djmte8zah9.execute-api.us-east-1.amazonaws.com/dev/";
        }

        return null;
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

        return null;
    }

    @Override
    public String method() {
        return HttpConstants.Methods.POST;
    }

    @Override
    public HttpHeaders headers() {
        if (_headers == null) {
            _headers = new DefaultHttpHeaders();
            _headers.add("Content-Type", "application/json");
            _headers.add("Accept", "application/json");
        }

        return _headers;
    }
}
