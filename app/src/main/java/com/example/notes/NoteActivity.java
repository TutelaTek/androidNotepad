package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    String id = null;

    // Should load list on entree
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        editUpdate();
    }

    public void editUpdate(){
        DataBaseHelper help = new DataBaseHelper(NoteActivity.this);

        int intID = Integer.parseInt(id);
        NoteModel model = help.read(intID);
        EditText e = (EditText) findViewById(R.id.title);
        EditText b = (EditText) findViewById(R.id.noteMessage);
        model.getId();
        e.setText(model.getTitle());
        b.setText(model.getMessage());



    }

    public void edit(View view){
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        int intID = Integer.parseInt(id);

        TextView t = findViewById(R.id.title);
        String title = t.getText().toString();
        TextView m = findViewById(R.id.noteMessage);
        String message = m.getText().toString();
        NoteModel noteModel = new NoteModel(intID, title, message);
        DataBaseHelper help = new DataBaseHelper(NoteActivity.this);
        boolean success = help.editOne(noteModel);
        if(success) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show();
        }
    }



        //Should Send you back to main page
    public void deleteNote (View view){
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        int intID = Integer.parseInt(id);
        DataBaseHelper help = new DataBaseHelper(NoteActivity.this);
        boolean success = help.deleting(intID);
        if(success) {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Deleting failed", Toast.LENGTH_SHORT).show();
        }

    }
}