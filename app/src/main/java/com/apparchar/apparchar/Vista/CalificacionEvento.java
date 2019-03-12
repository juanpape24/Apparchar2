package com.apparchar.apparchar.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.apparchar.apparchar.Presentador.CalificacionPresenter;
import com.apparchar.apparchar.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalificacionEvento extends AppCompatActivity {
    TextView info;
    EditText comentario;
    ImageButton comentar;
    LinearLayout comentarios;
    ImageButton takePhoto;
    LinearLayout imagenes;
    RatingBar porcentaje;
    Button submit;
    final int codFoto = 20;
    final int codCarga = 10;
    private final String carpetaRaiz = "Apparchar/";
    private final String rutaImagen = carpetaRaiz + "misFotos";
    String ruta = "";
    CalificacionPresenter calificacionPresenter;
    ArrayList a=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_calificacion);
        info = (TextView) findViewById(R.id.infoEvento);
        comentario = (EditText) findViewById(R.id.comentario);
        comentar = (ImageButton) findViewById(R.id.comentar);
        comentarios = (LinearLayout) findViewById(R.id.comentarios);
        takePhoto = (ImageButton) findViewById(R.id.takePhoto);
        imagenes = (LinearLayout) findViewById(R.id.imagenes);
        porcentaje = (RatingBar) findViewById(R.id.porcentajeStar);
        submit = (Button) findViewById(R.id.submit);
        calificacionPresenter=new CalificacionPresenter(new View(this));
        a = (ArrayList) getIntent().getExtras().get("datos");
        String inf1 = "IdEvento: " + a.get(0) + "\tNombre: " + a.get(1) + "\tFecha: " + a.get(2) + "\nHora de Inicio: " + a.get(3) + "\tHora Final: " + a.get(4) + "\tDireccion: " + a.get(5) + "\nDescripcion: " + a.get(6);
        info.setText(inf1);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView c = new TextView(CalificacionEvento.this);
                String mensaje = comentario.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
                String year=currentDateandTime.substring(0,4);
                String month=currentDateandTime.substring(4,6);
                String day=currentDateandTime.substring(6,8);
                String fechac=day+"/"+month+"/"+year;
                String hour=currentDateandTime.substring(9,11);
                String minutos=currentDateandTime.substring(11,13);
                String time=hour+":"+minutos;
                String h=fechac+" "+time;
                c.setText(mensaje+"                       "+h);






                comentarios.addView(c);
                comentario.setText("");
                calificacionPresenter.CrearCalificacion(-1,mensaje,time,fechac,String.valueOf(a.get(3)),String.valueOf(a.get(4)),String.valueOf(a.get(2)),null,1,(int)a.get(0));

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pcr=porcentaje.getRating();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
                String year=currentDateandTime.substring(0,4);
                String month=currentDateandTime.substring(4,6);
                String day=currentDateandTime.substring(6,8);
                String fechac=day+"/"+month+"/"+year;
                String hour=currentDateandTime.substring(9,11);
                String minutos=currentDateandTime.substring(11,13);
                String time=hour+":"+minutos;

                calificacionPresenter.CrearCalificacion(pcr,"",time,fechac,String.valueOf(a.get(3)),String.valueOf(a.get(4)),String.valueOf(a.get(2)),null,1,(int)a.get(0));
            }

        });

    }


    public void take(View view) {
        cargarImagen();
    }

    private byte[] readFile(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void cargarImagen() {
        final CharSequence[] opc = {"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOpc = new AlertDialog.Builder(CalificacionEvento.this);
        alertOpc.setTitle("Seleccione una opcion");
        alertOpc.setItems(opc, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String nombreImagen = "";
                if (opc[i].equals("Tomar Foto")) {
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


                } else {
                    if (opc[i].equals("Cargar Imagen")) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), codCarga);


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
                    int alto = 180;
                    Uri pathh = data.getData();
                    ImageView img = new ImageView(this);
                    img.setImageURI(pathh);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800, 500);
                    img.setLayoutParams(params);
                    imagenes.addView(img);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime = sdf.format(new Date());
                    String year=currentDateandTime.substring(0,4);
                    String month=currentDateandTime.substring(4,6);
                    String day=currentDateandTime.substring(6,8);
                    String fechac=day+"/"+month+"/"+year;
                    String hour=currentDateandTime.substring(9,11);
                    String minutos=currentDateandTime.substring(11,13);
                    String time=hour+":"+minutos;
                    Bitmap bitmap1 = null;
                    try {
                        bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pathh);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    calificacionPresenter.CrearCalificacion(-1,"",time,fechac,String.valueOf(a.get(3)),String.valueOf(a.get(4)),String.valueOf(a.get(2)),readFile(bitmap1),1,(int)a.get(0));

                    break;
                case codFoto:
                    MediaScannerConnection.scanFile(this, new String[]{ruta}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "Path: " + path);
                        }
                    });

                    Bitmap bitmap = BitmapFactory.decodeFile(ruta);
                    ImageView picture = new ImageView(this);
                    picture.setImageBitmap(bitmap);
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(800, 500);
                    picture.setLayoutParams(params1);
                    imagenes.addView(picture);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime2 = sdf2.format(new Date());
                    String year2=currentDateandTime2.substring(0,4);
                    String month2=currentDateandTime2.substring(4,6);
                    String day2=currentDateandTime2.substring(6,8);
                    String fechac2=day2+"/"+month2+"/"+year2;
                    String hour2=currentDateandTime2.substring(9,11);
                    String minutos2=currentDateandTime2.substring(11,13);
                    String time2=hour2+":"+minutos2;

                    calificacionPresenter.CrearCalificacion(-1,"",time2,fechac2,String.valueOf(a.get(3)),String.valueOf(a.get(4)),String.valueOf(a.get(2)),readFile(bitmap),1,(int)a.get(0));

                    break;
            }


        }
    }
}

