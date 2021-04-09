package com.example.semester_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapterclass extends RecyclerView.Adapter<myviewholderclass>
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ArrayList<mymodelclass> data;
    Context context;
    public myadapterclass(ArrayList<mymodelclass> data,Context context)
    {
        this.data = data;
        this.context=context;
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
        final mymodelclass temp=data.get(position);

        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());

        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.jazz));
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.telenor));
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.ufone));
        holder.imageView.setImageResource(data.get(position).getImgname(R.drawable.zong));

        
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity2.class);
                intent.putExtra("imagename",temp.getImgname('a'));
                intent.putExtra("header",temp.getDesc());
                intent.putExtra("desc",temp.getHeader());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                //camera();
            }
        });
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity2.class);
                intent.putExtra("imagename",temp.getImgname('a'));
                intent.putExtra("header",temp.getDesc());
                intent.putExtra("desc",temp.getHeader());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity2.class);
                intent.putExtra("imagename",temp.getImgname('a'));
                intent.putExtra("header",temp.getDesc());
                intent.putExtra("desc",temp.getHeader());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    



}
