package com.masterandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTodoActivity extends AppCompatActivity {

    TextView date1;
    DatabaseHelper myDb;
    RadioGroup radioGroup;
    RadioButton radiob1;
    String priority;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        long millis=System.currentTimeMillis();
        java.sql.Date date11=new java.sql.Date(millis);
        //
        date1 =(TextView) findViewById(R.id.date);
        date1.setText(""+date11);

        myDb = new DatabaseHelper(this);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        EditText inputText = (EditText) findViewById(R.id.inputText);
        radioGroup =(RadioGroup) findViewById(R.id.radioGroup);
        radiob1 = (RadioButton) findViewById(R.id.radiobutton1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @SuppressLint("NonConstantResourceId")
             @Override
             public void onCheckedChanged(RadioGroup group, int i) {
                 switch(i){
                     case R.id.radiobutton1:
                         priority="COMPLETE";
                         break;
                     case R.id.radiobutton2:
                         priority="INCOMPLETE";
                         break;
                 }
             }
         });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char s = 0;
                if(inputText.getText().toString().length()>0) {
                    s = inputText.getText().toString().charAt(0);
                }
                if(inputText.getText().toString().length()>0 && priority.length()>0 && s != (' ')){
                    boolean isInserted = myDb.insertData(inputText.getText().toString(),priority.toString());
                    if (isInserted){
                        Toast.makeText(AddTodoActivity.this, "Data Inserted "+inputText.getText().toString()+" "+priority.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddTodoActivity.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AddTodoActivity.this, "Data Could not be Inserted", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(AddTodoActivity.this, "Please Fill all Field", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
}