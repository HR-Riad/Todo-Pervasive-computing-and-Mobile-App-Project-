package com.masterandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText inputText;
    RadioButton r1, r2;
    TextView date1;
    Button button;
    DatabaseHelper myDb;
    String priority;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
       //
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        //
        date1 =(TextView) findViewById(R.id.date);
        date1.setText(""+date);


        r1 = findViewById(R.id.radiobutton1);
        r2 = findViewById(R.id.radiobutton2);
        inputText = findViewById(R.id.inputText);
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String status = i.getStringExtra("status");
        String id = i.getStringExtra("id");
        inputText.setText("" + title);
        if (status.equals("COMPLETE")) {
            r1.setChecked(true);
            r2.setChecked(false);
        } else {
            r1.setChecked(false);
            r2.setChecked(true);
        }
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.radiobutton1:
                        priority = "COMPLETE";
                        break;
                    case R.id.radiobutton2:
                        priority = "INCOMPLETE";
                        break;

                }
            }
        });

        button = findViewById(R.id.updateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = inputText.getText().toString();
                String status = priority;
            try {
                myDb.updateData(id,title,status);
            }
            catch (Exception e){
                System.out.println("Exception : "+e);
            }
            }
        });
    }
}