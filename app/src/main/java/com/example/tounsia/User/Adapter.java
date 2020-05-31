package com.example.tounsia.User;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tounsia.Details;
import com.example.tounsia.Details2;
import com.example.tounsia.R;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater inflater;
    private String[] sTitles;
    private String[] sContents;

    Adapter (Context context , String[] titles , String[] contents){

        this.inflater = LayoutInflater.from(context);
        this.sTitles =titles;
        this.sContents=contents;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.view_rights,viewGroup,false);
        return new Adapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int i) {
        String title  = sTitles[i];
        holder.rightsTitle.setText(title);
//        holder.violenceContent.setText(title);




    }

    @Override
    public int getItemCount() {
        return sTitles.length;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView rightsTitle, violenceContent ;



        public  ViewHolder (@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(view.getContext(), Details2.class);
                    i.putExtra("titleOfRights",sTitles[getAdapterPosition()]);
                    i.putExtra("contentOfRights",sContents[getAdapterPosition()]);


                    view.getContext().startActivity(i);
                }
            });
            rightsTitle =itemView.findViewById(R.id.storyTitle);
            // violenceTitle =itemView.findViewById(R.id.storycContent);


        }

    }


}

