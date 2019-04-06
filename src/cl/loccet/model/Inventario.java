package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase Inventario
 *
 * @author Sebastian Fuenzalida
 */
public class Inventario {

    private ArrayList<ItemInventario> listaInventarios;

    private HashMap<Integer, ItemInventario> mapInventarios;

    public Inventario() {
        listaInventarios = new ArrayList<>();
        mapInventarios = new HashMap<>();
    }

    public void AgregarItem(ItemInventario item){
        ItemInventario itemInventario= mapInventarios.get(item.getId());
        if (itemInventario == null){
            mapInventarios.put(item.getId(),item);
            listaInventarios.add(item);
        }
        else{
            itemInventario.setCantidad(itemInventario.getCantidad() + item.getCantidad());
        }
    }
}
