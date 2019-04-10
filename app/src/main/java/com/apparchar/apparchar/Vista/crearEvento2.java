package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractCreacionEvento;
import com.apparchar.apparchar.MapActivity;
import com.apparchar.apparchar.Presentador.CreacionEventoPresenter;
import com.apparchar.apparchar.R;

import java.util.ArrayList;

public class crearEvento2 extends AppCompatActivity implements ContractCreacionEvento.ViewCE {

    //Widgets
    Button b2,map;
    TextView t2;
    TextView direction;

    //Variables Locales
    boolean flag=false;
    String defaultDirection="Calle 75a#28b-21";

    //TAG
    private static final String TAG = "crearEvento2";

    //Estructuras
    ArrayList<String> info;
    ArrayList a, categoriasCheck;
    ArrayList<String> cat;
    LinearLayout categorias;
    ContractCreacionEvento.PresenterCE presentador;
    ArrayList<CheckBox> items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearevento_2);
        b2 = (Button) findViewById(R.id.next2);
        direction= (TextView) findViewById(R.id.defaultDirection);
        map = (Button) findViewById(R.id.ButtonGeocoding);
        t2=(TextView)findViewById(R.id.t2);
        info=getIntent().getStringArrayListExtra("info1");
        //Colocando la direccion por default
        if(info.size()<=2){
            direction.setText(defaultDirection);
        }else{
            direction.setText(info.get(2));
        }

        t2.setTypeface(Typeface.createFromAsset(crearEvento2.this.getAssets(),"Fonts/Abel_Regular.ttf"));

        //LISTAR LAS CATEGORIAS
        categorias = findViewById(R.id.categoriasCheck);
        items = new ArrayList<>();

        presentador = new CreacionEventoPresenter(this);

        a = new ArrayList();
        cat = new ArrayList<>();

    }

    /*
    Boton para lanzar el auxiliar para escoger el lugar del evento
     */
    public void MapAux(View view){
        Intent intent = new Intent(this, MapActivity.class);
        intent.putStringArrayListExtra("info2",info);
        intent.putExtra("user",getIntent().getStringExtra("user"));
        intent.putExtra("nit",getIntent().getStringExtra("nit"));
        startActivity(intent);
    }

    /*
    Escuchador del boton para avanzar en el formulario
     */
    public void next2(View view) {
            Intent intent = new Intent(this, crearEvento3.class);
            intent.putStringArrayListExtra("info1",info);
        intent.putExtra("user",getIntent().getStringExtra("user"));
        intent.putExtra("nit",getIntent().getStringExtra("nit"));
        intent.putStringArrayListExtra("categoriasCheck",categoriasCheck);
            startActivity(intent);

        Log.d(TAG, "Categorias"+categoriasCheck.toString());


    }

    @Override
    public void showResult(String info) {

    }

    @Override
    public void mostrarCategorias(ArrayList cat) {
        categoriasCheck = new ArrayList();
        for (int i = 0; i < cat.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(String.valueOf(cat.get(i)));
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check = ((CheckBox) v).isChecked();
                    int indice = v.getId();
                    if (check) {
                        categoriasCheck.add((indice + 1));
                    } else {categoriasCheck.remove(categoriasCheck.indexOf(indice + 1));
                    }
                    showResult(categoriasCheck.toString());
                }
            });
            items.add(checkBox);
            categorias.addView(checkBox);
        }
    }

    @Override
    public void swap() {

    }

    @Override
    public void ingresarFoto() {

    }

    @Override
    public void salir() {

    }
}
