package com.apparchar.apparchar.Modelo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// Clase para interactuar con el servidor

public class MyATaskCliente extends AsyncTask<String, Void, String> {
    /**
     * muestra una ventana emergente
     */

    private static final int SERVERPORT = 5000;
    /**
     * HOST
     */
    private static final String ADDRESS = "192.168.137.1";
    /**
     * Ventana que bloqueara la pantalla del movil hasta recibir respuesta
     * del servidor
     */
    ProgressDialog progressDialog;
    /**
     * Se conecta al servidor y trata resultado
     */
    Token token;
    String jsonInput = "";

    public MyATaskCliente(Token token) {
        this.token = token;
    }

    @Override
    protected String doInBackground(String... values) {
        String received = "pailas";
        try {
            //Se conecta al servidor
            InetAddress serverAddr = InetAddress.getByName(ADDRESS);
            Log.i("I/TCP Client", "Connecting...");
            Socket socket = new Socket(serverAddr, SERVERPORT);
            Log.i("I/TCP Client", "Connected to server");

            //envia peticion de cliente
            Log.i("I/TCP Client", "Send data to server");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Gson enviar = new Gson();
            String jsonOutput = enviar.toJson(token);
            System.out.println("objeto en Json: " + jsonOutput);
            out.writeUTF(jsonOutput);

            //recibe respuesta del servidor y formatea a String
            Log.i("I/TCP Client", "Received data to server");
            Token respuesta = new Token();
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            jsonInput = entrada.readUTF();
            Log.i("informacion",jsonInput);
            Gson gson = new Gson();
            //respuesta = gson.fromJson(jsonInput, respuesta.getClass());
//cierra conexion
            socket.close();
            out.close();
            entrada.close();

        } catch (UnknownHostException ex) {
            Log.e("E/TCP Client", "" + ex.getMessage());
            return ex.getMessage();
        } catch (IOException ex) {
            Log.e("E/TCP Client", "" + ex.getMessage());
            return ex.getMessage();
        }
        return jsonInput;
    }

    public String getX() {
        return jsonInput;
    }

    /**
     * Oculta ventana emergente y muestra resultado en pantalla
     */
    @Override
    protected void onPostExecute(String value) {
        jsonInput = value;
    }

}