package com.example.rohangoel.newsapp.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.rohangoel.newsapp.Data.DBHelper;
import com.example.rohangoel.newsapp.Data.DatabaseUtils;
import com.example.rohangoel.newsapp.Data.NewsItem;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rohangoel on 7/28/17.
 */

public class RefreshNews {

    public static final String ACTION_REFRESH = "refresh";


    public static void refreshNews(Context context) {
        ArrayList<NewsItem> result = null;
        URL url = NetworkUtility.buildUrl();

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        try {
            DatabaseUtils.deleteAll(db);
            String json = NetworkUtility.getResponseFromHttpUrl(url);
            result = (ArrayList<NewsItem>) NetworkUtility.parseJSON(json);
            DatabaseUtils.bulkInsert(db, result);

        } catch (IOException e) {
            e.printStackTrace();

        }

        db.close();
    }
}
