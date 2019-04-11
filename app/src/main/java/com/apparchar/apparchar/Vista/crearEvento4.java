package com.apparchar.apparchar.Vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractCreacionEvento;
import com.apparchar.apparchar.Modelo.LugarM;
import com.apparchar.apparchar.Presentador.CreacionEventoPresenter;
import com.apparchar.apparchar.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class crearEvento4 extends AppCompatActivity implements ContractCreacionEvento.ViewCE{

    //Widgets
    Button b4;
    TextView t3;
    Button btnAddFoto;
    ImageView foto;

    //Variables Locales
    ArrayList<String>info,categoriasCheck;
    private static final String TAG = "crearEvento4";
    final int codCarga = 10;
    Bitmap eventoFoto;
    ContractCreacionEvento.PresenterCE presentador;
    ByteArrayOutputStream b = new ByteArrayOutputStream();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearevento_4);

        b4 = (Button) findViewById(R.id.next4);
        t3=(TextView)findViewById(R.id.t4);
        btnAddFoto = findViewById(R.id.photoselect);
        foto = findViewById(R.id.photoEvent);
        t3.setTypeface(Typeface.createFromAsset(crearEvento4.this.getAssets(),"Fonts/Abel_Regular.ttf"));
        info=getIntent().getStringArrayListExtra("info1");
        btnAddFoto = findViewById(R.id.photoselect);
        foto = findViewById(R.id.photoEvent);
        categoriasCheck = getIntent().getStringArrayListExtra("categoriasCheck");
        presentador=  new CreacionEventoPresenter(crearEvento4.this);


    }


    public void pickPhoto(View view) {
        ingresarFoto();
    }


    @Override
    public void showResult(String info) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }

    @Override
    public void mostrarCategorias(ArrayList cat) {

    }

    @Override
    public void swap() {

    }

    public void ingresarFoto() {
        String nombreImagen = "";
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent, "Seleccione la aplicacion"), codCarga);
    }

    @Override
    public void salir() {

    }

    public void next4(View view) {
        Log.d(TAG, info.toString()+"CATEGORIAS : "+categoriasCheck.size());

        //Orden de el arrayList -> Nombre,Descripcion,direccion,latitud,longitud,fecha,horai,horaf
       // String nombre, String horaInicio, String horaFinal, LugarM direccion, String descripcion, ArrayList categorias, String fecha, String nit, byte[] foto, String usuario
       LugarM place = new LugarM();
       place.setDireccion(info.get(2));
       place.setCoordenadaX(Double.parseDouble(info.get(3)));
       place.setCoordenadaY(Double.parseDouble(info.get(4)));


        presentador.crearEvento(info.get(0),info.get(6),info.get(7),place,info.get(1),categoriasCheck,info.get(5),getIntent().getStringExtra("nit"),b.toByteArray(),getIntent().getStringExtra("user"));
        Intent next=new Intent(this,CreacionEvento.class);
        startActivity(next);
    }


    public Bitmap getImage(byte[] byteArray) {
        ArrayList<Bitmap> a = new ArrayList<>();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }

    private byte[] readFile(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Uri pathh = data.getData();
            foto.setImageURI(pathh);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800, 500);
            foto.setLayoutParams(params);
            try {
                eventoFoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pathh);
                eventoFoto.compress(Bitmap.CompressFormat.JPEG, 10, b);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}