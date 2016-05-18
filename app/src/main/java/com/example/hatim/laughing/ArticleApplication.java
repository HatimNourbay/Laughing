package com.example.hatim.laughing;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hatim on 11/05/2016.
 */
public class ArticleApplication extends Application implements Application.ActivityLifecycleCallbacks {

    OkHttpClient client = new OkHttpClient();

    public ArrayList<ArticleItem> articleItems;

    String McgURL = "http://www.macg.co/news/feed";

    @Override
    public void onCreate(){
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

    }

    private void retrieveRSSXML(String URLToDownload) throws IOException{
        Request request = new Request.Builder()
                .url(URLToDownload)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                String xmlStringResponse = response.body().string();
                StringReader reader = new StringReader(xmlStringResponse);
                XMLParser xmlParser = new XMLParser(reader);
                articleItems =  xmlParser.articles;
                Intent intent = new Intent("XML_RECEIVE");
                LocalBroadcastManager.getInstance(ArticleApplication.this).sendBroadcast(intent);
            }

        });
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

        if (activity instanceof MainActivity){
            try{
                retrieveRSSXML(McgURL);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
