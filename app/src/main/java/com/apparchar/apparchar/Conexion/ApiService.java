package com.apparchar.apparchar.Conexion;

import android.graphics.Bitmap;
import android.media.Image;

import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Vista.Cliente;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService{
    @GET("cliente/")
    Call<ClienteM> validacion(@Query("usuario") String usuario, @Query("contrasenia") String contrasenia);

    @POST("cliente/")
    Call<ClienteM> registro(@Body ClienteM cliente);

    @Multipart
    @POST("cliente/uploadFoto")
    Call<ClienteM> uploadFile(@Part MultipartBody.Part file, @Query("ruta") String ruta);

    @GET("onlyEvent/")
    Call<EventoM> getOnlyEvent(@Query("evento") int idEvento);

    @GET("clientes")
    Call<List<ClienteM>> getClientes();

    @GET("calificacion/")
    Call<List<CalificacionM>> getCalificaciones(@Query("evento") int idEvento);

    @POST("calificacion/")
    Call<CalificacionM> doCalification(@Body CalificacionM calificacionM);

    @Multipart
    @POST("calificacion/uploadFoto")
    Call<ClienteM> uploadFoto(@Part MultipartBody.Part file, @Query("ruta") String ruta);

    @POST("sentiments_analisis/")
    Call<String> sendComentariotoAnalisis(@Query("comentario") String comentario, @Query("id_user") String id_user,@Query("id_evento") int id_evento);

}