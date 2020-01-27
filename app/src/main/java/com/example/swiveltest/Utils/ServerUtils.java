package com.example.swiveltest.Utils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class ServerUtils {
    private OkHttpClient client;
    private String url;
    private Callback callback;

    public ServerUtils(String url, Callback callback) {
        this.client = new OkHttpClient();
        this.url = url;
        this.callback = callback;
        sendHttpRequest(url, callback);
    }

    public void sendHttpRequest(final String url, final Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        builder.url(url);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
