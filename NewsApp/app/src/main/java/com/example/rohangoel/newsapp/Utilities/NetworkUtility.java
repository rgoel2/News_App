package com.example.rohangoel.newsapp.Utilities;
import android.net.Uri;
import android.util.Log;

import com.example.rohangoel.newsapp.Data.NewsItem;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rohangoel on 6/20/17.
 */

public class NetworkUtility {
    private static final String NEWS_API_URL="https://newsapi.org/v1/articles";
    private static final String format = "json";
    static final String source="the-next-web";
    static final String apikey="610d727b342944118b80a3ce3cfdaf19";
    static final String sortby="latest";
    static final String PARAM_SOURCE="source";
    static final String PARAM_API="apiKey";
    static final String PARAM_SORT="sortBy";

    public static URL buildUrl()
    {
        Uri builtUri= Uri.parse(NEWS_API_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE,source)
                .appendQueryParameter(PARAM_SORT,sortby)
                .appendQueryParameter(PARAM_API,apikey).build();

        URL url=null;
        try{
            url=new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
    public static List<NewsItem> parseJSON(String json){
        ArrayList<NewsItem> newsItems = new ArrayList<> ();

        try{

            JSONObject root= new JSONObject(json);
            JSONArray articles= root.getJSONArray("articles");
            for (int i = 0; i < articles.length() ; i++) {
                JSONObject article=articles.getJSONObject(i);
                String url = article.getString("url");
                String title = article.getString("title");
                String author = article.getString("author");
                String imageUrl= article.getString("urlToImage");
                String description = article.getString("description");
                String datetime = article.getString("publishedAt");
                String publishedAt = datetime.substring(0,10)+" "+datetime.substring(12,datetime.length()-1);
                Log.i("publishedAt :  ",publishedAt);
                newsItems.add(new NewsItem(url, title, description,imageUrl, author, publishedAt));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return newsItems;
    }
}
