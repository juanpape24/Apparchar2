package com.apparchar.apparchar.Vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractCalificacion;
import com.apparchar.apparchar.Presentador.CalificacionPresenter;
import com.apparchar.apparchar.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalificacionEvento extends AppCompatActivity implements ContractCalificacion.ViewC {
    final int codFoto = 20;
    final int codCarga = 10;
    private final String carpetaRaiz = "Apparchar/";
    private final String rutaImagen = carpetaRaiz + "Apparchar";
    TextView info;
    EditText comentario;
    ImageButton comentar;
    LinearLayout comentarios;
    ImageButton takePhoto;
    RatingBar porcentaje;
    Button submit;
    ImageButton recargar;
    String ruta = "";
    ContractCalificacion.PresenterC calificacionPresenter;
    ArrayList a = new ArrayList();

    ImageView imagenes;
    String user;
    String horaFinal;
    String horaInicio;
    String fecha;

    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_calificacion);
        info = findViewById(R.id.infoEvento);
        comentario = findViewById(R.id.comentario);
        comentar = findViewById(R.id.comentar);
        comentarios = findViewById(R.id.comentarios);
        takePhoto = findViewById(R.id.takePhoto);
        porcentaje = findViewById(R.id.porcentajeStar);
        submit = findViewById(R.id.submit);
        recargar = findViewById(R.id.reload);
        imagenes = findViewById(R.id.multimedia);
        user = getIntent().getExtras().getString("user");

        horaFinal = getIntent().getExtras().getString("final");
        horaInicio = getIntent().getExtras().getString("inicio");
        fecha = getIntent().getExtras().getString("fecha");
        calificacionPresenter = new CalificacionPresenter(this);
        // info.setText(calificacionPresenter.obtenerInfoEvento());      //Muestra la informacion del evento en un TextView
        int id = getIntent().getExtras().getInt("id");
        calificacionPresenter.actualizar(id);  //Coloca toda la informacion inicial, tanto comentarios, multimedia y calificaciones
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView c = new TextView(CalificacionEvento.this);
                String mensaje = comentario.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
                String year = currentDateandTime.substring(0, 4);
                String month = currentDateandTime.substring(4, 6);
                String day = currentDateandTime.substring(6, 8);
                String fechac = day + "/" + month + "/" + year;
                String hour = currentDateandTime.substring(9, 11);
                String minutos = currentDateandTime.substring(11, 13);
                String time = hour + ":" + minutos;
                String h = fechac + " " + time;
                c.setText(mensaje + "                       " + h);
                comentarios.addView(c);
                comentario.setText("");
                int id = getIntent().getExtras().getInt("id");
                calificacionPresenter.crearComentario(mensaje, time, user, id, fechac, horaInicio, horaFinal, fecha);

            }
        });
        recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getExtras().getInt("id");
                calificacionPresenter.actualizar(id);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pcr = porcentaje.getRating();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
                String year = currentDateandTime.substring(0, 4);
                String month = currentDateandTime.substring(4, 6);
                String day = currentDateandTime.substring(6, 8);
                String fechac = day + "/" + month + "/" + year;
                String hour = currentDateandTime.substring(9, 11);
                String minutos = currentDateandTime.substring(11, 13);
                String time = hour + ":" + minutos;
                int id = getIntent().getExtras().getInt("id");
                calificacionPresenter.crearCalificacion(pcr, time, user, id, fechac, horaInicio, horaFinal, fecha);


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
                        startActivityForResult(Intent.createChooser(intent, "Seleccione la aplicacion"), codCarga);


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
                    Uri pathh = data.getData();
                    ImageView img = new ImageView(this);
                    img.setImageURI(pathh);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800, 500);
                    img.setLayoutParams(params);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime = sdf.format(new Date());
                    String year = currentDateandTime.substring(0, 4);
                    String month = currentDateandTime.substring(4, 6);
                    String day = currentDateandTime.substring(6, 8);
                    String fechac = day + "/" + month + "/" + year;
                    String hour = currentDateandTime.substring(9, 11);
                    String minutos = currentDateandTime.substring(11, 13);
                    String time = hour + ":" + minutos;
                    Bitmap bitmap1 = null;
                    try {
                        bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pathh);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    int id = getIntent().getExtras().getInt("id");
                    calificacionPresenter.crearMultimedia(readFile(bitmap1), time, user, id, fechac, horaInicio, horaFinal, fecha);

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
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime2 = sdf2.format(new Date());
                    String year2 = currentDateandTime2.substring(0, 4);
                    String month2 = currentDateandTime2.substring(4, 6);
                    String day2 = currentDateandTime2.substring(6, 8);
                    String fechac2 = day2 + "/" + month2 + "/" + year2;
                    String hour2 = currentDateandTime2.substring(9, 11);
                    String minutos2 = currentDateandTime2.substring(11, 13);
                    String time2 = hour2 + ":" + minutos2;
                    id = getIntent().getExtras().getInt("id");
                    calificacionPresenter.crearMultimedia(readFile(bitmap), time2, user, id, fechac2, horaInicio, horaFinal, fecha);

                    break;
            }


        }
    }

    @Override
    public void showResult(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void swap() {

    }

    @Override
    public void mostrarFotos(final ArrayList<byte[]> fotos) {
        if (fotos.isEmpty()) {

        } else {
            showResult(String.valueOf(fotos.size()));
            CountDownTimer count;
            if (imagenes != null) {

                count = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        imagenes.setImageBitmap(getImage(fotos.get(j)));
                        j++;
                        if (j == fotos.size() - 1) j = 0;
                        start();
                    }
                }.start();


            }
        }


    }

    @Override
    public void mostrarComentarios(ArrayList<String> com) {
        comentarios.removeAllViewsInLayout();
        if (com.isEmpty()) {

        } else {
            for (int i = 0; i < com.size(); i++) {
                TextView c = new TextView(CalificacionEvento.this);
                c.setText(com.get(i));
                comentarios.addView(c);
            }
        }

    }

    @Override
    public void mostrarCalificacion(ArrayList<Double> calificacion) {
        if (calificacion.isEmpty()) {
        } else {
            double suma = 0;
            for (int i = 0; i < calificacion.size(); i++) {
                suma = suma + calificacion.get(i);
            }
            double promedio = suma / calificacion.size();
            String s = String.valueOf(promedio);
            porcentaje.setRating(Float.valueOf(s));
        }
    }

    @Override
    public String getIdEvento() {
        String idEvento = getIntent().getExtras().getString("idEvento");
        return idEvento;
    }

    public Bitmap getImage(byte[] byteArray) {
        ArrayList<Bitmap> a = new ArrayList<>();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bmp;
    }


}


