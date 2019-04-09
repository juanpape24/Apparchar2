package com.apparchar.apparchar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Vista.ListaEvento;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {


    private List<String> lista;
    private Context context;
    private String idUser;

    public AdapterRecycler(Context context, ArrayList<String> lista, String id) {
        this.lista = lista;
        this.context = context;
        this.idUser = id;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categoria, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        ArrayList<Bitmap> a = new ArrayList<>();
        if(lista.get(i).equals("cine")){
            myViewHolder.image.setImageResource(R.drawable.cine);
            myViewHolder.texto.setText(lista.get(i));
        }else if(lista.get(i).equals("lectura")){
            myViewHolder.image.setImageResource(R.drawable.lectura);
            myViewHolder.texto.setText(lista.get(i));
        }else if(lista.get(i).equals("rumba")){
            myViewHolder.image.setImageResource(R.drawable.rumba);
            myViewHolder.texto.setText(lista.get(i));
        }else{
            myViewHolder.image.setImageResource(R.drawable.foto);
            myViewHolder.texto.setText(lista.get(i));
        }
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ListaEvento.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("categoria",lista.get(i));
                intent.putExtra("user",idUser);
               /* intent.putExtra("id",lista.get(i).getEventoPK().getIdevento());
                intent.putExtra("fecha",lista.get(i).getEventoPK().getFecha());
                intent.putExtra("final",lista.get(i).getEventoPK().getHoraFinal());
                intent.putExtra("inicio",lista.get(i).getEventoPK().getHoraInicio());
                intent.putExtra("user",idUser);*/
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        CardView cardView;
        TextView texto;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_categoria);
            cardView = itemView.findViewById(R.id.cardv2);
            texto=itemView.findViewById(R.id.texto_categoria);
        }
    }
}
