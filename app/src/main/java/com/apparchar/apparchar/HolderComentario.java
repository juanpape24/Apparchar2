package com.apparchar.apparchar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderComentario extends RecyclerView.ViewHolder {
    private TextView nombre;
    private TextView comentario;
    private TextView hora;
    private CircleImageView foto;
    private Context context;


    public HolderComentario(@NonNull View itemView) {
        super(itemView);
        nombre = itemView.findViewById(R.id.userComentario);
        comentario = itemView.findViewById(R.id.comentarioComentario);
        hora = itemView.findViewById(R.id.horaComentario);
        foto = itemView.findViewById(R.id.fotoComentario);
        context= itemView.getContext();


    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getComentario() {
        return comentario;
    }

    public void setComentario(TextView comentario) {
        this.comentario = comentario;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public CircleImageView getFoto() {
        return foto;
    }

    public void setFoto(CircleImageView foto) {
        this.foto = foto;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
