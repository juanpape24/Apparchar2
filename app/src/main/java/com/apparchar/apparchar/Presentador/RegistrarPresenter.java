package com.apparchar.apparchar.Presentador;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.apparchar.apparchar.Conexion.JsonApi;
import com.apparchar.apparchar.Contract.ContractClient;

import com.apparchar.apparchar.Modelo.ClienteM;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrarPresenter implements ContractClient.Presenter{
    ClienteM cliente;
    RequestParams params;

    private ContractClient.View vista;


    /**
     * HOST
     */

    public RegistrarPresenter(ContractClient.View vista) {
        this.vista = vista;
        cliente = new ClienteM();
    }

    @Override
    public void enviar(String nombre, String apellido, String edad, String correo, String cel, String user, String pass, String pass2, File foto) {
        if (nombre.equals("") || apellido.equals("") || edad.equals("") || correo.equals("") || cel.equals("") || user.equals("") || pass.equals("") || pass2.equals("")) {
            vista.showResult("Llene todos los campos");
        } else {
            if (!pass.equals(pass2)) {
                vista.showResult("Las contrase\u00f1as no coinciden");
            } else {
                Long tslong = System.currentTimeMillis()/1000;
                String ts = tslong.toString();
                Random r = new Random();
                String n1= String.valueOf(r.nextInt(10));
                String n2= String.valueOf(r.nextInt(10));
                String ruta ="fotoPerfil/"+tslong+n1+n2;
                cliente.setNombre(nombre);
                cliente.setEdad(Integer.parseInt(edad));
                cliente.setCorreo(correo);
                cliente.setTelefono(cel);
                cliente.setUsuario(user);
                cliente.setContrasenia(pass);
                cliente.setFoto(ruta);

                vista.swap();
                params = new RequestParams();

                // RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),foto);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), foto);
                MultipartBody.Part body= MultipartBody.Part.createFormData("foto",user,requestBody);


                JsonApi.getApiService().uploadFile(body,ruta).enqueue(new Callback<ClienteM>() {
                    @Override
                    public void onResponse(Call<ClienteM> call, Response<ClienteM> response) {


                    }

                    @Override
                    public void onFailure(Call<ClienteM> call, Throwable t) {
                        Log.i("error",t.getMessage());
                        //vista.showResult("No se pudo subir la foto");
                    }
                });

                JsonApi.getApiService().registro(cliente).enqueue(new Callback<ClienteM>() {
                    @Override
                    public void onResponse(Call<ClienteM> call, Response<ClienteM> response) {
                        vista.showResult("Se registr\u00f3 correctamente");
                    }

                    @Override
                    public void onFailure(Call<ClienteM> call, Throwable t) {
                        vista.showResult("El usuario ya existe");
                    }
                });

            }

        }
    }



}
