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

    private HashMap<Integer, Material> mapInventarios;

    public InventarioMaterial() {
        listaInventarios = new ArrayList<>();
        mapInventarios = new HashMap<>();
    }

    public List<Material> obtenerMateriales() {
        return Collections.unmodifiableList(listaInventarios);
    }

        public void AgregarItem(Material item){
            Material itemInventario= mapInventarios.get(item.getId());
            if (itemInventario == null){
                mapInventarios.put(item.getId(),item);
                listaInventarios.add(item);
            }
            else{
                itemInventario.setCantidad(item.getCantidad()+itemInventario.getCantidad());
            }
    }
    public void retirarItem(Material material,int sacar){
       Material aux = mapInventarios.get(material.getId());
        System.out.println(aux.getCantidad());
       if (aux.getCantidad() >= sacar){
            aux.setCantidad(aux.getCantidad()-sacar);

       }
    }
}
