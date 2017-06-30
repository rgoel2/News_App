package com.example.rohangoel.newsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.rohangoel.newsapp.Data.NewsItem;
import com.example.rohangoel.newsapp.Utilities.NetworkUtility;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ArrayList articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(this);
        recyclerView.setAdapter(newsAdapter);


    }
    private void makeNewsSearchData(){
        URL newsSearchUrl= NetworkUtility.buildUrl();
        new newsSearchQueryTask().execute(newsSearchUrl);
    }

    public class newsSearchQueryTask extends AsyncTask<URL,Void,ArrayList<NewsItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading data...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected ArrayList<NewsItem> doInBackground(URL... params) {
            String jsonNewsResults;
            List<NewsItem> results = null;
            try {
                jsonNewsResults = NetworkUtility.getResponseFromHttpUrl(params[0]);
                results = NetworkUtility.parseJSON(jsonNewsResults);
            }catch (Exception e){
                e.printStackTrace();
            }
            return (ArrayList<NewsItem>) results;
        }

        @Override
        protected void onPostExecute(final ArrayList<NewsItem> newsSearchResults) {
            if (newsSearchResults != null)
                newsAdapter.setNewsData(newsSearchResults);
            progressDialog.dismiss();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeNewsSearchData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
