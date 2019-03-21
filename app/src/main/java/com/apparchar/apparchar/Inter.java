package com.apparchar.apparchar;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.Contract.ContractListaEvento;

public class Inter implements ContractListaEvento.Interactor {

    ContractListaEvento.EventoPresenter presenter;

    public Inter(ContractListaEvento.EventoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getEvent() {

    }
}
