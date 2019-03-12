package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.apparchar.apparchar.R;

import java.util.ArrayList;

public class EventoCreado extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_evento_creado);
        linearLayout=(LinearLayout) findViewById(R.id.vistaEventos);
        Button btn= new Button(this);
        final ArrayList a= (ArrayList) getIntent().getExtras().get("datos");
        btn.setText(String.valueOf(a.get(0)));
        linearLayout.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventoCreado.this, CalificacionEvento.class);
                i.putExtra("datos",a);
                startActivity(i);

            }
        });



    }


}

