package com.example.notes;

import static android.widget.Toast.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String NOTES_TABLE = "NOTE_TABLE";
    public static final String COLUMN_NOTE_TITLE = "NOTE_TITLE";
    public static final String COLUMN_NOTE_MESSAGE = "NOTE_MESSAGE";
    private static final String COLUMN_ID = "ID";

    public void loadData(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT " + COLUMN_NOTE_TITLE + ", " + COLUMN_NOTE_MESSAGE + " FROM " + NOTES_TABLE + " WHERE " + COLUMN_ID + " = " + id;
        Cursor c = db.rawQuery(selectQuery, null);
    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, "notes.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + NOTES_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOTE_TITLE + " TEXT, "+ COLUMN_NOTE_MESSAGE + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(NoteModel NoteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOTE_TITLE, NoteModel.getTitle());
        cv.put(COLUMN_NOTE_MESSAGE, NoteModel.getMessage());
        long insert = db.insert(NOTES_TABLE, null,cv);

        if(insert == -1){
            return false;
        }else{
            return true;
        }

    }

    public boolean editOne(NoteModel notemodel){
        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOTE_TITLE,notemodel.getTitle()); //These Fields should be your String values of actual column names
        cv.put(COLUMN_NOTE_MESSAGE,notemodel.getMessage());

        String strSQL = NOTES_TABLE + " , " + COLUMN_NOTE_TITLE + " = " + notemodel.getTitle() + ", " + COLUMN_NOTE_MESSAGE + " = " + notemodel.getMessage() + " WHERE " + COLUMN_ID + " = " + notemodel.getId();
        int insert = data.update(NOTES_TABLE, cv, "id = ?", new String[]{notemodel.getId()});

        if (insert == -1){
            return false;
        }else {
            return true;
        }

    }

    public boolean deleting(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTES_TABLE + " Where " + COLUMN_ID + " = " + id;
        Cursor cur = db.rawQuery(queryString, null);
        if (cur.moveToFirst()){
            return false;
        }
        else {
             return true;
        }

    }

    public NoteModel read(int id){
        int noteID = 1;
        String title = "Title";
        String message = "message";

        String queryString = "SELECT * FROM " + NOTES_TABLE + " WHERE " + COLUMN_ID + " = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        //error
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            noteID = cursor.getInt(0);
            title = cursor.getString(1);
            message = cursor.getString(2);

            NoteModel note = new NoteModel(noteID, title, message);
        }else {

        }
        NoteModel note = new NoteModel(noteID, title, message);
        return note;
    }

    public List<NoteModel> getEveryone(){
        List<NoteModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM NOTE_TABLE";

        SQLiteDatabase db = this.getReadableDatabase();


        //error happening on this line
        Cursor cursor = db.rawQuery( queryString,null);
        System.out.println("testing" );
        try{
            if (cursor.moveToFirst()) {
                do {
                    int noteID = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String message = cursor.getString(2);
                    NoteModel note = new NoteModel(noteID,title,message);
                    returnList.add(note);
                } while (cursor.moveToNext());


            } else {
                // failure. do not add anything to the list
            }
        }catch (Exception e){

        }
        cursor.close();
        db.close();


        return returnList;
    }



}
