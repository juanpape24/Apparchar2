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
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

public class EventoCreado extends AppCompatActivity{
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.vista_evento_creado);
        linearLayout=(LinearLayout) findViewById(R.id.vistaEventos);
        Button btn= new Button(this);
        final ArrayList a= (ArrayList) getIntent().getExtras().get("datos");
        for (int i=0;i<a.size();i++){
            TextView txt = new TextView(this);
            txt.setText(String.valueOf(a.get(i)));
            linearLayout.addView(txt);
        }




    }



}

