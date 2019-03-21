package com.apparchar.apparchar.Vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apparchar.apparchar.R;
import com.apparchar.apparchar.RecyclerViewAdapter;
import com.apparchar.apparchar.VO.EventoVO;

import java.util.ArrayList;
import java.util.List;

public class ListaEvento extends AppCompatActivity {

    List<EventoVO> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        lista=new ArrayList<>();
        lista.add(new EventoVO("nombre","sss","sss","sass","Twitter",R.drawable.descarga,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Facebook",R.drawable.descarga1,"la que sea"));
        lista.add(new EventoVO("nombre","sss","sss","sass","Música",R.drawable.descarga2,"la que sea"));
        RecyclerView rv=findViewById(R.id.recycler);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,lista);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(adapter);
    }
}
