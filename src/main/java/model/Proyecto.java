package model;

import repository.RepositoryAsistencia;
import repository.RepositoryFase;
import repository.memory.MemoryRepositoryAsistencia;
import repository.memory.MemoryRepositoryFase;
import repository.memory.MemoryRepositoryTrabajador;
import repository.RepositoryTrabajador;

import java.time.LocalDate;

public class Proyecto {

    // MARK: - Variables
    private String id;

    private String nombreProyecto;

    private Localizacion localizacion;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private double estimacion;

    private String cliente;

    private RepositoryTrabajador repositoryTrabajador;

    private RepositoryAsistencia repositoryAsistencia;

    private RepositoryFase repositoryFase;

    private InventarioMaterial inventarioMaterial;

    // MARK: - Constructor

    private Proyecto(Builder builder){
        this.id = builder.id;
        this.nombreProyecto = builder.nombreProyecto;
        this.estimacion = builder.estimacion;
        this.fechaInicio = builder.fechaInicio;
        this.fechaTermino = builder.fechaTermino;
        this.cliente = builder.cliente;

        repositoryAsistencia = new MemoryRepositoryAsistencia();
        repositoryTrabajador = new MemoryRepositoryTrabajador();
        repositoryFase = new MemoryRepositoryFase();
        inventarioMaterial = new InventarioMaterial();
    }

    // MARK: - Getter

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getId() {
        return id;
    }

    public double getEstimacion() {
        return estimacion;
    }

    public InventarioMaterial getInventarioMaterial() {
        return inventarioMaterial;
    }

    public String getCliente() {
        return cliente;
    }

    // MARK: - Metodos

    /**
     * Agrega un nuevo trabajador al proyecto
     * @param trabajador trabajador a guardar
     * @return true si pudo agregarlo, en caso contrario false
     *
     * @author Matias Zuñiga
     */
    public void agregarTrabajador(Trabajador trabajador){
        trabajador.asociarProyecto(this);
        repositoryTrabajador.add(trabajador);
    }


    public Trabajador actualizarTrabajador(Trabajador nuevoTrabajador) {
        return repositoryTrabajador.update(nuevoTrabajador);
    }


//    /**
//     * Busca todos los trabajadores que coincidan con la busqueda
//     * @param busqueda Texto de Busqueda
//     * @return Lista de Trabajadores encontrados
//     *
//     * @author Matias Barrientos
//     */
//    public List<Trabajador> buscarTrabajador(String busqueda) {
//        ArrayList<Trabajador> encontrados = new ArrayList<>();
//
//        for (Trabajador trabajador: listaTrabajadores) {
//            if (StringUtils.containsIgnoreCase(trabajador.getNombre(), busqueda) ||
//                    StringUtils.containsIgnoreCase(trabajador.getRut(), busqueda))
//                encontrados.add(trabajador);
//        }
//
//        return encontrados;
//    }

    /**
     * Obtiene al trabajador el cual coincida con el rut.
     * @param rut del trabajador.
     * @return Trabajador encontrado.
     *
     * @author Matias Barrientos
     */
    public Trabajador obtenerTrabajador(String rut) {
        return repositoryTrabajador.get(rut);
    }

    /**
     * Elimina al trabajador que coincida con el rut.
     * @param rut del trabajador.
     * @return El trabajador eliminado
     *
     * @author Matias Barrientos
     */
    public Trabajador eliminarTrabajador(String rut) {
        return repositoryTrabajador.remove(repositoryTrabajador.get(rut));
    }

    /*public void estimacionGasto() {
        double total = inventarioMaterial.gastoTotal();

        NumberFormat formatter = new DecimalFormat("#0.00$");

        System.out.println("Gasto inventario (estimación): " + formatter.format(total));

        Duration diff = Duration.between(fechaInicio.atStartOfDay(), fechaTermino.atStartOfDay());
        long cant = diff.toDays();

        double gastoTrabajadores = listaTrabajadores.stream()
                .map(Trabajador::getEspecialidad)
                .map(Especialidad::sueldoTotal)
                .map(aDouble -> aDouble * cant)
                .reduce(0.0, (left, right) -> left + right);

        System.out.println("Gasto trabajadores (estimación): " + formatter.format(gastoTrabajadores));

        total += gastoTrabajadores;

        System.out.println("Gasto total de la estimación: " + formatter.format(total));

        System.out.println("Gasto propuesto menos estimación: " + formatter.format(estimacion - total));
    }*/

    public static class Builder {
        private String id;
        private String nombreProyecto;
        private String jefeProyecto;
        private String cliente;
        private String mailCliente;
        private String telefonoCliente;
        private String direccion;
        private String pais;
        private String ciudad;
        private String estado;
        private LocalDate fechaInicio;
        private LocalDate fechaTermino;
        private String fechaTerminoReal;
        private double estimacion;
        private double costoReal;

        public Builder(String nombreProyecto, String jefeProyecto, Double estimacion, String cliente){
            this.id = generarId();
            this.nombreProyecto = nombreProyecto;
            this.jefeProyecto = jefeProyecto;
            this.estimacion = estimacion;
            this.cliente = cliente;
        }

        public Builder datosCliente(String mailCliente, String telefonoCliente) {
            this.mailCliente = mailCliente;
            this.telefonoCliente = telefonoCliente;
            return this;
        }

        public Builder datosUbicacion(String direccion, String pais, String ciudad, String estado) {
            this.direccion = direccion;
            this.pais = pais;
            this.ciudad = ciudad;
            this.estado = estado;
            return this;
        }

        public Builder fechaProyecto(LocalDate fechaInicio, LocalDate fechaTermino) {
            this.fechaInicio = fechaInicio;
            this.fechaTermino = fechaTermino;
            return this;
        }

        public Proyecto build(){
            return new Proyecto(this);
        }

        /**
         * @return un string generando un Id unico para el proyecto.
         * @author Matías Zúñiga
         */
        private final String generarId() {
            String result = java.util.UUID.randomUUID().toString();
            //result = result.replaceAll("-", "");
            //result = result.replaceAll("[A-Za-z]","");
            result = result.substring(0, 12);
            return result;
        }


    }


}
