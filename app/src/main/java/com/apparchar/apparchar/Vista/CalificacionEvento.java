package com.apparchar.apparchar.Vista;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.apparchar.apparchar.AdapterComentarios;
import com.apparchar.apparchar.Contract.ContractCalificacion;
import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Modelo.EventoPKM;
import com.apparchar.apparchar.Presentador.CalificacionPresenter;
import com.apparchar.apparchar.R;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class CalificacionEvento extends AppCompatActivity implements ContractCalificacion.ViewC {
    final int codFoto = 20;
    final int codCarga = 10;
    private final String carpetaRaiz = "Apparchar/";
    private final String rutaImagen = carpetaRaiz + "Apparchar";
    AdapterComentarios adapterComentarios;
    TextView info;
    EditText comentario;
    ImageButton comentar;
    RecyclerView comentarios;
    ImageButton takePhoto;
    RatingBar porcentaje;
    ImageButton recargar;
    String ruta = "";
    boolean star = true;
    private int position;
    private static final Integer DURATION = 2500;
    private Timer timer = null;
    ContractCalificacion.PresenterC calificacionPresenter;
    ArrayList a = new ArrayList();
    TextView nombreEvento, horaInicioEvento, horaFinalEvento, fechaEvento, direccionEvento, descripcionEvento;
    ImageView imagenes;
    String user;
    String horaFinal;
    String horaInicio;
    String fecha;
    ImageView fotoEvento;
    private ImageSwitcher imageSwitcher;

    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final String CAMERA = Manifest.permission.CAMERA;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private Boolean mLocationPermissionsGranted = false;

    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_calificacion);
        comentario = findViewById(R.id.comentario);
        comentar = findViewById(R.id.comentar);
        comentarios = findViewById(R.id.comentarios);
        takePhoto = findViewById(R.id.takePhoto);
        porcentaje = findViewById(R.id.porcentajeStar);
        recargar = findViewById(R.id.reload);
        nombreEvento = findViewById(R.id.nombreEvento);
        descripcionEvento = findViewById(R.id.descripcionEvento);
        direccionEvento = findViewById(R.id.direccionEvento);
        fechaEvento = findViewById(R.id.fechaEvento);
        horaInicioEvento = findViewById(R.id.horaInicioEvento);
        horaFinalEvento = findViewById(R.id.horaFinalEvento);
        fotoEvento = findViewById(R.id.fotoEvento);
        // carouselPicker = findViewById(R.id.CarouselPicker);
        imageSwitcher = findViewById(R.id.imageSwitcher);
        adapterComentarios = new AdapterComentarios(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        comentarios.setLayoutManager(l);
        comentarios.setAdapter(adapterComentarios);
        user = getIntent().getExtras().getString("user");
        showResult(user);
        //System.out.println("USUARIO"+user);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                return new ImageView(CalificacionEvento.this);
            }
        });
        // Set animations
        // http://danielme.com/2013/08/18/diseno-android-transiciones-entre-activities/
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);


        horaFinal = getIntent().getExtras().getString("final");
        horaInicio = getIntent().getExtras().getString("inicio");
        fecha = getIntent().getExtras().getString("fecha");
        calificacionPresenter = new CalificacionPresenter(this);
        int id = getIntent().getExtras().getInt("id");
        calificacionPresenter.obtenerInfoEvento(id, horaInicio, horaFinal, fecha);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalificacionM calificacionM = new CalificacionM();
                ClienteM clienteM = new ClienteM();
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
                clienteM.setUsuario(user);
                calificacionM.setUsuariocliente(clienteM);
                calificacionM.setComentario(mensaje);
                calificacionM.setHora(time);
                adapterComentarios.addC(calificacionM);
                int id = getIntent().getExtras().getInt("id");
                comentario.setText("");
                calificacionPresenter.crearComentario(mensaje, time, user, id, fechac, horaInicio, horaFinal, fecha);

            }
        });
        recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getExtras().getInt("id");
                EventoPKM eventoPKM = new EventoPKM();
                eventoPKM.setFecha(fecha);
                eventoPKM.setHoraFinal(horaFinal);
                eventoPKM.setHoraInicio(horaInicio);
                eventoPKM.setIdevento(id);
                calificacionPresenter.actualizar(eventoPKM);
            }
        });
        adapterComentarios.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScroll();
            }
        });
        porcentaje.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float pcr = porcentaje.getRating();
                if (star) {
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
                    porcentaje.setEnabled(false);
                    calificacionPresenter.crearCalificacion(pcr, time, user, id, fechac, horaInicio, horaFinal, fecha);
                }


                star = true;


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
                        bitmap1.compress(Bitmap.CompressFormat.JPEG, 10, b);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    int id = getIntent().getExtras().getInt("id");
                    calificacionPresenter.crearMultimedia(b.toByteArray(), time, user, id, fechac, horaInicio, horaFinal, fecha);

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
                    calificacionPresenter.crearMultimedia(b2.toByteArray(), time2, user, id, fechac2, horaInicio, horaFinal, fecha);

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
            // showResult("No hay fotos");
        } else {
            if (timer != null) {
                timer.cancel();
            }
            position = 0;
            startSlider(fotos);
            /*ArrayList<Bitmap> imagenesBitmap= new ArrayList<>();
            for (int i=0; i<fotos.size();i++){
                imagenesBitmap.add(getImage(fotos.get(i)));
            }
            llenarCarousel(imagenesBitmap);*/
        }


    }

   /* private void llenarCarousel(ArrayList<Bitmap> imagenesBitmap) {

        List<CarouselPicker.PickerItem> itemsImages = new ArrayList<>();
        for (int i=0;i<imagenesBitmap.size();i++){
            Drawable d= new BitmapDrawable(getResources(),imagenesBitmap.get(i));
           // itemsImages.add(new CarouselPicker.DrawableItem(d.getAlpha()));
           // itemsImages.add(new CarouselPicker.DrawableItem(new BitmapDrawable(getResources(),imagenesBitmap.get(i))));
        }
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(imagenesBitmap.get(0));


        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.empresa));
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.picture_default));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this,itemsImages,0);
        carouselPicker.setAdapter(imageAdapter);

    }*/

    private void startSlider(final ArrayList<byte[]> fotos) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                // avoid exception:
                // "Only the original thread that created a view hierarchy can touch its views"
                runOnUiThread(new Runnable() {
                    public void run() {
                        Drawable d = new BitmapDrawable(getResources(), getImage(fotos.get(position)));
                        imageSwitcher.setImageDrawable(d);
                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(800, 500);
                        imageSwitcher.setLayoutParams(params1);

                        position++;
                        if (position == fotos.size()) {
                            position = 0;
                        }
                    }
                });
            }

        }, 0, DURATION);


    }

    @Override
    public void mostrarComentarios(ArrayList<CalificacionM> com) {
        adapterComentarios.removeC();
        if (com.isEmpty()) {

        } else {
            for (int i = 0; i < com.size(); i++) {
                adapterComentarios.addC(com.get(i));
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
            star = false;
            porcentaje.setRating(Float.valueOf(s));


        }
    }

    @Override
    public void mostrarEvento(EventoM evento) {
        String catxd = "";
        ArrayList lista = new ArrayList();
        lista = (ArrayList) evento.getCategoriaCollection();
        nombreEvento.setText(evento.getNombre());
        horaFinalEvento.setText(evento.getEventoPK().getHoraFinal());
        horaInicioEvento.setText(evento.getEventoPK().getHoraInicio());
        fechaEvento.setText(evento.getEventoPK().getFecha());
        direccionEvento.setText(evento.getDireccion().getDireccion());
        descripcionEvento.setText(evento.getDescripcion());
        if (evento.getFoto() != null)
            fotoEvento.setImageBitmap(getImage(evento.getFoto()));
       // TableRow.LayoutParams params1 = new TableRow.LayoutParams(178, 125);
        //fotoEvento.setLayoutParams(params1);

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

    private void setScroll() {
        comentarios.scrollToPosition(adapterComentarios.getItemCount() - 1);
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

      /*
      Metodo utilizado para manejar las respuestas a la solicitud de los permisos del usuario
       */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }

                    }

                    mLocationPermissionsGranted = false;
                    //INICIAR EL MAPA

                }

            }


        }

    }
}


