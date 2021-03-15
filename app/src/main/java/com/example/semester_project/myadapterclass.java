package com.example.semester_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapterclass extends RecyclerView.Adapter<myviewholderclass>
{
    ArrayList<mymodelclass> data;

    public myadapterclass(ArrayList<mymodelclass> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholderclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.singlerow_card,parent,false);//blank box bn gaya
        return new myviewholderclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholderclass holder, int position) {
        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.jazz));
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.telenor));
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.ufone));
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.zong));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
