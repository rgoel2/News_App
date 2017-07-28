package com.example.rohangoel.newsapp.Data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.example.rohangoel.newsapp.Data.Contract.TABLE_NEWS.*;


/**
 * Created by rohangoel on 7/28/17.
 */

public class DatabaseUtils {

    public static Cursor getAll(SQLiteDatabase db) {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME_PUBLISHED_AT + " DESC"
        );
        return cursor;
    }

    public static void bulkInsert(SQLiteDatabase db, ArrayList<NewsItem> newsItems) {

        db.beginTransaction();
        try {
            for (NewsItem newsItem : newsItems) {
                ContentValues cv = new ContentValues();
                cv.put(COLUMN_NAME_URL, newsItem.getUrl());
                cv.put(COLUMN_NAME_TITLE, newsItem.getTitle());
                cv.put(COLUMN_NAME_DESCRIPTION, newsItem.getDescription());
                cv.put(COLUMN_NAME_IMAGE_URL, newsItem.getImageUrl());
                cv.put(COLUMN_NAME_AUTHOR, newsItem.getAuthor());
                cv.put(COLUMN_NAME_PUBLISHED_AT, newsItem.getPublishedAt());
                db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public static void deleteAll(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }
}
