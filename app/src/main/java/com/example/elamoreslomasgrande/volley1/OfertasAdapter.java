package com.example.elamoreslomasgrande.volley1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by elamoreslomasgrande on 13/11/2017.
 */

public class OfertasAdapter extends RecyclerView.Adapter<OfertasAdapter.MyViewHolder> {
    private Context mContext;
    private List<Oferta> ofertaList;



    public OfertasAdapter(Context mContext, List<Oferta> ofertaList){
        this.mContext = mContext;
        this.ofertaList = ofertaList;


    }

    @Override
    public OfertasAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.oferta_card, viewGroup, false);

        return new MyViewHolder(view);

    }

    public List<Oferta> getOfertas() {
        return ofertaList;
    }




    @Override
    public void onBindViewHolder(final OfertasAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(ofertaList.get(i).getTitulo());
        String vote = ofertaList.get(i).getDescripcion();
        viewHolder.userrating.setText(vote);

    }

    @Override
    public int getItemCount(){

        int n = ofertaList.size();
        return n;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, userrating;


        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);

        }

    }

}
