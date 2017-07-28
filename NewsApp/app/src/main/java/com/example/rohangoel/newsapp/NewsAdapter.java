
package com.example.rohangoel.newsapp;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private ItemClickListener listener;
    private Context context;
    private Cursor cursor;

    NewsAdapter(Cursor cursor, ItemClickListener listener)
    {
        this.cursor=cursor;
        this.listener = listener;
    }

    public interface ItemClickListener {
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
        cursor.moveToPosition(position);

        // Setting Data to TextViews
        holder.newsTitleTextView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_TITLE)));
        holder.newsTimeTextView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_AUTHOR))
                +" "+cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_PUBLISHED_AT)));
        holder.newsDescriptionTextView.setText(cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_DESCRIPTION)));

        // Adding Image using Picasso
        String imageURL= cursor.getString(cursor.getColumnIndex(Contract.TABLE_NEWS.COLUMN_NAME_IMAGE_URL));
        Picasso.with(context).load(imageURL).into(holder.newsImageView);
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
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Log.i("Clicked at : ", Integer.toString(position));
            listener.onItemClick(cursor,position);
        }
    }
}