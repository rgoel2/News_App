package com.example.rohangoel.newsapp.Utilities;
import android.os.AsyncTask;
import android.widget.Toast;
import com.firebase.jobdispatcher.JobParameters;
/**
 * Created by rohangoel on 7/28/17.
 */
public class NewsJob extends com.firebase.jobdispatcher.JobService {
    AsyncTask mBackgroundTask;
    @Override
    public boolean onStartJob(final JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                Toast.makeText(NewsJob.this, "News Refreshed", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }
            @Override
            protected Object doInBackground(Object[] params) {
                RefreshNews.refreshNews(NewsJob.this);
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
                super.onPostExecute(o);
            }
        };
        mBackgroundTask.execute();
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters job) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(false);
        return true;
    }
}
