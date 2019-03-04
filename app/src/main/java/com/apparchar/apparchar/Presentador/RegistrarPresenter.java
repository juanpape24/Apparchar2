package com.apparchar.apparchar.Presentador;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.apparchar.apparchar.Contract.ContractClient;
import com.apparchar.apparchar.Modelo.Cliente;
import com.apparchar.apparchar.Modelo.UserClient;
import com.apparchar.apparchar.Modelo.Token;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class RegistrarPresenter implements ContractClient.Presenter {
    UserClient cliente;
    private ContractClient.View vista;
    private static final int SERVERPORT = 5000;
    /**
     * HOST
     */
    private static final String ADDRESS = "192.168.137.1";

    public RegistrarPresenter(ContractClient.View vista) {
        this.vista = vista;
        cliente = new UserClient();
    }

    @Override
    public void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass, String pass2) {
        if (nombre.equals("") || apellido.equals("") || edad.equals("") || correo.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")) {
            vista.showResult("Llene todos los campos");
        } else {
            if (!pass.equals(pass2)) {
                vista.showResult("Las contraseñas no coinciden");
            } else {
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEdad(Integer.parseInt(edad));
                cliente.setCorreo(correo);
                cliente.setCelular(cel);
                cliente.setUser(user);
                cliente.setPass(pass);
                vista.swap();
                vista.showResult(cliente.toString());
            }

        }
    }
    class MyATaskCliente extends AsyncTask<String, Void, String> {
        /**
         * Ventana que bloqueara la pantalla del movil hasta recibir respuesta
         * del servidor
         */
        ProgressDialog progressDialog;

        /**
         * muestra una ventana emergente
         */

        /**
         * Se conecta al servidor y trata resultado
         */
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
                Cliente myCliente = new Cliente(123, cliente.getNombre(), cliente.getEdad(), cliente.getCorreo(), cliente.getCelular(), cliente.getUser(), cliente.getPass());
                Token peticion = new Token("insertar", "cliente", myCliente);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Gson enviar = new Gson();
                String jsonOutput = enviar.toJson(peticion);
                System.out.println("objeto en Json: " + jsonOutput);
                out.writeUTF(jsonOutput);

                //recibe respuesta del servidor y formatea a String
                Log.i("I/TCP Client", "Received data to server");
                Token respuesta = new Token();
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                String jsonInput = entrada.readUTF();
                Gson gson = new Gson();
                respuesta = gson.fromJson(jsonInput, respuesta.getClass());
                boolean a = (boolean) respuesta.getObjecto();
                String resultado = "false";
                if (a == true) {
                    resultado = "true";
                }
                received = resultado;
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
            return received;
        }

        /**
         * Oculta ventana emergente y muestra resultado en pantalla
         */
    }
}

