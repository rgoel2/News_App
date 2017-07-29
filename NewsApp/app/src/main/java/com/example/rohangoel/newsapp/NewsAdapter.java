
package com.example.rohangoel.newsapp;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rohangoel.newsapp.Data.Contract;
import com.squareup.picasso.Picasso;

/**
 * Created by rohangoel on 6/28/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private NewsItemClickListener listener;
    private Context context;
    private Cursor cursor;

    NewsAdapter(Cursor cursor, NewsItemClickListener listener)
    {
        this.cursor=cursor;
        this.listener = listener;
    }

    public interface NewsItemClickListener {
        void onItemClick(Cursor cursor, int clickedItemIndex);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {

        return cursor.getCount();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView newsTitleTextView;
        TextView newsDescriptionTextView;
        TextView newsTimeTextView;
        ImageView newsImageView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsTitleTextView = (TextView) itemView.findViewById(R.id.news_item_title);
            newsDescriptionTextView = (TextView) itemView.findViewById(R.id.news_item_description);
            newsTimeTextView = (TextView) itemView.findViewById(R.id.news_item_time);
            newsImageView= (ImageView) itemView.findViewById(R.id.news_item_image);
            itemView.setOnClickListener(this);
        }
        public void bind(int position){
            cursor.moveToPosition(position);

            //TODO Setting Data from Database to RecyclerView's TextViews
            newsTitleTextView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_TITLE)));
            newsTimeTextView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_AUTHOR))
                    +" "+cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHED_AT)));
            newsDescriptionTextView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION)));

            //TODO load Image using Picasso in ImageView
            String imageURL= cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_IMAGE_URL));
            Picasso.with(context).load(imageURL).into(newsImageView);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            listener.onItemClick(cursor,position);
        }
    }
}