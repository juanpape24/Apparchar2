package com.apparchar.apparchar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private List<EventoM> lista;
    private Context context;
    private String idUser;

    public RecyclerViewAdapter(Context context, List<EventoM> lista, String id) {
        this.lista = lista;
        this.context = context;
        this.idUser = id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        ArrayList<Bitmap> a = new ArrayList<>();
        if(lista.get(i).getFoto()==null){
            myViewHolder.image.setImageResource(R.drawable.descarga);
        }else{
            Bitmap bmp = BitmapFactory.decodeByteArray(lista.get(i).getFoto(), 0, lista.get(i).getFoto().length);
            myViewHolder.image.setImageBitmap(bmp);
        }
        myViewHolder.titulo.setText(lista.get(i).getNombre());
        myViewHolder.descripcion.setText(lista.get(i).getDescripcion());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CalificacionEvento.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",lista.get(i).getEventoPK().getIdevento());
                intent.putExtra("fecha",lista.get(i).getEventoPK().getFecha());
                intent.putExtra("final",lista.get(i).getEventoPK().getHoraFinal());
                intent.putExtra("inicio",lista.get(i).getEventoPK().getHoraInicio());
                intent.putExtra("user",idUser);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo,descripcion;
        ImageView image;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.texto_marimondazo);
            image = itemView.findViewById(R.id.img_marimondazo);
            cardView = itemView.findViewById(R.id.cardv);
            descripcion=itemView.findViewById(R.id.descripcione);
        }
    }
}
