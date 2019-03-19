package com.apparchar.apparchar.Vista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.VO.CategoriaVO;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class EventoCreado extends AppCompatActivity{
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_evento_creado);
        linearLayout= findViewById(R.id.vistaEventos);
        final ArrayList<CategoriaVO> a= (ArrayList<CategoriaVO>) getIntent().getExtras().get("datos");
        for (int i=0;i<a.size();i++){

            Button evento = new Button(this);
            int id=a.get(i).getId();
            evento.setText(String.valueOf(a.get(i).getNombre()));
            evento.setId(id);
            evento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= new Intent(EventoCreado.this,CalificacionEvento.class);
                    intent.putExtra("idEvento",evento.getId());
                    intent.putExtra("usuario",getIntent().getExtras().getString("usuario"));
                    startActivity(intent);
                }
            });
            linearLayout.addView(evento);
        }




    }



}

