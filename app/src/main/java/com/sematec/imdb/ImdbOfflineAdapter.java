package com.sematec.imdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Details.Details;

public class ImdbOfflineAdapter extends RecyclerView.Adapter<ImdbOfflineAdapter.ImdbOfflineViewHolder> {

    List<Details> mylist;
    ImdbOfflineAdapter (List<Details> list){

        mylist = list;
    }

    @NonNull
    @Override
    public ImdbOfflineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.offline_recycler_item,parent,false);
        ImdbOfflineViewHolder holder = new ImdbOfflineViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImdbOfflineViewHolder holder, int position) {

        holder.txtOfflineTitle.setText(mylist.get(position).getTitle());
        holder.txtOfflineYear.setText(mylist.get(position).getYear());
        holder.txtOfflineDirector.setText(mylist.get(position).getDirector());
        holder.txtOfflineCountry.setText(mylist.get(position).getCountry());
        holder.txtOfflineLanguage.setText(mylist.get(position).getLanguage());
        holder.txtOfflineActors.setText(mylist.get(position).getActors());
        String ImageUrl = mylist.get(position).getPoster();
        Picasso.get().load(ImageUrl).into(holder.imgOfflinePoster);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ImdbOfflineViewHolder extends RecyclerView.ViewHolder {
        TextView txtOfflineTitle;
        TextView txtOfflineYear;
        TextView txtOfflineDirector;
        TextView txtOfflineCountry;
        TextView txtOfflineLanguage;
        TextView txtOfflineActors;
        ImageView imgOfflinePoster;

        public ImdbOfflineViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOfflineTitle = itemView.findViewById(R.id.txtOfflineTitle);
            txtOfflineYear = itemView.findViewById(R.id.txtOfflineYear);
            txtOfflineDirector = itemView.findViewById(R.id.txtOfflineDirector);
            txtOfflineCountry = itemView.findViewById(R.id.txtOfflineCountry);
            txtOfflineLanguage = itemView.findViewById(R.id.txtOfflineLanguage);
            txtOfflineActors = itemView.findViewById(R.id.txtOfflineActors);
            imgOfflinePoster = itemView.findViewById(R.id.imgOfflinePoster);
        }
    }
}
