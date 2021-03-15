package com.example.semester_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    myadapterclass adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new myadapterclass(dataqueue());
        recyclerView.setAdapter(adapter);
    }
    public ArrayList<mymodelclass> dataqueue()
    {
        ArrayList<mymodelclass> holder=new ArrayList<>();

        mymodelclass obj=new mymodelclass();
        obj.setHeader("Jazz");
        obj.setDesc("Load Card");
        obj.setImgname(R.drawable.jazz);
        holder.add(obj);


        mymodelclass obj1=new mymodelclass();
        obj1.setHeader("Zong");
        obj1.setDesc("Load Card");
        obj1.setImgname(R.drawable.zong);
        holder.add(obj1);

        mymodelclass obj2=new mymodelclass();
        obj2.setHeader("Ufone");
        obj2.setDesc("Load Card");
        obj2.setImgname(R.drawable.ufone);
        holder.add(obj2);

        mymodelclass obj3=new mymodelclass();
        obj3.setHeader("Telenor");
        obj3.setDesc("Load Card");
        obj3.setImgname(R.drawable.telenor);
        holder.add(obj3);
        return holder;
    }
}