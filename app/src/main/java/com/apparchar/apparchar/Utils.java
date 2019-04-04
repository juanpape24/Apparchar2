package com.apparchar.apparchar;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
public class Utils extends AppCompatActivity{

    //Tag utilizado para identificar los reportes de cada actividad
    private static final String TAG = "Util";

    //entero para recibir la respuesta del servidor por parte de google
    private static final int ERROR_DIALOG_REQUEST = 9001;


    /*
    Metodo para verificar la conexion de la App con los servicios de google
     */

    public  boolean servicesOk() {
        Log.d(TAG, "SERVICIOS OK");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {
            //Todo esta bien entonces conecta
            Log.d(TAG, "CONEXION EXITOSA CON PLAY SERVICES");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            // Hay un error pero se puede arreglar
            Log.d(TAG, "HAY ERRORES PERO SE PUEDEN SOLUCIONAR");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "NO SE PUEDE CONECTAR CON PLAY SERVICES", Toast.LENGTH_SHORT);
        }
        return false;

    }
}
