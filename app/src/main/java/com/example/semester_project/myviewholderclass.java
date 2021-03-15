package com.example.semester_project;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholderclass extends RecyclerView.ViewHolder
{

    ImageView imageView;
    TextView t1,t2;
    public myviewholderclass(@NonNull View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.img);
        t1=(TextView)itemView.findViewById(R.id.btext);
        t2=(TextView)itemView.findViewById(R.id.stext);
    }
}
