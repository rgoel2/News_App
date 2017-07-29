package com.example.rohangoel.newsapp.Data;

import android.provider.BaseColumns;

/**
 * Created by rohangoel on 7/28/17.
 */
//TODO Creating Contract to Define Table and Column Name
public class Contract {

    public static class TABLE_NEWS implements BaseColumns {
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NAME_URL= "url";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_URL = "imageUrl";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_PUBLISHED_AT = "publishedAt";
    }
}
