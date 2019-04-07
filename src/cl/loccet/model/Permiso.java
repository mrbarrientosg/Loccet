package cl.loccet.model;

import java.util.ArrayList;

public class Permiso {

    private ArrayList<ItemPermiso> listaPermiso;

    public Permiso(){
        listaPermiso = new ArrayList<>();
    }

    public boolean AgregarPermiso(ItemPermiso item){
        for(int i = 0; i < listaPermiso.size(); i++){
            if(item.getTipoPermiso().compareTo(listaPermiso.get(i).getTipoPermiso()) == 0){
                return false;
            }
        }
        return listaPermiso.add(item);
    }
    public void ValidarPermiso(int i){
        if(listaPermiso.get(i).isAprobado()){
            listaPermiso.get(i).setAprobado(true);
        }
        listaPermiso.get(i).setAprobado(false);
    }
}
