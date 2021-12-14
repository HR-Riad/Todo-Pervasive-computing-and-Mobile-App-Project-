package com.masterandroid.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {


    FloatingActionButton Addbutton;
    ImageButton editButton;
    ////
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    Context context;

    ArrayList<Todo> todos;
    ////
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        initializeData();
        initializeAdapter();
        ////
        Addbutton = (FloatingActionButton) findViewById(R.id.AddButton);
        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initializeData() {
        todos = new ArrayList<Todo>();
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            System.out.println("");
        } else {
            while (res.moveToNext()) {
                todos.add(new Todo("" + res.getString(1), "" + res.getString(0), "" + res.getString(2)));
            }
        }
    }

    public void initializeAdapter() {
        recyclerView = findViewById(R.id.RecycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, todos);
        recyclerView.setAdapter(adapter);
    }

    public void del(String id){
        myDb.deleteData(id);
    }
}