package com.example.tounsia.English;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tounsia.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
private LayoutInflater inflater;
private String[] sTitles;
private String[] sContents;

Adapter(Context context , String[] titles , String[] contents){

    this.inflater = LayoutInflater.from(context);
    this.sTitles =titles;
    this.sContents=contents;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.view_violence,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
String title  = sTitles[i];
        holder.violenceTitle.setText(title);
//        holder.violenceContent.setText(title);




    }

    @Override
    public int getItemCount() {
        return sTitles.length;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
    TextView violenceTitle, violenceContent ;



        public  ViewHolder (@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(view.getContext(),Details2.class);
                    i.putExtra("titleOfViolence",sTitles[getAdapterPosition()]);
                    i.putExtra("contentOfViolence",sContents[getAdapterPosition()]);


                    view.getContext().startActivity(i);
                }
            });
            violenceTitle =itemView.findViewById(R.id.storyTitle);
           // violenceTitle =itemView.findViewById(R.id.storycContent);


        }

    }


}
