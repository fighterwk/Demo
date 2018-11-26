package com.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/11/23
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(3, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        for (int i = 0; i < 3; i++) {
            String url = "http://10.2.29.217:801/api/converter/text2voice?text=测试测试" + i;

            Request.Builder rb = new Request.Builder();
            Request request = rb.get().url(url)
                    .build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("失败");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        System.out.println("成功");
                    } else {
                        System.out.println("失败:" + response.code());
                    }
                }
            });
        }

        while (true) ;
    }
}
