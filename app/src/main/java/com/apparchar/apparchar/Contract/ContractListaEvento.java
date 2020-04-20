package com.apparchar.apparchar.Contract;

import com.apparchar.apparchar.Modelo.EventoM;

import java.util.List;


public interface ContractListaEvento {

    interface ViewEvento{
        void dato(List<EventoM> lista);
        void showResult(String mensaje);
        String getCat();
        String getIdentificador();
    }
    interface EventoPresenter{
    }
}
