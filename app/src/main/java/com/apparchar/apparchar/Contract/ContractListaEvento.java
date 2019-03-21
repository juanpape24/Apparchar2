package com.apparchar.apparchar.Contract;

import com.apparchar.apparchar.Modelo.Evento;
import com.apparchar.apparchar.VO.EventoVO;

import java.util.List;


public interface ContractListaEvento {

    interface ViewEvento{
        void dato(List<EventoVO> lista);
        void showResult(String mensaje);
    }
    interface EventoPresenter{
        void getEvent();
        void upEvent(List<EventoVO> lista);
    }
    interface Interactor{
        void getEvent();
    }
}
