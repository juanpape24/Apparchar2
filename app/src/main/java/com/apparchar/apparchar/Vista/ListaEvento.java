package com.apparchar.apparchar.Vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.RecyclerViewAdapter;
import com.apparchar.apparchar.VO.CategoriaVO;
import com.apparchar.apparchar.VO.EventoVO;

import java.util.ArrayList;
import java.util.List;

public class ListaEvento extends AppCompatActivity implements ContractListaEvento.ViewEvento {
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;
    private ContractListaEvento.EventoPresenter presenter;
    GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        final ArrayList<CategoriaVO> a= (ArrayList<CategoriaVO>) getIntent().getExtras().get("datos");
        presenter=new ListaEventoPresenter(this);
        presenter.getEvent();

    }


    @Override
    public void dato(List<EventoVO> lista) {
        Log.i("info","sasass");
        rv= findViewById(R.id.recycler);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new RecyclerViewAdapter(getApplicationContext(),lista);
        rv.setAdapter(adapter);

    }

    @Override
    public void showResult(String mensaje) {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
}
