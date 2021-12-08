package com.example.notes;

import android.widget.Toast;

public class NoteModel {
    public String getId() {
        return String.valueOf(id);
    }



    private int id ;
    private String title;
    private String message;


    public NoteModel(int id, String title, String message){
        this.id = id;
        this.title = title;
        this.message = message;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return title;
    }






}
