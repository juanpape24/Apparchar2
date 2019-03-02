package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.apparchar.apparchar.R;

public class LoginActivity extends AppCompatActivity {

    private TextView ver1, ver2;
    private EditText texto1, texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ver1 = findViewById(R.id.ver1);
        ver2 = findViewById(R.id.ver2);
        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
    }

    public void logIn(View view) {

    }

    public void recordar(View view) {

    }

    public void registrar(View view) {
        Intent next=new Intent(this, TipoLogin.class);
        startActivity(next);
    }
}
