package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        DataBaseHelper dataBaseHelp = new DataBaseHelper(MainActivity.this);
        showNotes(dataBaseHelp);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                NoteModel clickedItem = (NoteModel) parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, NoteActivity.class);
                i.putExtra("id",clickedItem.getId());
                //Toast.makeText(MainActivity.this,"Checking", Toast.LENGTH_SHORT);
                startActivity(i);
            }
        });


    }

    public void newNote(View view){
        Intent i = new Intent(this, AddNote.class);
        startActivity(i);
    }
/*
    public void testingLoad(View view){
        DataBaseHelper dataBaseHelp = new DataBaseHelper(MainActivity.this);
        List<NoteModel> everyone = dataBaseHelp.getEveryone();
        Toast.makeText(this, everyone.toString(), Toast.LENGTH_SHORT).show();
    }
    */


    public void showNotes(DataBaseHelper help){

        List<NoteModel> everyone = help.getEveryone();
        ArrayAdapter itemsAdapter = new ArrayAdapter<NoteModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone );

        list.setAdapter(itemsAdapter);
    }


}