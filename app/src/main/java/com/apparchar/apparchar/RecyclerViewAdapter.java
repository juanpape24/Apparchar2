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

import com.apparchar.apparchar.Contract.ContractListaEvento;
import com.apparchar.apparchar.Presentador.ListaEventoPresenter;
import com.apparchar.apparchar.VO.EventoVO;
import com.apparchar.apparchar.Vista.ListaEvento;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    private List<EventoVO> lista;
    private Context context;

    public RecyclerViewAdapter(Context context,List<EventoVO> lista) {
        this.lista=lista;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        myViewHolder.image.setImageResource(lista.get(i).getIdEvento());
        myViewHolder.titulo.setText(lista.get(i).getDescripcion());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,myViewHolder.titulo.getText(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
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
