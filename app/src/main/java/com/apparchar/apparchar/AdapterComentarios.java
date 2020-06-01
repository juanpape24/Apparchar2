package com.apparchar.apparchar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apparchar.apparchar.Modelo.CalificacionM;
import com.apparchar.apparchar.Modelo.ClienteM;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterComentarios extends RecyclerView.Adapter<HolderComentario> {
    private ArrayList<CalificacionM> comentarios;
    private Context c;
    final String s3Bucket="https://apparchar.s3.amazonaws.com/";
    ArrayList<ClienteM> clientes;

    public AdapterComentarios(Context c) {
        this.c = c;
        comentarios = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public void addC(CalificacionM calificacionM, ClienteM clienteMO) {
        comentarios.add(calificacionM);
        clientes.add(clienteMO);

        notifyItemInserted(comentarios.size());
    }

    public void removeC() {
        if (!comentarios.isEmpty()) {
            notifyItemRangeRemoved(0, comentarios.size());
            comentarios.clear();
        }
    }


    @NonNull
    @Override
    public HolderComentario onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_view_comentario, viewGroup, false);
        return new HolderComentario(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderComentario holderComentario, int i) {
        holderComentario.getNombre().setText(comentarios.get(i).getUsuariocliente());
        holderComentario.getComentario().setText(comentarios.get(i).getComentario());
        holderComentario.getHora().setText(comentarios.get(i).getHora());
        String url=s3Bucket+clientes.get(i).getFoto()+".jpg";
        Context context = holderComentario.getContext();
        Picasso.with(context).load(url).fit().into(holderComentario.getFoto());
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

}
