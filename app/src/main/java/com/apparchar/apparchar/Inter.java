package com.apparchar.apparchar;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.VO.EventoVO;

import java.util.ArrayList;
import java.util.List;

public class Inter implements ContractListaEvento.Interactor {
    List<EventoVO> lista=new ArrayList<>();
    ContractListaEvento.EventoPresenter presenter;

    public Inter(ContractListaEvento.EventoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getEvent() {
        lista.add(new EventoVO("nombre","sss","sss","sass","Cule Marimondazo",R.drawable.images,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Cule Marimondazo",R.drawable.images,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Cule Marimondazo",R.drawable.images,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Twitter",R.drawable.descarga,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Facebook",R.drawable.descarga1,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Música",R.drawable.descarga2,"la que sea"));
        presenter.upEvent(lista);
    }
}
