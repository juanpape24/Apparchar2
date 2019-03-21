package com.apparchar.apparchar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apparchar.apparchar.VO.EventoVO;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private List<EventoVO> dato;

    public RecyclerViewAdapter(Context context, List<EventoVO> dato) {
        this.context = context;
        this.dato = dato;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.cardview_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {

        myViewHolder.titulo.setText(dato.get(i).getDescripcion());
        myViewHolder.image.setImageResource(dato.get(i).getIdEvento());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,myViewHolder.titulo.getText(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return dato.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        ImageView image;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.texto_marimondazo);
            image=itemView.findViewById(R.id.img_marimondazo);
            cardView=itemView.findViewById(R.id.cardv);
        }
    }
}
