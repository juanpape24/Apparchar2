package com.apparchar.apparchar.Presentador;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Inter;
import com.apparchar.apparchar.RecyclerViewAdapter;
import com.apparchar.apparchar.VO.EventoVO;
import com.apparchar.apparchar.Vista.ListaEvento;

import java.util.List;

public class ListaEventoPresenter implements ContractListaEvento.EventoPresenter {

    ContractListaEvento.ViewEvento vista;
    ContractListaEvento.Interactor interactor;

    public ListaEventoPresenter(ContractListaEvento.ViewEvento vista) {
        this.vista = vista;
        interactor= new Inter(this);
    }

    @Override
    public void getEvent() {
        if(vista!=null){
            interactor.getEvent();
        }
    }

    @Override
    public void upEvent(List<EventoVO> lista) {
        if(!lista.isEmpty()){
            vista.dato(lista);
        }else
            vista.showResult("No hay eventos disponibles");
    }
}
