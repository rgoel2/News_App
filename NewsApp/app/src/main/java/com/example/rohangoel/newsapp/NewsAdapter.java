
package com.example.rohangoel.newsapp;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rohangoel.newsapp.Data.NewsItem;

import java.util.ArrayList;

/**
 * Created by rohangoel on 6/28/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<NewsItem> mNewsData;
    private NewsAdapterOnClickHandler mClickHandler=null;
    Context context = null;

    NewsAdapter(Context context) {
        this.context = context;
    }

    public interface NewsAdapterOnClickHandler {
        void onClick(String url);
    }

    public NewsAdapter(NewsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitleTextView;
        TextView newsDescriptionTextView;
        TextView newsTimeTectView;

        public NewsViewHolder(View itemView) {
            super(itemView);

            newsTitleTextView = (TextView) itemView.findViewById(R.id.news_item_title);
            newsDescriptionTextView = (TextView) itemView.findViewById(R.id.news_item_decsription);
            newsTimeTectView = (TextView) itemView.findViewById(R.id.news_item_time);
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.newsTitleTextView.setText(mNewsData.get(position).getTitle());
        holder.newsTimeTectView.setText(mNewsData.get(position).getAuthor() + "    " + mNewsData.get(position).getPublishedAt());
        holder.newsDescriptionTextView.setText(mNewsData.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW);
                Log.d("hello", mNewsData.get(position).getImageUrl() + "jjiijiji");
                in.setData(Uri.parse(mNewsData.get(position).getImageUrl()));
                context.startActivity(in);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (null == mNewsData) return 0;
        return mNewsData.size();
    }

    public void setNewsData(ArrayList<NewsItem> newsData) {
        mNewsData = newsData;
        notifyDataSetChanged();
    }
}