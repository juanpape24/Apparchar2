package com.apparchar.apparchar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apparchar.apparchar.Modelo.CalificacionM;

import java.util.ArrayList;
import java.util.List;

public class AdapterComentarios extends RecyclerView.Adapter<HolderComentario> {
    private ArrayList<CalificacionM> comentarios;
    private Context c;

    public AdapterComentarios(Context c) {
        this.c = c;
        comentarios = new ArrayList<>();
    }

    public void addC(CalificacionM calificacionM) {
        comentarios.add(calificacionM);
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
        holderComentario.getNombre().setText(comentarios.get(i).getUsuariocliente().getUsuario());
        holderComentario.getComentario().setText(comentarios.get(i).getComentario());
        holderComentario.getHora().setText(comentarios.get(i).getHora());
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}
