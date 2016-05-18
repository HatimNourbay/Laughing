package com.example.hatim.laughing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailArticleActivity extends AppCompatActivity {

    WebView articleWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        articleWeb = (WebView)findViewById(R.id.webviewArticle);
        articleWeb.getSettings().setJavaScriptEnabled(true);

        String url = getIntent().getStringExtra("lien");
        articleWeb.loadUrl(url);

    }
}
