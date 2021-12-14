package com.masterandroid.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    public   static String DATABASE_NAME = "Todo.db";
    public   static String TABLE_NAME = "todo_table";
    public  static String COL_1="ID";
    public  static String COL_2="TITLE";
    public  static String COL_3="STATUS";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,STATUS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String title,String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,title);
        cv.put(COL_3,status);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result==-1) return false;
        else
            return true;
    }
public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
}
 public void deleteData(String id){
    System.out.println("ID : "+id);
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_NAME, "ID = ?", new String[]{id});

}
public void updateData(String id,String title, String status){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(COL_1,id);
    cv.put(COL_2,title);
    cv.put(COL_3,status);
    db.update(TABLE_NAME,cv,"ID = ?", new String[]{id});

}

}
