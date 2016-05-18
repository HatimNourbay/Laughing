package com.example.hatim.laughing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {



    List<ArticleItem> articleItemList;

    RecyclerView contactList;

    ArticleAdapter articleAdapter;

    private final OkHttpClient client = new OkHttpClient();

    ArrayList<ArticleItem> artList = new ArrayList<>();

    String URLToDownload = "";
    public String NeededXML = "";

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            //String message = intent.getStringExtra("message");
            Log.e("receiver","notif received");

            refreshRecyclerview();
        }
    };

    private void refreshRecyclerview() {
        Context articleContext = getApplicationContext();
    }


    public XMLParser xmlParser;
    Button butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butt = (Button)findViewById(R.id.hello_button);

        contactList = (RecyclerView) findViewById(R.id.cont_list);

        articleAdapter = new ArticleAdapter(artList);
        RecyclerView.LayoutManager aLayoutManager = new LinearLayoutManager(this);
        contactList.setLayoutManager(aLayoutManager);
        contactList.setItemAnimator(new DefaultItemAnimator());
        contactList.setAdapter(articleAdapter);

        //RSSDownload(McgURL);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("XML_RECEIVE"));
    }

    public void Click(View view) {

    }

    public void RSSDownload(String urlToDowndload)
    {
        URLToDownload = urlToDowndload;


        Request request = new Request.Builder()
                .url(URLToDownload)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    Log.i("HTTPHeaders", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                NeededXML = response.body().string();
                xmlParser = new XMLParser(new StringReader(NeededXML));
                //Log.w("finalResult",xmlParser.articles.get(3).Title);
                artList = xmlParser.articles;
                call.request();
            }

        });


    }
}
