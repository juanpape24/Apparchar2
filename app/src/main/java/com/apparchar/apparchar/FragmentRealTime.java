package com.apparchar.apparchar;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentRealTime.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentRealTime#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRealTime extends Fragment implements OnMapReadyCallback, OnLoopjCompleted {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "RealTimeActivity";
    private GoogleMap mMap; // Mapa

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

    private View vista;
    List<EventoM> lista ;
    RequestParams params;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentRealTime() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRealTime.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRealTime newInstance(String param1, String param2) {
        FragmentRealTime fragment = new FragmentRealTime();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista=inflater.inflate(R.layout.fragment_fragment_real_time, container, false);
        titulo = (TextView) vista.findViewById(R.id.RealTimeTittle);
        titulo.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"Fonts/Abel_Regular.ttf"));
        boton = (Button) vista.findViewById(R.id.Parchame);
        boton.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"Fonts/Abel_Regular.ttf"));
        // Inflate the layout for this fragment
        return vista;
    }
    @Override
    public void onResume(){

        super.onResume();
        getLocationPermission();
        params=new RequestParams();
        lista = new ArrayList<>();

        params = new RequestParams();
        params.put("realtime", "gg");
        String nameServlet = "SERVEvento";
        MyLoopjTask loopjTask = new MyLoopjTask(params, nameServlet,getActivity(),this);
        loopjTask.executeLoopjCall();
    }
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map2);;
        Log.d(TAG,"SE INICIO EL MAPA");
        mapFragment.getMapAsync(this);
    }



    private LocationManager locationManager ;

    protected boolean isLocationEnabled(){
        String le = Context.LOCATION_SERVICE;
        locationManager =(LocationManager) getActivity().getSystemService(le);
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            return false;
        } else {
            return true;
        }
    }




    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                           // AddMarker(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), "ESTAS AQUI", "", true);
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 20f);
                            //Toast.makeText(getActivity(), "Parchado", Toast.LENGTH_SHORT).show();

                            /*
                            Conexion con el server
                             */

                        } else {
                            Log.d(TAG, "onComplete: current location is null");

                            Toast.makeText(getActivity(), "unable to get current location", Toast.LENGTH_SHORT).show();

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

        if (ContextCompat.checkSelfPermission(getActivity(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(getActivity(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }

        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
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

        getDeviceLocation();
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

        Geocoder geocoder = new Geocoder(getActivity());
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
            Toast.makeText(getActivity(), "No se pudo encontrar el lugar", Toast.LENGTH_SHORT).show();
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

        //Toast.makeText(getActivity(), "Carga Exitosa", Toast.LENGTH_SHORT).show();
        //moveCamera(new LatLng(4.6097102,-74.081749),15f);
        mMap = googleMap;


        if (mLocationPermissionsGranted) {
           // getDeviceLocation();
            Parchame();

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);

            //VISUALIZAR EL BOTON DE UBICACION EN LA ESQUINA INFERIOR DERECHA
            View locationButton = ((View) vista.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
