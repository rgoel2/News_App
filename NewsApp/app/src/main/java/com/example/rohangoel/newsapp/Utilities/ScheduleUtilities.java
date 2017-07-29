package com.example.rohangoel.newsapp.Utilities;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.*;

/**
 * Created by rohangoel on 7/28/17.
 */
//TODO Implement Firebase JobScheduler to Update Content in a Minute.
public class ScheduleUtilities {

    private static final int SCHEDULE_INTERVAL_MINUTES = 0;
    private static final int SYNC_FLEXTIME_SECONDS = 60;
    private static final String NEWS_JOB_TAG = "news_job_tag";

    private static boolean sInitialized;

    synchronized public static void scheduleRefresh(@NonNull final Context context){
        if(sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintRefreshJob = dispatcher.newJobBuilder()
                .setService(NewsJob.class)
                .setTag(NEWS_JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(SCHEDULE_INTERVAL_MINUTES,
                        SCHEDULE_INTERVAL_MINUTES + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintRefreshJob);
        sInitialized = true;
    }
}
