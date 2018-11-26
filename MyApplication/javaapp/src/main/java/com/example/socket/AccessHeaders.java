package com.example.socket;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class AccessHeaders {
  private  OkHttpClient client = new OkHttpClient();

  public void run() throws Exception {
    client = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    Request request = new Request.Builder()
//        .url("https://api.github.com/repos/square/okhttp/issues")
            .url("http://127.0.0.1:8989")
        .header("User-Agent", "OkHttp Headers.java")
        .addHeader("Accept", "application/json; q=0.5")
        .addHeader("Accept", "application/vnd.github.v3+json")
        .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

      System.out.println("Server: " + response.header("Server"));
      System.out.println("Date: " + response.header("Date"));
      System.out.println("Vary: " + response.headers("Vary"));
    }
  }

  public static void main(String... args) throws Exception {
    new AccessHeaders().run();
  }
}