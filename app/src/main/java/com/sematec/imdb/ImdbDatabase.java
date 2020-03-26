package com.sematec.imdb;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Details.Details;

public class ImdbDatabase extends SQLiteOpenHelper {
    String TABLE_NAME = "detailMovie";
    public ImdbDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREAE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "year TEXT, " +
                "director TEXT, " +
                "country TEXT," +
                "Language TEXT," +
                "actors TEXT," +
                "poster" +
                ")";
        db.execSQL(CREAE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertMovie (String title,String year,String director,String country,String language,String actors,String poster){
        String INSERT_MOVIE_QUERY = "INSERT INTO " + TABLE_NAME + "(title,year,director,country,language,actors,poster) VALUES("
                +"'"+title+"'"+","
                +"'"+year+"'"+","
                +"'"+director+"'"+","
                +"'"+country+"'"+","
                +"'"+language+"'"+","
                +"'"+actors+"'"+","
                +"'"+poster+"'"
                +")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_MOVIE_QUERY);
        db.close();
    }

    public List<Details> GetAllMovie() {
        List<Details> mylist = new ArrayList<>();
        String GET_ALL_MOVIE = "SELECT title,year,director,country,language,actors,poster FROM " + TABLE_NAME;
        Details movieDetails;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(GET_ALL_MOVIE,null);
        while (c.moveToNext()) {
            movieDetails = new Details();
            movieDetails.setTitle(c.getString(0));
            movieDetails.setYear(c.getString(1));
            movieDetails.setDirector(c.getString(2));
            movieDetails.setCountry(c.getString(3));
            movieDetails.setLanguage(c.getString(4));
            movieDetails.setActors(c.getString(5));
            movieDetails.setPoster(c.getString(6));
            mylist.add(movieDetails);
        }
        db.close();
        return mylist;
    }
}
