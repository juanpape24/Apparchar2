package com.apparchar.apparchar.Vista;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.RealTimeActivity;
import com.apparchar.apparchar.RecyclerViewAdapter;
import com.apparchar.apparchar.Utils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;
import java.util.List;

public class ListaEvento extends AppCompatActivity implements ContractListaEvento.ViewEvento {
    private RecyclerView rv;
    private RecyclerViewAdapter adapter;
    private ContractListaEvento.EventoPresenter presenter;
    private String idUser="";
    private TextView event;
    private ArrayList<EventoM> eventos;
    GridLayoutManager gridLayoutManager;
    private static final String TAG = "LISTA EVENTO";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    //Widgets

    public boolean servicesOk() {
        Log.d(TAG, "SERVICIOS OK");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {
            //Todo esta bien entonces conecta
            Log.d(TAG, "CONEXION EXITOSA CON PLAY SERVICES");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // Hay un error pero se puede arreglar
            Log.d(TAG, "HAY ERRORES PERO SE PUEDEN SOLUCIONAR");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "NO SE PUEDE CONECTAR CON PLAY SERVICES", Toast.LENGTH_SHORT);
        }
        return false;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_evento);
        event=findViewById(R.id.event);
        getSupportActionBar().hide();
        idUser=getIntent().getExtras().getString("user");
        presenter=new ListaEventoPresenter(this);

    }


    @Override
    public void dato(List<EventoM> lista) {
        Log.i("info","sasass");
        rv= findViewById(R.id.recycler);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new RecyclerViewAdapter(getApplicationContext(),lista,idUser);
        rv.setAdapter(adapter);
        eventos= (ArrayList<EventoM>) lista;

    }

    @Override
    public void showResult(String mensaje) {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
    public void onBackPressed() {
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void actualizar(View view){
        presenter=new ListaEventoPresenter(this);
    }

    public void mapa(View view){

        if(servicesOk()) {
            Intent intent = new Intent(this, RealTimeActivity.class);
            ArrayList<String> dirrecion=new ArrayList<>();
            for (int i=0;i<eventos.size();i++){
                dirrecion.add(eventos.get(i).getDireccion().getDireccion()+" Bogotá");
            }
            intent.putStringArrayListExtra("direcciones",dirrecion);
            startActivity(intent);
        }
    }
    public void cSesion(View view){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
