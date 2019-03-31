import java.util.ArrayList;
import java.util.HashMap;

public class Constructora{
    private String rutEmpresa;
    private String nombreEmpresa;
    private  ArrayList<Obra> listaObras;
    //TODO:permisos
    private HashMap<Integer, Obra> mapObra;

    public Constructora(String rutEmpresa, String nombreEmpresa) {
        this.rutEmpresa = rutEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        listaObras = new ArrayList<Obra>();
        mapObra = new HashMap<>();
    }
    //Setter

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    //Getter

    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    //Metodos

    public void agregarObra(Obra obra){
        listaObras.add(obra);
        mapObra.put(obra.getIdObra(),obra);
    }

    public boolean agregarTrabajador(Integer idObra, Trabajador trabajador){
        if(mapObra.get(idObra) == null) return false;
        mapObra.get(idObra).agregarTrabajador(trabajador);
        return true;
    }
}

