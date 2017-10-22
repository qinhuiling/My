package model;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/10/8.
 */

public class UserModel {

    public interface NetShuju{
        void success(String str);
    };
    public void loaderNetContent(final NetShuju netShuju){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient httpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("http://m.yunifang.com/yunifang/mobile/home").build();
                    Call call = httpClient.newCall(request);
                    Response execute = call.execute();
                    if(execute.isSuccessful()){
                        String result = execute.body().string();
                        netShuju.success(result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
