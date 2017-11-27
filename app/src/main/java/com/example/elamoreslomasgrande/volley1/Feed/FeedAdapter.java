package com.example.elamoreslomasgrande.volley1.Feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elamoreslomasgrande.volley1.Ofertas.Oferta;
import com.example.elamoreslomasgrande.volley1.Ofertas.OfertasAdapter;
import com.example.elamoreslomasgrande.volley1.R;

import java.util.List;

/**
 * Created by elamoreslomasgrande on 23/11/2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {
    private Context mContext;
    private List<Publicacion> publicacionList;

    public FeedAdapter(Context mContext, List<Publicacion> publicacionList){
        this.mContext = mContext;
        this.publicacionList = publicacionList;


    }

    @Override
    public FeedAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.publicacion_card, viewGroup, false);

        return new MyViewHolder(view);

    }


    public List<Publicacion> getFeed() {
        return publicacionList;
    }




    @Override
    public void onBindViewHolder(final FeedAdapter.MyViewHolder viewHolder, int i){
        viewHolder.title.setText(publicacionList.get(i).getDescripcion());
        String vote = publicacionList.get(i).getDescripcion();
        viewHolder.userrating.setText(vote);

    }

    @Override
    public int getItemCount(){

        int n = publicacionList.size();
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
