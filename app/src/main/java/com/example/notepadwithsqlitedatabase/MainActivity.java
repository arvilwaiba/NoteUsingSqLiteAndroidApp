package com.example.notepadwithsqlitedatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private FloatingActionButton addBtn;
    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Data> arrayList=new ArrayList<>();
    private SqlLiteHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recyclerview);
        addBtn=findViewById(R.id.floatingActionButton);
        linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        myDb=new SqlLiteHelper(this);
        AddData();
        ViewData();
        recyclerAdapter=new RecyclerAdapter(arrayList,this,myDb);
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void ViewData() {
        Cursor data= myDb.getAllData();

        while (data.moveToNext()){

            arrayList.add(new Data(data.getString(0),data.getString(1),data.getString(2)
            ,data.getString(3)));
        }
    }



    public void AddData() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dataPassIntent = new Intent(MainActivity.this, AddDataActivity.class);
                dataPassIntent.putExtra("id", "");
                dataPassIntent.putExtra("firstname", "");
                dataPassIntent.putExtra("lastname", "");
                dataPassIntent.putExtra("mark", "");
                startActivity(dataPassIntent);
            }
        });
    }

}



