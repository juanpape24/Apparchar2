package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractEmpresa;
import com.apparchar.apparchar.Presentador.EmpresaPresenter;
import com.apparchar.apparchar.R;

public class Empresa extends AppCompatActivity implements ContractEmpresa.ViewE {
    private TextView nombree, emaile, address, cele, usere, passe, passe2;
    private EditText nombree1, emaile1, address1, cele1, usere1, passe1, passe3;
    private ContractEmpresa.PresenterE presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);
        nombree = findViewById(R.id.nombree);
        nombree1 = findViewById(R.id.nombree1);
        emaile = findViewById(R.id.emaile);
        emaile1 = findViewById(R.id.emaile1);
        address = findViewById(R.id.address);
        address1 = findViewById(R.id.address1);
        cele = findViewById(R.id.cele);
        cele1 = findViewById(R.id.cele1);
        usere = findViewById(R.id.usere);
        usere1 = findViewById(R.id.usere1);
        passe = findViewById(R.id.passe);
        passe1 = findViewById(R.id.passe1);
        passe2 = findViewById(R.id.passe2);
        passe3 = findViewById(R.id.passe3);
        presentador = new EmpresaPresenter(this);

    }


    public void rEmpresa(View view) {
        presentador.signUp(nombree1.getText().toString(), emaile1.getText().toString(), address1.getText().toString(), cele1.getText().toString(), usere1.getText().toString(), passe1.getText().toString(), passe3.getText().toString());

    }

    @Override
    public void showResultE(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cambiar() {
        Intent cargar = new Intent(this, LoginActivity.class);
        startActivity(cargar);

    }
}
