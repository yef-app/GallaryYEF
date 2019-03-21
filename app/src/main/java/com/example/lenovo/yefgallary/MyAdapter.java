package com.example.lenovo.yefgallary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<MyModel> myModels;

    public MyAdapter(MainActivity mainActivity, ArrayList<MyModel> myModels) {
        this.context=mainActivity;
        this.myModels=myModels;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.rowdesign,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {
        MyModel obj1=myModels.get(i);
        Picasso.with(context).load(obj1.getPageurl()).placeholder(R.mipmap.ic_launcher_round).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] names=new String[4];
                names[0]=myModels.get(i).getPageurl();
                names[1]=myModels.get(i).getTag();



            }
        });


    }

    @Override
    public int getItemCount() {
        return myModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image1);
        }
    }
}

