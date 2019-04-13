package cl.loccet.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class InventarioMaterial {

    private ArrayList<Material> listaInventarios;

    private HashMap<String, Material> mapInventarios;

    public InventarioMaterial() {
        listaInventarios = new ArrayList<>();
        mapInventarios = new HashMap<>();
    }

    public List<Material> obtenerMateriales() {
        return Collections.unmodifiableList(listaInventarios);
    }

    public void AgregarItem(Material item){
        Material itemInventario= mapInventarios.get(item.getNombre());
        if (itemInventario == null){
            mapInventarios.put(item.getNombre(),item);
            listaInventarios.add(item);
        }

    }


}
