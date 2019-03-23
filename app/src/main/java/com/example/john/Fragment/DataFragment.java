package com.example.john.Fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.john.RecyclerList.NewsItem;
import com.example.john.RecyclerList.NewsItemAdapter;
import com.example.john.util.ArticlesBean;
import com.example.john.util.NewsAPI;
import com.example.john.happynews.R;
import com.example.john.util.OperateBuffer;
import com.example.john.util.TopHeadlinesBean;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class DataFragment extends Fragment{
    public static final String ARGS_PAGE = "args_page";
    private int mNews = 0;
    private int newsPage = 1;
    private int mPage;
    private RecyclerView newsList;
    private NewsItemAdapter adapter;
    private List<NewsItem>newsItems = new ArrayList<>();
    private String title;
    private String search="";
    private String username;
    private String imageURL;
    private TwinklingRefreshLayout refreshLayout;
    private ConstraintLayout information;
    @SuppressLint("ValidFragment")
    public DataFragment(String title,String username) {
        this.title = title;
        this.username = username;
    }
    public DataFragment(){

    }

    public static DataFragment newInstance(int page,String t,String username) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        DataFragment fragment = new DataFragment(t,username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.page, container, false);
        newsItems.clear();
        newsList = rootView.findViewById(R.id.newslist);
        information = rootView.findViewById(R.id.information);
        newsList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        adapter = new NewsItemAdapter(newsItems,getContext(),username,title);
        newsList.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        newsList.setLayoutManager(layoutManager);
        refreshLayout = (TwinklingRefreshLayout)rootView.findViewById(R.id.refreshLayout);
        refreshLayout.startRefresh();
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                information.setVisibility(View.GONE);
                newsItems.clear();
                mNews = 0;
                newsPage = 1;
                new DownloadUpdate().execute("");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new DownloadUpdate().execute("");
            }
        });
        return rootView;
    }

    public RecyclerView getNewsList(){
        return newsList;
    }

    public NewsItemAdapter getAdapter() {
        return adapter;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection urlConnection = null;
            BufferedReader reader;
            NewsAPI newsAPI = new NewsAPI("us", title, "",search, "10", String.valueOf(newsPage));
            Message msg = new Message();
            Bundle bundle = new Bundle();
            try {
                if(title.equals("search")){
                    if(!search.equals("")) {
                        url = new URL(newsAPI.getEverythingNews());
                    }
                    else{
                        return "NoSearch";
                    }
                }
                else {
                    url = new URL(newsAPI.getTopHeadlinesNews());
                }
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(2000);
                urlConnection.setReadTimeout(2000);
                urlConnection.connect();
                int code = urlConnection.getResponseCode();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    Log.d("TAG", line);
                    buffer.append(line + "\n");
                }
                String contents = buffer.toString();
                Gson gson = new Gson();
                TopHeadlinesBean tb = gson.fromJson(contents, TopHeadlinesBean.class);
                if(tb.getTotalResults()<=mNews){
                    return "NoMore";
                }
                return buffer.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
                Toast.makeText(getContext(),"Failed to get data,please try again",Toast.LENGTH_SHORT).show();
                List<NewsItem>temp = OperateBuffer.getBuffer(title);
                for (int i = 0; i < temp.size(); i++) {
                    newsItems.add(temp.get(i));
                }
                adapter.notifyDataSetChanged();
                return;
            }
            if(result.equals("NoMore")){
                Toast.makeText(getContext(),"No more News",Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
                return;
            }
            if(result.equals("NoSearch")){
                //Toast.makeText(getContext(),"Please input keywords first",Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
                return;
            }
            else {
                Gson gson = new Gson();
                TopHeadlinesBean tb = gson.fromJson(result, TopHeadlinesBean.class);
                List<ArticlesBean> articlesBeans = tb.getArticles();
                mNews+=articlesBeans.size();
                newsPage++;
                for (int i = 0; i < articlesBeans.size(); i++) {
                    ArticlesBean articlesBean = articlesBeans.get(i);
                    NewsItem newsItem = new NewsItem(articlesBean.getUrlToImage(), articlesBean.getUrl(), articlesBean.getTitle(), articlesBean.getSource().getId(),articlesBean.getPublishedAt());
                    newsItems.add(newsItem);
                }
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefreshing();
                refreshLayout.finishLoadmore();
            }
        }
    }
}

