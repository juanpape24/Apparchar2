package com.apparchar.apparchar.Contract;

import com.apparchar.apparchar.Modelo.CategoriaM;

import java.util.ArrayList;

public interface ContractListaCategoria{

    interface viewCategoria{
        void datos(ArrayList<CategoriaM> categoria);
        void showResult(String mensaje);
    }
    interface presenterCategoria{

    }
}
