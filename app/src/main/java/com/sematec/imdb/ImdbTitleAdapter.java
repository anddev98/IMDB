package com.sematec.imdb;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Movies.Search;

public class ImdbTitleAdapter extends RecyclerView.Adapter<ImdbTitleAdapter.ImdbTitleViewHolder> {

    List<Search> mylist;
    ImdbTitleAdapter (List<Search> list) {

        mylist = list;
    }


    @NonNull
    @Override
    public ImdbTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.title_recycler_item,parent,false);
        ImdbTitleViewHolder holder = new ImdbTitleViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImdbTitleViewHolder holder, int position) {

        String title = mylist.get(position).getTitle();
        holder.txtTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ImdbTitleViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;

        public ImdbTitleViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Search search = mylist.get(getAdapterPosition());
                    Intent intent = new Intent(itemView.getContext(),ImdbOnlineDetailActivity.class);
                    intent.putExtra("id",search.getImdbID());
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }
}



