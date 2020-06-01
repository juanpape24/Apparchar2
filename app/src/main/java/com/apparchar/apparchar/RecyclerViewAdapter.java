package com.apparchar.apparchar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Modelo.EventoM;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
//import com.apparchar.apparchar.Vista.CalificacionEvento;
import com.apparchar.apparchar.Vista.CalificacionEvento;
import com.apparchar.apparchar.Vista.ListaEvento;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    private List<EventoM> lista;
    private Context context;

    public RecyclerViewAdapter(Context context, List<EventoM> lista) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        ArrayList<Bitmap> a = new ArrayList<>();
        Picasso.with(context).load(lista.get(i).getFoto()).fit().into(myViewHolder.image);
        /*else {
            Bitmap bmp = BitmapFactory.decodeByteArray(lista.get(i).getFoto(), 0, lista.get(i).getFoto().length);
            myViewHolder.image.setImageBitmap(bmp);
        }*/
        myViewHolder.titulo.setText(lista.get(i).getNombre());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        String year = currentDateandTime.substring(0, 4);
        String month = currentDateandTime.substring(4, 6);
        String day = currentDateandTime.substring(6, 8);
        final String fechac = day + "/" + month + "/" + year;
        final String hour = currentDateandTime.substring(9, 11);
        final String minutos = currentDateandTime.substring(11, 13);
        String time = hour + ":" + minutos;
        myViewHolder.descripcion.setText(lista.get(i).getDescripcion() + "\nDireccion: "+ lista.get(i).getLugar()+"\nFecha: " + lista.get(i).getFecha() + "\nHora: " + lista.get(i).getHora_inicio());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String inicio[] = lista.get(i).getEventoPK().getHoraInicio().split(":");
                //String end[] = lista.get(i).getEventoPK().getHoraFinal().split(":");
                //String timeS = inicio[0] + inicio[1]; //hora inicio
                //String timeE = end[0] + end[1]; //hora final
                //String timeA = hour + minutos; //hora actual
                //System.out.println("Condicional: "+timeS+"<"+timeA+"-----"+timeE+">"+timeA);


               // if (lista.get(i).getEventoPK().getFecha().equals(fechac) && Integer.valueOf(timeS) < Integer.valueOf(timeA) && Integer.valueOf(timeE) > Integer.valueOf(timeA)) {
                    Intent intent = new Intent(context, CalificacionEvento.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", lista.get(i).getId());

                    /*intent.putExtra("fecha", lista.get(i).getEventoPK().getFecha());
                    intent.putExtra("final", lista.get(i).getEventoPK().getHoraFinal());
                    intent.putExtra("inicio", lista.get(i).getEventoPK().getHoraInicio());*/
                    context.startActivity(intent);
                //} else {
                  //  Toast.makeText(context, "El evento no ha empezado o ya finalizó", Toast.LENGTH_SHORT).show();
                //}


            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, descripcion;
        ImageView image;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.texto_marimondazo);
            image = itemView.findViewById(R.id.img_marimondazo);
            cardView = itemView.findViewById(R.id.cardv);
            descripcion = itemView.findViewById(R.id.descripcione);
        }
    }
}