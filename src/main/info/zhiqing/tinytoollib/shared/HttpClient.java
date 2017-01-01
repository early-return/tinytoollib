package info.zhiqing.tinytoollib.shared;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zhiqing on 17-1-1.
 */
public class HttpClient {
    private OkHttpClient client = new OkHttpClient();
    private String cookie;

    public String get(String url) throws IOException{
        return this.get(url, null);
    }

    public String get(String url, Map<String, String> headers) throws IOException{
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if(cookie != null && !cookie.equals("")) {
            builder.header("Cookie", cookie);
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = builder.build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            System.out.println("HttpClient request failed: " + response.code());
        }
        String respCookie = response.header("Set-Cookie");
        if (respCookie != null && !respCookie.equals("")) {
            this.cookie = respCookie;
        }

        String result = response.body().string();
        return result;
    }
}
