package com.example.rohangoel.newsapp;

import android.content.ClipData;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rohangoel.newsapp.Utilities.NetworkUtility;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mNewsReportTextView;
    private ProgressBar mLoadingContentBar;
    private TextView mErrorMessageTextView;
    private EditText mSearchQueryEditText;
    private TextView mNewsUrlDisplayTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchQueryEditText=(EditText) findViewById(R.id.news_search_box);
        mNewsReportTextView = (TextView) findViewById(R.id.news_search_results_json);
        mErrorMessageTextView=(TextView) findViewById(R.id.news_error_message_display);
        mLoadingContentBar=(ProgressBar) findViewById(R.id.pb_loading_indicator);
        mNewsUrlDisplayTextView=(TextView) findViewById(R.id.news_url_display);
        makeNewsSearchData();
    }
    private void makeNewsSearchData(){
        String newsSearchQuery=mSearchQueryEditText.getText().toString();
        URL newsSearchUrl= NetworkUtility.buildUrl(newsSearchQuery);
        mNewsUrlDisplayTextView.setText(newsSearchUrl.toString());
        new newsSearchQueryTask().execute(newsSearchUrl);

    }
    private void showNewsReportData(){
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
        mNewsReportTextView.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage(){
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mNewsReportTextView.setVisibility(View.INVISIBLE);
    }

    public class newsSearchQueryTask extends AsyncTask<URL,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingContentBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String newsSearchResults = null;
            try {
                newsSearchResults = NetworkUtility.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsSearchResults;
        }

        @Override
        protected void onPostExecute(String newsSearchResults) {
            mLoadingContentBar.setVisibility(View.INVISIBLE);
            if (newsSearchResults != null && !newsSearchResults.equals("")) {
                mNewsReportTextView.setText(newsSearchResults);
            } else {
                showErrorMessage();
            }
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
