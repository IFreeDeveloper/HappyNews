package com.example.john.util;

import android.content.Context;
import com.example.john.RecyclerList.NewsItem;

import org.litepal.LitePal;
import java.util.ArrayList;
import java.util.List;

public class OperateBuffer{


    public static boolean saveBuffer(String channel, String imageURL, String newsURL, String title, String source, String time){
        List<Buffer> BufferList = LitePal.where("NewsURL == ?",newsURL).find(Buffer.class);
        if(BufferList.isEmpty()){
            Buffer buffer = new Buffer();
            buffer.setChannel(channel);
            buffer.setImageURL(imageURL);
            buffer.setNewsURL(newsURL);
            buffer.setTitle(title);
            buffer.setSource(source);
            buffer.setTime(time);
            if(buffer.save()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static List<NewsItem>getBuffer(String channel){
        List<Buffer> BufferList = LitePal.where("channel == ?",channel).find(Buffer.class);
        List<NewsItem> newsItems = new ArrayList<>();
        for(int i=0;i<BufferList.size();i++){
            Buffer buffer = BufferList.get(i);
            NewsItem newsItem = new NewsItem(buffer.getImageURL(),
                    buffer.getNewsURL(),
                    buffer.getTitle(),
                    buffer.getSource(),
                    buffer.getTime());
            newsItems.add(newsItem);
        }
        return newsItems;
    }

}