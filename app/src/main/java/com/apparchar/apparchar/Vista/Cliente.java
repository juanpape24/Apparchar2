package com.apparchar.apparchar.Vista;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.Presentador.RegistrarPresenter;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cliente extends AppCompatActivity implements ContractClient.View {
    private TextView nombre, apellido, edad, email, cel, user, pass, pass2;
    private EditText nombre1, apellido1, edad1, email1, cel1, user1, pass1, pass3;
    private boolean fotoExist=false;
    private ContractClient.Presenter presenter;
    final int codFoto = 20;
    final int codCarga = 10;
    private final String carpetaRaiz = "Apparchar/";
    private final String rutaImagen = carpetaRaiz + "Apparchar";
    private CircleImageView foto;
    private Button cargarFoto;
    private byte[] fotobytes;
    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;
    String ruta = "";
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
        foto = findViewById(R.id.fotoPerfil);
        cargarFoto=findViewById(R.id.cargarFoto);
        presenter = new RegistrarPresenter(this);

    }
    private void cargarImagen() {
        final CharSequence[] opc = {"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpc = new AlertDialog.Builder(Cliente.this);
        alertOpc.setTitle("Seleccione una opcion");
        alertOpc.setItems(opc, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String nombreImagen = "";
                if (opc[i].equals("Tomar Foto")) {
                    if (mLocationPermissionsGranted) {
                        File fileImagen = new File(Environment.getExternalStorageDirectory(), rutaImagen);
                        boolean iscreada = fileImagen.exists();
                        if (iscreada == false) {
                            iscreada = fileImagen.mkdirs();
                        }
                        if (iscreada) {
                            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
                        }
                        ruta = Environment.getExternalStorageDirectory() + File.separator + rutaImagen + File.separator + nombreImagen;
                        File imagen = new File(ruta);
                        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
                        startActivityForResult(in, codFoto);
                    } else getPermission();

                } else {
                    if (opc[i].equals("Cargar Imagen")) {
                        if (mLocationPermissionsGranted) {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(Intent.createChooser(intent, "Seleccione la aplicacion"), codCarga);

                        } else getPermission();
                    } else {
                        dialog.dismiss();
                    }

                }
            }
        });
        alertOpc.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case codCarga:
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    Uri pathh = data.getData();
                    Bitmap bitmap1 = null;
                    try {
                        bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pathh);
                        bitmap1.compress(Bitmap.CompressFormat.JPEG, 10, b);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fotobytes=b.toByteArray();
                    foto.setImageBitmap(bitmap1);
                    fotoExist=true;

                    break;
                case codFoto:
                    ByteArrayOutputStream b2 = new ByteArrayOutputStream();
                    MediaScannerConnection.scanFile(this, new String[]{ruta}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "Path: " + path);
                        }
                    });

                    Bitmap bitmap = BitmapFactory.decodeFile(ruta);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, b2);

                    fotobytes=b2.toByteArray();
                    foto.setImageBitmap(bitmap);
                    fotoExist=true;
                    break;
            }


        }
    }

    public void upload(View view){
        cargarImagen();
    }
    public void registrar(View view) throws JSONException {
        if (fotoExist){
            presenter.enviar(nombre1.getText().toString(), apellido1.getText().toString(), edad1.getText().toString(), email1.getText().toString(), cel1.getText().toString(), user1.getText().toString(), pass1.getText().toString(), pass3.getText().toString(),fotobytes);
        } else showResult("Ingrese una foto por favor");



    }

    private void getPermission() {

        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                if (ContextCompat.checkSelfPermission(this.getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionsGranted = true;

                } else {
                    ActivityCompat.requestPermissions(this, permissions, 1888);
                }
            } else {
                ActivityCompat.requestPermissions(this, permissions, 20);
            }

        } else {
            ActivityCompat.requestPermissions(this, permissions, 10);
        }
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