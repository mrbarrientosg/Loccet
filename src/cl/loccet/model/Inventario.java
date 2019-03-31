package cl.loccet.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {
    private ArrayList<ItemInventario> listaInventarios;
    private HashMap<> mapInventarios;

    public Inventario(){
        listaInventarios = new ArrayList<>();
        mapInventarios = new HashMap<>();
    }
    public void AgregarItem(ItemInventario item){
        if (mapInventarios.get(item.getId()) == null){
            mapInventarios.put(item.getId(),item);
            listaInventarios.add(item);
        }
        else{

        }
    }

}
