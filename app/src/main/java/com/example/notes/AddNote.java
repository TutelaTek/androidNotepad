package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }


    // should send back to main
    public void saveNote (View view){
        boolean success;

        TextView t = findViewById(R.id.title);
        String title = t.getText().toString();
        TextView m = findViewById(R.id.noteMessage);
        String message = m.getText().toString();
        NoteModel noteModel = new NoteModel(-1, title, message);
        DataBaseHelper dataHelp = new DataBaseHelper(AddNote.this);

        success = dataHelp.addOne(noteModel);


        if(success) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}