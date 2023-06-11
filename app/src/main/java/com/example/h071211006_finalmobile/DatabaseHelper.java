package com.example.h071211006_finalmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.h071211006_finalmobile.Model.DataMoviesResponse;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Favorites.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_POSTER = "poster";
    private static final String COLUMN_BACKDROP = "backdrop";
    private static final String COLUMN_RELEASE_DATE = "release_date";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_SYNOPSIS = "synopsis";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_RELEASE_DATE + " TEXT, " +
                COLUMN_POSTER + " TEXT, " +
                COLUMN_SYNOPSIS + " TEXT, " +
                COLUMN_BACKDROP + " TEXT, " +
                COLUMN_RATING + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addFavorite(DataMoviesResponse movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(COLUMN_POSTER, movie.getPosterPath());
        values.put(COLUMN_SYNOPSIS, movie.getOverview());
        values.put(COLUMN_BACKDROP, movie.getBackdropPath());
        values.put(COLUMN_RATING, movie.getVoteAverage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addFavorite(DataTVShowsResponse tvShow) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, tvShow.getName());
        values.put(COLUMN_RELEASE_DATE, tvShow.getFirstAirDate());
        values.put(COLUMN_POSTER, tvShow.getPosterPath());
        values.put(COLUMN_SYNOPSIS, tvShow.getOverview());
        values.put(COLUMN_BACKDROP, tvShow.getBackdropPath());
        values.put(COLUMN_RATING, tvShow.getVoteAverage());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeFavorite(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TITLE + "=?", new String[]{title});
        db.close();
    }

    public boolean isFavorite(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_TITLE + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{title});
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }

    public List<Object> getAllFavorites() {
        List<Object> favorites = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RELEASE_DATE));
                String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTER));
                String synopsis = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SYNOPSIS));
                String backdropPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BACKDROP));
                float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_RATING));

                if (isDataMovie(cursor)) {
                    DataMoviesResponse movie = new DataMoviesResponse(String.valueOf(id), title, releaseDate, posterPath, synopsis, backdropPath, String.valueOf(rating));
                    favorites.add(movie);
                } else {
                    DataTVShowsResponse tvShow = new DataTVShowsResponse(String.valueOf(id), title, releaseDate, posterPath, synopsis, backdropPath, String.valueOf(rating));
                    favorites.add(tvShow);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return favorites;
    }

    private boolean isDataMovie(Cursor cursor) {
        int columnId = cursor.getColumnIndex(COLUMN_ID);

        return cursor.getColumnIndex(COLUMN_ID) != -1;
    }

}
