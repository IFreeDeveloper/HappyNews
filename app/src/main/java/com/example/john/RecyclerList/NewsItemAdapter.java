package com.example.john.RecyclerList;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.john.Web.WebViewActivity;
import com.example.john.happynews.R;

import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {
    private List<NewsItem> mNewsList;
    Context context;
    String username;
    public NewsItemAdapter(List<NewsItem> mNewsList, Context context,String username) {
        this.mNewsList = mNewsList;
        this.context = context;
        this.username = username;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView source;
        TextView newsURL;
        ImageView imageView;
        String imageURL;
        TextView time;
        public ViewHolder(@NonNull final View itemView, final Context context, final String username) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            source = (TextView)itemView.findViewById(R.id.source);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            newsURL = (TextView)itemView.findViewById(R.id.NewsURL);
            time = (TextView)itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context,title.getText(),Toast.LENGTH_LONG).show);
                    Intent intent = new Intent(context,WebViewActivity.class);
                    intent.putExtra("NewsURL",newsURL.getText().toString());
                    intent.putExtra("ImageURL",imageURL);
                    intent.putExtra("Title",title.getText().toString());
                    intent.putExtra("Source",source.getText().toString());
                    intent.putExtra("UserName",username);
                    intent.putExtra("Time",time.getText().toString());
                    context.startActivity(intent);
                }
            });

        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newsitem,viewGroup,false);
        ViewHolder holder = new ViewHolder(view,context,username);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsItem newsItem = mNewsList.get(i);
        Glide.with(context).load(newsItem.getImageURL()).into(viewHolder.imageView);
        viewHolder.imageURL = newsItem.getImageURL();
        viewHolder.title.setText(newsItem.getTitle());
        viewHolder.source.setText(newsItem.getSource());
        viewHolder.newsURL.setText(newsItem.getNewsURL());
        viewHolder.time.setText(newsItem.getTime());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

}
