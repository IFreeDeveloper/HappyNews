package com.example.john.Web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.happynews.R;
import com.example.john.util.OperateFavorites;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public class WebViewActivity extends AppCompatActivity {
    TwinklingRefreshLayout refreshLayout;
    String UserName;
    String Title;
    String Source;
    String ImageURL;
    String NewsURL;
    String Time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        Title = intent.getStringExtra("Title");
        NewsURL = intent.getStringExtra("NewsURL");
        UserName = intent.getStringExtra("UserName");
        Source = intent.getStringExtra("Source");
        ImageURL = intent.getStringExtra("ImageURL");
        Time = intent.getStringExtra("Time");
        TextView tv = (TextView)findViewById(R.id.text_title);
        tv.setText(Title);
        WebView web = (WebView) findViewById(R.id.webview);
        web.loadUrl(NewsURL);
        refreshLayout = (TwinklingRefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setHeaderHeight(30);
        web.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                refreshLayout.startRefresh();
            }
            public void onPageFinished(WebView view, String url) {
                refreshLayout.finishRefreshing();
                refreshLayout.setEnableRefresh(false);
            }
        });
    }
    public void btnReturn(View view){
        finish();
    }

    public void addFvorites(View view) {
        boolean isSuccess = OperateFavorites.setFavorites(UserName,Title,ImageURL,NewsURL,Time,Source);
        if(isSuccess){
            Toast.makeText(WebViewActivity.this,"Successfully add to favorites",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(WebViewActivity.this,"It has already been added",Toast.LENGTH_SHORT).show();
        }
    }
}
