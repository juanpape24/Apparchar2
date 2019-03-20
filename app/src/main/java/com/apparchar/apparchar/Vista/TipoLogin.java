package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apparchar.apparchar.R;

public class TipoLogin extends AppCompatActivity {
    private TextView d1,d2,d3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_login);
        d1=findViewById(R.id.d1);
        d2=findViewById(R.id.d2);
        d3=findViewById(R.id.d3);
    }




    public void cliente(View view){
        Intent next=new Intent(this, Cliente.class);
        startActivity(next);
    }
    public void empresa(View view){
        Intent next=new Intent(this,Empresa.class);
        startActivity(next);
    }
}
