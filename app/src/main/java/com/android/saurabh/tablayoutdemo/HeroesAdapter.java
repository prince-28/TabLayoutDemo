package com.android.saurabh.tablayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Saurabh on 3/28/2018.
 */

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder> {

    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";

    private List<HeroesList> heroesLists;
    private Context context;

    public HeroesAdapter(List<HeroesList> herolist, Context context) {

        this.heroesLists = herolist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_layout, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeroesAdapter.ViewHolder holder, int position) {

        final HeroesList heroesList = heroesLists.get(position);
        holder.title.setText(heroesList.gettitle());

//        Picasso.with(context)
//                .load(heroesList.gethero_url())
//                .into(holder.hero_url);
    }

    @Override
    public int getItemCount() {
        return heroesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView hero_url;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            hero_url = itemView.findViewById(R.id.item_icon);
        }
    }
}
