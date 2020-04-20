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
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.R;
import com.apparchar.apparchar.Presentador.RegistrarPresenter;

import org.json.JSONException;


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
