package com.apparchar.apparchar.Vista;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import com.apparchar.apparchar.Presentador.CalificacionPresenter;
import com.apparchar.apparchar.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;


public class CalificacionEvento extends AppCompatActivity implements ContractCalificacion.ViewC{
    final int codFoto = 20;
    final int codCarga = 10;
    final String s3Bucket="https://apparchar.s3.amazonaws.com/";
    private final String carpetaRaiz = "Apparchar/";
    private final String rutaImagen = carpetaRaiz + "Apparchar";
    AdapterComentarios adapterComentarios;
    TextView info;
    EditText comentario;
    ImageButton comentar;
    RecyclerView comentarios;
    ImageButton takePhoto;
    RatingBar porcentaje;
    RatingBar viewPorcentaje;
    File fotobytes;
    ImageButton recargar;
    String ruta = "";
    boolean star = true;
    private int position;
    private static final Integer DURATION = 2500;
    private Timer timer = null;
    ContractCalificacion.PresenterC calificacionPresenter;
    ArrayList a = new ArrayList();
    TextView nombreEvento, horaInicioEvento, fechaEvento, direccionEvento, descripcionEvento;
    ImageView imagenes;
    String idUser="";

    int idEvento= -1;
    ImageView fotoEvento;
    CarouselView carouselView;
    TextView promNumber;
    //private ImageSwitcher imageSwitcher;
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
        SharedPreferences sharedPreferences= getSharedPreferences("apparchardata", Context.MODE_PRIVATE);
        idUser=sharedPreferences.getString("user","null");
        Intent intent= getIntent();
        idEvento= intent.getIntExtra("id",-1);
        Log.i("i","idEvento-->"+idEvento);
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
        horaInicioEvento = findViewById(R.id.horaEvento);
        promNumber= findViewById(R.id.promedio);
        viewPorcentaje= findViewById(R.id.viewStar);
        fotoEvento = findViewById(R.id.fotoEvento);
        carouselView = findViewById(R.id.carouselView);
        adapterComentarios = new AdapterComentarios(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        comentarios.setLayoutManager(l);
        comentarios.setAdapter(adapterComentarios);

        calificacionPresenter = new CalificacionPresenter(this);

        calificacionPresenter.getEvento(idEvento);
        calificacionPresenter.actualizar(idEvento);
        comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalificacionM calificacionM = new CalificacionM();
                ClienteM clienteM = new ClienteM();
                String mensaje = comentario.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new java.util.Date());
                String year = currentDateandTime.substring(0, 4);
                String month = currentDateandTime.substring(4, 6);
                String day = currentDateandTime.substring(6, 8);
                String fechac = year+"-"+month+"-"+day;
                String hour = currentDateandTime.substring(9, 11);
                String minutos = currentDateandTime.substring(11, 13);
                String time = hour + ":" + minutos;

                //Time time = new Time(Integer.parseInt(hour),Integer.parseInt(minutos),0);
                //Date date = new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                comentario.setText("");
                calificacionPresenter.crearComentario(mensaje, time, idUser, fechac);

            }
        });
        recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calificacionPresenter.actualizar(idEvento);
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
                    String currentDateandTime = sdf.format(new java.util.Date());
                    String year = currentDateandTime.substring(0, 4);
                    String month = currentDateandTime.substring(4, 6);
                    String day = currentDateandTime.substring(6, 8);
                    //String fechac = day + "/" + month + "/" + year;
                    String hour = currentDateandTime.substring(9, 11);
                    String minutos = currentDateandTime.substring(11, 13);
                    String time = hour + ":" + minutos;
                    String fechac = year+"-"+month+"-"+day;
                    //int id = getIntent().getExtras().getInt("id");
                    //Time time = new Time(Integer.parseInt(hour),Integer.parseInt(minutos),0);
                    //Date date = new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                    porcentaje.setEnabled(false);
                    calificacionPresenter.crearCalificacion(pcr, time, idUser, fechac);
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
                        /*File fileImagen = new File(Environment.getExternalStorageDirectory(), rutaImagen);
                        boolean iscreada = fileImagen.exists();
                        if (iscreada == false) {
                            iscreada = fileImagen.mkdirs();
                        }
                        if (iscreada) {
                            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
                        }
                        ruta = Environment.getExternalStorageDirectory() + File.separator + rutaImagen + File.separator + nombreImagen;
                        File imagen = new File(ruta);*/
                        Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        // in.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
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
                    String currentDateandTime = sdf.format(new java.util.Date());
                    String year = currentDateandTime.substring(0, 4);
                    String month = currentDateandTime.substring(4, 6);
                    String day = currentDateandTime.substring(6, 8);
                    //String fechac = day + "/" + month + "/" + year;
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
                    String filePath = getRealPathFromURIPath(pathh.normalizeScheme(), CalificacionEvento.this);
                    File file = new File(filePath);

                    fotobytes = file;

                    //Time time = new Time(Integer.parseInt(hour),Integer.parseInt(minutos),0);
                    //Date date = new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
                    String fechac = year+"-"+month+"-"+day;
                    //int id = getIntent().getExtras().getInt("id");
                    calificacionPresenter.crearMultimedia(fotobytes, time, idUser,fechac);

                    break;
                case codFoto:


                    ByteArrayOutputStream b2 = new ByteArrayOutputStream();
                    /*MediaScannerConnection.scanFile(this, new String[]{ruta}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "Path: " + path);
                        }
                    });*/

                    //Bitmap bitmap = BitmapFactory.decodeFile(ruta);
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, b2);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime2 = sdf2.format(new java.util.Date());
                    String year2 = currentDateandTime2.substring(0, 4);
                    String month2 = currentDateandTime2.substring(4, 6);
                    String day2 = currentDateandTime2.substring(6, 8);
                    String fechac2 = year2+"-"+month2+"-"+day2;
                    String hour2 = currentDateandTime2.substring(9, 11);
                    String minutos2 = currentDateandTime2.substring(11, 13);
                    String time2 = hour2 + ":" + minutos2;
                    //id = getIntent().getExtras().getInt("id");

                    //String filePath = getRealPathFromURIPath(pathh.normalizeScheme(), Cliente.this);
                    //File file = new File(filePath);

                    //fotobytes = file;
                    //Time time2 = new Time(Integer.parseInt(hour2),Integer.parseInt(minutos2),0);
                    //Date date2 = new Date(Integer.parseInt(year2),Integer.parseInt(month2),Integer.parseInt(day2));
                    calificacionPresenter.crearMultimedia(fotobytes, time2, idUser, fechac2);

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
    public void mostrarFotos(final ArrayList<String> fotos) {
        final Context c = this;
        if (fotos.isEmpty()) {
            // showResult("No hay fotos");
        } else {


            carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    String url=s3Bucket+fotos.get(position)+".jpg";
                    Log.i("fotosxd",url);
                    Picasso.with(c).load(url).fit().into(imageView);

                }
            });
            carouselView.setPageCount(fotos.size());

        }


    }


    @Override
    public void mostrarComentarios(ArrayList<CalificacionM> com, ArrayList<ClienteM> clientes) {
        adapterComentarios.removeC();
        if (com.isEmpty()) {

        } else {
            for (int i = 0; i < com.size(); i++) {
                ClienteM clienteM= new ClienteM();
                clienteM=getCliente(clientes,com.get(i).getUsuariocliente());
                adapterComentarios.addC(com.get(i),clienteM);
            }
        }

    }

    private ClienteM getCliente(ArrayList<ClienteM> clientes, String user) {
        int index = 0;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getUsuario().equals(user)) {
                index=i;
            }
        }

        return clientes.get(index);
    }

    @Override
    public void mostrarCalificacion(ArrayList<Double> calificacion) {
        if (calificacion.isEmpty()) {
        } else {
            double suma = 0;
            for (int i = 0; i < calificacion.size(); i++) {
                suma = suma + calificacion.get(i);
            }
            double promedio = (suma / calificacion.size());
            String s = String.valueOf(promedio);
            star = false;
            viewPorcentaje.setRating(Float.valueOf(s));
            porcentaje.setRating(Float.valueOf(s));
            promNumber.setText(s);



        }
    }


    @Override
    public void mostrarEvento(EventoM evento) {
        String catxd = "";
        ArrayList lista = new ArrayList();
        //lista = (ArrayList) evento.getCategoriaCollection();
        nombreEvento.setText(evento.getNombre());
        String horaE= evento.getHora_inicio()+"-"+evento.getHora_final();
        horaInicioEvento.setText(horaE);
        fechaEvento.setText(evento.getFecha());
        direccionEvento.setText(evento.getLugar());
        descripcionEvento.setText(evento.getDescripcion());
        if (evento.getFoto() != null) {
            Picasso.with(this).load(evento.getFoto()).fit().into(fotoEvento);
        }
        //fotoEvento.setImageBitmap(getImage(evento.getFoto()));
        // TableRow.LayoutParams params1 = new TableRow.LayoutParams(178, 125);
        //fotoEvento.setLayoutParams(params1);

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

   /* public static Bitmap getBitmap(String url) {
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
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
