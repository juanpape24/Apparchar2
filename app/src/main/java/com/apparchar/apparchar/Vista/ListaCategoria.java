package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.apparchar.apparchar.AdapterRecycler;
import com.apparchar.apparchar.Contract.ContractListaCategoria;
import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Modelo.CategoriaM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Presentador.CategoriaPresenter;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaCategoria extends AppCompatActivity implements ContractListaCategoria.viewCategoria {
    private static final String TAG = "LISTA EVENTO";
    GridLayoutManager gridLayoutManager;
    private RecyclerView rv;
    private AdapterRecycler adapter;
    private ContractListaCategoria.presenterCategoria presenter;
    private String idUser = "";
    private ArrayList<String> categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categoria);
        getSupportActionBar().hide();
        idUser = getIntent().getExtras().getString("user");
        presenter = new CategoriaPresenter(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void datos(ArrayList<String> categoria) {
        rv = findViewById(R.id.recycler2);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new AdapterRecycler(getApplicationContext(), categoria, idUser);
        rv.setAdapter(adapter);
        this.categoria = categoria;

    }

    @Override
    public void showResult(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
