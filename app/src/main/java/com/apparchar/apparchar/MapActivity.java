package com.apparchar.apparchar;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apparchar.apparchar.Vista.crearEvento2;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    //TAG utilizado para identificar MapActivity en la ejecucion
    private static final String TAG = "MapActivity";
    //Resultado de los permisos
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    //zoom por defecto en la animacion del mapa
    private static final float ZOOM = 15f;

    //Variables
    private Boolean mLocationPermissionsGranted = false; // lleva el booleano que indica si los permisos ya se han concedido
    private GoogleMap mMap; // Mapa

    private FusedLocationProviderClient mFusedLocationProviderClient; // Objeto de ubicacion
    View mapView; //auxiliar requerido para modificar la posicion del boton de ubicacion

    //Elementos de la vista
    private AutoCompleteTextView textobusqueda; // texto de busqueda
    private ImageButton boton; // boton de buscar

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        textobusqueda = (AutoCompleteTextView) findViewById(R.id.buscart);
        boton = (ImageButton) findViewById(R.id.BtnBuscar);
        getLocationPermission();
    }


    /*
    Este metodo tiene como funcion inicializar el fragmento colocado en el layout map_activity
     */

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapView = mapFragment.getView();

        mapFragment.getMapAsync(MapActivity.this);
    }


    /*
    Metodo utilizado para llevar el control de las busquedas de lugares sobre el mapa
     */


    private void init() {

        Log.d(TAG, "INICIALIZANDO");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geolocalizar(textobusqueda.getText().toString());
            }

        });
    }


    private String coordinatesToAddres(LatLng aux){

        Log.d(TAG, "CoordinatesToAddres");

        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> lista = new ArrayList<>();

        try {
            lista = geocoder.getFromLocation(aux.latitude,aux.longitude,1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }


        if (lista.size() > 0) {
            Address address = lista.get(0);
            String info = address.getAddressLine(0);
            return info;
        } else {
            Toast.makeText(MapActivity.this, "No se pudo encontrar el lugar", Toast.LENGTH_SHORT).show();
            return "";

        }


    }


    /*
    Metodo utilizado para geolocalizar (traducir direcciones a coordenadas) los lugares introducidos por el usuario
     */

    private void geolocalizar(String busqueda) {
        Log.d(TAG, "Geolocalizando");

        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> lista = new ArrayList<>();

        try {
            lista = geocoder.getFromLocationName(busqueda, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }


        if (lista.size() > 0) {
            Address address = lista.get(0);
            //  Toast.makeText(MapActivity.this, "Se encontro el lugar " + address.toString(), Toast.LENGTH_LONG).show();
            // Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //
            String info = address.getAddressLine(0);
            // String info = "Pais : "+address.getCountryName()+" Ciudad :"+address.getAdminArea()+" Localidad :"+address.getLocality();
            AddMarker(new LatLng(address.getLatitude(), address.getLongitude()), info);
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), 20f);


        } else {
            Toast.makeText(MapActivity.this, "No se pudo encontrar el lugar", Toast.LENGTH_SHORT).show();
        }

    }

    private void AddMarker(LatLng point, String info) {
        MarkerOptions mark = new MarkerOptions();
        mark.position(point);
        mark.title("PULSE PARA SELECCIONAR LUGAR");
        mark.snippet(info);
        mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        mark.draggable(true);
        mMap.addMarker(mark);
    }



    /*
      Metodo utilizado para obtener la ubicacion del dispositivo
     */

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

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), ZOOM);

                            Toast.makeText(MapActivity.this, "Su ubicacion", Toast.LENGTH_SHORT).show();

                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
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
                    initMap();

                }

            }


        }

    }


    /*
    Metodo que esta en constante ejecucion el cual contiene el mapa y en el cual se realizan acciones sobre el mismo
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Carga Exitosa", Toast.LENGTH_SHORT).show();
        mMap = googleMap;


        if (mLocationPermissionsGranted) {
            getDeviceLocation();

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

            init();

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                   // Toast.makeText(MapActivity.this, "Coordenadas :" + marker.getPosition().latitude + "," + marker.getPosition().longitude, Toast.LENGTH_LONG).show();
                    ArrayList<String>info = new ArrayList<>();
                    info=getIntent().getStringArrayListExtra("info2");
                    info.add(coordinatesToAddres(marker.getPosition()));
                    info.add(String.valueOf(marker.getPosition().latitude));
                    info.add(String.valueOf(marker.getPosition().longitude));
                    //Retorna a la vista anterior
                    Intent intent = new Intent(MapActivity.this, crearEvento2.class);
                    intent.putStringArrayListExtra("info1",info);
                    intent.putExtra("user",getIntent().getStringExtra("user"));
                    intent.putExtra("nit",getIntent().getStringExtra("nit"));
                    startActivity(intent);
                }

            });

            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {
                    marker.setSnippet("");
                    marker.setTitle("");
                }

                @Override
                public void onMarkerDrag(Marker marker) {
                    marker.setSnippet("");
                    marker.setTitle("");
                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    marker.setTitle("PULSE PARA SELECCIONAR!");
                    marker.setSnippet(coordinatesToAddres(marker.getPosition()));
                }
            });

        }

    }


}
