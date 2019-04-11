package com.apparchar.apparchar;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Conexion.MyLoopjTask;
import com.apparchar.apparchar.Conexion.OnLoopjCompleted;
import com.apparchar.apparchar.Modelo.EventoM;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RealTimeActivity extends AppCompatActivity implements OnMapReadyCallback , OnLoopjCompleted {

    private static final String TAG = "RealTimeActivity";
    private GoogleMap mMap; // Mapa
    View mapView;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    //zoom por defecto en la animacion del mapa
    private static final float ZOOM = 20f;
    private Boolean mLocationPermissionsGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    //Widgets
    private Button boton;
    private TextView titulo;


    List<EventoM> lista ;
    RequestParams params;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realtime_activity);
        titulo = (TextView) findViewById(R.id.RealTimeTittle);
        titulo.setTypeface(Typeface.createFromAsset(RealTimeActivity.this.getAssets(),"Fonts/Abel_Regular.ttf"));
        boton = (Button) findViewById(R.id.Parchame);
        boton.setTypeface(Typeface.createFromAsset(RealTimeActivity.this.getAssets(),"Fonts/Abel_Regular.ttf"));
        getLocationPermission();
        params=new RequestParams();
        lista = new ArrayList<>();

        params = new RequestParams();
        params.put("realtime", "gg");
        String nameServlet = "SERVEvento";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet,getApplicationContext(),RealTimeActivity.this);
        loopjTask.executeLoopjCall();
    }



    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        mapView = mapFragment.getView();
        Log.d(TAG,"SE INICIO EL MAPA");
        mapFragment.getMapAsync(RealTimeActivity.this);
    }



    private LocationManager locationManager ;

    protected boolean isLocationEnabled(){
        String le = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(le);
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            return false;
        } else {
            return true;
        }
    }




    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            AddMarker(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), "ESTAS AQUI", "", true);
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), ZOOM);
                            Toast.makeText(RealTimeActivity.this, "Su ubicacion", Toast.LENGTH_SHORT).show();

                            /*
                            Conexion con el server
                             */

                        } else {
                            Log.d(TAG, "onComplete: current location is null");

                            Toast.makeText(RealTimeActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }

    }


    /*
    Metodo utilizado para controlar y mover la camara dentro del mapa
     */
    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "Moviendo la camara");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

    }


    /*
   Metodo utilizado para pedir permisos requeridos por el modulo
    */
    private void getLocationPermission() {

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }

        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


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
                    initMap();

                }

            }


        }

    }



    private void Parchame(){

        /*
           Aqui va la conexion al server
         */
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CleanInterface(); //Limpia la interfaz
               // getDeviceLocation();
//Request
                Log.e(TAG,"LISTA :"+lista.size());
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lista.get(0).getDireccion().getCoordenadaX(),lista.get(0).getDireccion().getCoordenadaY()),15f));
                for (int i = 0;i<lista.size();i++){
                    EventoM event = lista.get(i);
                    Double x =event.getDireccion().getCoordenadaX();
                    Double y = event.getDireccion().getCoordenadaY();
                    Log.e(TAG,"COORD :"+x+" "+y);
                    AddMarker(new LatLng(x,y),event.getNombre(),event.getDescripcion(),false);
                }


            }
        });


    }


    private LatLng Geocoder(String direccion){
        Log.d(TAG, "Geolocalizando");

        Geocoder geocoder = new Geocoder(RealTimeActivity.this);
        List<Address> lista = new ArrayList<>();

        try {
            lista = geocoder.getFromLocationName(direccion, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }


        if (lista.size() > 0) {
            Address address = lista.get(0);
            return new LatLng(address.getLatitude(),address.getLongitude());

        } else {
            Toast.makeText(RealTimeActivity.this, "No se pudo encontrar el lugar", Toast.LENGTH_SHORT).show();
            return new LatLng(0,0);
        }


    }


    private void AddMarker(LatLng point, String tittle ,String info , boolean isMyLocation) {

        if(isMyLocation) {
            MarkerOptions mark = new MarkerOptions();
            mark.position(point);
            mark.title(tittle);
            mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            mMap.addMarker(mark);
        }else{
            MarkerOptions mark = new MarkerOptions();
            mark.position(point);
            mark.title(tittle);
            mark.snippet(info);
            mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mMap.addMarker(mark);
        }
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {

        Toast.makeText(this, "Carga Exitosa", Toast.LENGTH_SHORT).show();
        //moveCamera(new LatLng(4.6097102,-74.081749),15f);
        mMap = googleMap;


        if (mLocationPermissionsGranted) {
            // getDeviceLocation();
            Parchame();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);

            //VISUALIZAR EL BOTON DE UBICACION EN LA ESQUINA INFERIOR DERECHA
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            // position on right bottom
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.setMargins(0, 0, 30, 30);
            //mMap.getUiSettings().setMyLocationButtonEnabled(false);


        }


    }


    private void CleanInterface(){
        mMap.clear();
    }


    @Override
    public void taskCompleted(String results) {

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject) jsonParser.parse(results);
        JsonElement c = jo.get("respuesta");
        String r = c.getAsString();
        Type listType = new TypeToken<ArrayList<EventoM>>() {
        }.getType();
        Gson a = new Gson();
        ArrayList<EventoM> arrayList = a.fromJson(r, listType);

        for (int i = 0; i < arrayList.size(); i++) {
            lista.add(arrayList.get(i));
        }
    }
}
