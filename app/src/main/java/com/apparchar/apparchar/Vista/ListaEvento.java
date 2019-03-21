package com.apparchar.apparchar.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.RecyclerViewAdapter;
import com.apparchar.apparchar.VO.EventoVO;

import java.util.ArrayList;
import java.util.List;

public class ListaEvento extends AppCompatActivity implements ContractListaEvento.ViewEvento {

    List<EventoVO> lista;
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;
    private ContractListaEvento.EventoPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        presenter=new ListaEventoPresenter(this);
        lista=new ArrayList<>();
        lista.add(new EventoVO("nombre","sss","sss","sass","Twitter",R.drawable.descarga,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Facebook",R.drawable.descarga1,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Música",R.drawable.descarga2,"la que sea"));
        presenter.getEvent();
        RecyclerView rv=findViewById(R.id.recycler);
        rv.setLayoutManager(new GridLayoutManager(this,3));
    }


    @Override
    public void datos(List<EventoVO> lista) {
        adapter=new RecyclerViewAdapter(this,lista);
        rv.setAdapter(adapter);
    }
}
