package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Presentador.LoginPresenter;
import com.apparchar.apparchar.R;

public class LoginActivity extends AppCompatActivity implements ContractLogin.ViewL {

    private TextView ver1, ver2;
    private EditText texto1, texto2;
    private ContractLogin.PresenterL presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ver1 = findViewById(R.id.ver1);
        ver2 = findViewById(R.id.ver2);
        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        presenter=new LoginPresenter(this);
    }

    public void logIn(View view) {
        presenter.validar(texto1.getText().toString(),texto2.getText().toString());
    }

    public void recordar(View view) {

    }

    public void registrar(View view) {
        Intent next=new Intent(this, TipoLogin.class);
        startActivity(next);
    }

    @Override
    public void showResult(String info) {
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }
}
