package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.Presentador.RegistrarPresenter;

import org.json.JSONException;

import java.util.ArrayList;

public class Cliente extends AppCompatActivity implements ContractClient.View, AdapterView.OnItemSelectedListener {
    private EditText nombre1, apellido1,email1, cel1, user1, pass1, pass3;
    private MaterialBetterSpinner edad1;
    private ContractClient.Presenter presenter;
    private ArrayList edades=new ArrayList();
    private String item="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        nombre1 = findViewById(R.id.nombre1);
        apellido1 = findViewById(R.id.apellido1);
        edad1 = findViewById(R.id.edad1);
        email1 = findViewById(R.id.email1);
        cel1 = findViewById(R.id.cel1);
        user1 = findViewById(R.id.user1);
        pass1 = findViewById(R.id.pass1);
        pass3 = findViewById(R.id.pass3);
        llenar();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,edades);
        edad1.setAdapter(adapter);
        presenter = new RegistrarPresenter(this);

    }

    public void llenar(){
        edades.add("");
        for (int i=18;i<=70;i++){
            edades.add(i);
        }
    }
    public void registrar(View view) throws JSONException {
        presenter.enviar(nombre1.getText().toString(), apellido1.getText().toString(),edad1.getText().toString(),email1.getText().toString(), cel1.getText().toString(), user1.getText().toString(), pass1.getText().toString(), pass3.getText().toString());
    }

    @Override
    public void showResult(String info) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }

    public void swap() {
        Intent cambiar = new Intent(this, LoginActivity.class);
        startActivity(cambiar);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
