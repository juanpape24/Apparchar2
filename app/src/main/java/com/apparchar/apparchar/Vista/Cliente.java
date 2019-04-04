package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.Presentador.RegistrarPresenter;

import org.json.JSONException;

public class Cliente extends AppCompatActivity implements ContractClient.View {
    private TextView nombre, apellido, edad, email, cel, user, pass, pass2;
    private EditText nombre1, apellido1, edad1, email1, cel1, user1, pass1, pass3;
    private ContractClient.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        nombre = findViewById(R.id.nombre);
        nombre1 = findViewById(R.id.nombre1);
        apellido = findViewById(R.id.apellido);
        apellido1 = findViewById(R.id.apellido1);
        edad = findViewById(R.id.edad);
        edad1 = findViewById(R.id.edad1);
        email = findViewById(R.id.email);
        email1 = findViewById(R.id.email1);
        cel = findViewById(R.id.cel);
        cel1 = findViewById(R.id.cel1);
        user = findViewById(R.id.user);
        cel = findViewById(R.id.cel);
        cel1 = findViewById(R.id.cel1);
        user = findViewById(R.id.user);
        user1 = findViewById(R.id.user1);
        pass = findViewById(R.id.pass);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        pass3 = findViewById(R.id.pass3);
        presenter = new RegistrarPresenter(this);

    }


    public void registrar(View view) throws JSONException {
        presenter.enviar(nombre1.getText().toString(), apellido1.getText().toString(), edad1.getText().toString(), email1.getText().toString(), cel1.getText().toString(), user1.getText().toString(), pass1.getText().toString(), pass3.getText().toString());
    }

    @Override
    public void showResult(String info) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }

    public void swap() {
        Intent cambiar = new Intent(this, LoginActivity.class);
        startActivity(cambiar);
    }
}
