package com.example.john.happynews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.codingending.library.FairySearchView;
import com.example.john.Fragment.DataFragment;
import com.example.john.Fragment.MyPagerAdapter;
import com.example.john.RecyclerList.NewsItem;
import com.example.john.util.ArticlesBean;
import com.example.john.util.DragSortDialog;
import com.example.john.util.OperateUserAccount;
import com.example.john.util.User;
import com.ogaclejapan.smarttablayout.SmartTabLayout;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class NewsActivity extends AppCompatActivity {

    private String theme = "";
    private List<String> datas = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter adapter;
    private List<NewsItem>newsItems = new ArrayList<>();
    private ViewPager viewPager;
    private Handler mHandler;
    private String username=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final Intent intent = getIntent();
        if(intent.hasExtra("username")) {
            username = intent.getStringExtra("username");
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.item_channel:
                        showChannelDialog();
                        break;
                    case R.id.item_favorites:
                        Intent favorites = new Intent(NewsActivity.this,FavoritesActivity.class);
                        favorites.putExtra("UserName",username);
                        startActivity(favorites);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        View headerView = navigationView.getHeaderView(0);
        if(username!=null) {
            ((TextView)headerView.findViewById(R.id.username)).setText(username);
        }
        FairySearchView fairySearchView = (FairySearchView)findViewById(R.id.search_view);
        fairySearchView.setOnEnterClickListener(new FairySearchView.OnEnterClickListener() {
            @Override
            public void onEnterClick(String s) {
                DataFragment fragment = (DataFragment) fragments.get(fragments.size()-1);
                fragment.setSearch(s);
                viewPager.setCurrentItem(fragments.size()-1);
                adapter.notifyDataSetChanged();

            }
        });
        String channel[] = OperateUserAccount.getChannel(username).split(",");
        for(int i=0;i<channel.length;i++){
            datas.add(channel[i]);
        }
        datas.add("search");

        for (int i = 0; i < datas.size(); i++) {
            fragments.add(DataFragment.newInstance(i,datas.get(i),username));
        }

        adapter = new MyPagerAdapter(getSupportFragmentManager(), datas, fragments);

        viewPager.setAdapter(adapter);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initNewsItem(List<ArticlesBean>articlesBeans){
        for(int i=0;i<articlesBeans.size();i++){
            ArticlesBean articlesBean = articlesBeans.get(i);
            NewsItem newsItem = new NewsItem(articlesBean.getUrlToImage(),articlesBean.getUrl(),articlesBean.getTitle(),articlesBean.getSource().getId(),username);
            newsItems.add(newsItem);
        }
    }


    public void showChannelDialog() {
        if(username==null) return;
        DragSortDialog dialog = new DragSortDialog(this);
        String channel[] = OperateUserAccount.getChannel(username).split(",");
        dialog.setTopItemViews(channel);
        //dialog.setTopItemViews("OPQRST".split("\\B"));
        String temp = getRestChannel(channel);
        String [] nullString = null;
        if(temp==null){
            dialog.setBottomItemViews(nullString);
        }
        else{
            dialog.setBottomItemViews(temp.split(","));
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                List<String> list = ((DragSortDialog) dialog).getTopDefaultItemViews();
                StringBuffer temp = new StringBuffer();
                for (int i=0;i<list.size();i++) {
                    temp.append(list.get(i).toString());
                    if(i!=list.size()-1){
                        temp.append(",");
                    }
                }
                OperateUserAccount.setChannel(username,temp.toString());
            }
        });
        dialog.show();
    }
    public String getRestChannel(String [] s){
        HashMap<Integer,String>map =new HashMap<Integer, String>();
        for(int i=0;i<s.length;i++){
            map.put(i,s[i]);
        }
        String temp[] = {"bussiness","entertainment","general","health","science","sports","technology"};
        int number = 0;
        Vector<String>vector = new Vector<>();
        for(int i=0;i<temp.length;i++){
            if(!map.containsValue(temp[i])){
                vector.add(temp[i]);
            }
        }
        if(vector.size()==0){
            return null;
        }
        StringBuffer tempString = new StringBuffer();
        for (int i=0;i<vector.size();i++) {
            tempString.append(vector.get(i).toString());
            if(i!=vector.size()-1){
                tempString.append(",");
            }
        }
        return tempString.toString();
    }
}
