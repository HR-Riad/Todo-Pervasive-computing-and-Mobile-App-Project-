package com.masterandroid.myapplication;

public class Todo {
    private final String title;
    private final String id;
    private final String status;
    public Todo(String title,String id,String status) {
        this.title = title;
        this.id=id;
        this.status=status;
    }
    public String getTitle(){
        return title;
    }
    public String getId(){
        return id;
    }
    public  String getStatus(){
        return status;
    }
}
