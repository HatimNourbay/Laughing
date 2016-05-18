package com.example.hatim.laughing;

import android.util.Log;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hatim on 10/05/2016.
 */
public class RSSDownload {

    private final OkHttpClient client;

    String URLToDownload = "";
    public String NeededXML = "";

    public XMLParser xmlParser;
    public Call callResponse;

    public RSSDownload(String urlToDowndload)
    {
        client = new OkHttpClient();
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
                callResponse = call;
                call.request();
            }

        });


    }
}
