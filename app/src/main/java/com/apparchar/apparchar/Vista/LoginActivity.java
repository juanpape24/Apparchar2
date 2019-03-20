package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractLogin;
import com.apparchar.apparchar.Presentador.LoginPresenter;
import com.apparchar.apparchar.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements ContractLogin.ViewL {

    private TextView ver1, ver2;
    private EditText texto1, texto2;
    private ContractLogin.PresenterL presenter;
    private CheckBox caja1,caja2;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ver1 = findViewById(R.id.ver1);
        ver2 = findViewById(R.id.ver2);
        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        caja1=findViewById(R.id.caja1);
        caja2=findViewById(R.id.caja2);
        image=findViewById(R.id.image);
        presenter=new LoginPresenter(this);

    }

    public void logIn(View view) {
        String usuario=texto1.getText().toString();
        String pass=texto2.getText().toString();
        String cajita="";
        if(caja1.isChecked()) {
            cajita=caja1.getText().toString();
            caja2.setChecked(false);
        }
        else if(caja2.isChecked()){
            cajita=caja2.getText().toString();
            caja1.setChecked(false);
        }
        presenter.validar(usuario,pass,cajita);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void eventc(ArrayList a, String usuarioF){
        Intent i = new Intent(this, EventoCreado.class);
        i.putExtra("datos", a);
        i.putExtra("usuario",usuarioF);
        startActivity(i);
    }

    public void registrar(View view) {
        Intent next=new Intent(this, TipoLogin.class);
        startActivity(next);
    }
    public void crearEvento(String nit){
        Intent intent=new Intent(this,CreacionEvento.class);
        intent.putExtra("nit",nit);
        startActivity(intent);
    }

    @Override
    public void showResult(String info) {
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }
}
