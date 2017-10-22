package com.bwie.taobao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import adapter.HuntXiangQingListViewAdapter;
import bean.BigRolex;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HuntXiangQingActivity extends AppCompatActivity {


    private ImageView hunt_xiangqing_back;
    private ListView hunt_xiangqing_listview;
    private TextView hunt_xiangqing_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.huntxiangqing_main);
        //http://192.168.23.18/mobile/index.php?act=goods_class
        //http://192.168.23.18/mobile/index.php?act=goods&op=goods_list&page=100
        hunt_xiangqing_back = (ImageView) findViewById(R.id.hunt_xiangqing_back);
        hunt_xiangqing_listview = (ListView) findViewById(R.id.hunt_xiangqing_listview);
        hunt_xiangqing_tv = (TextView) findViewById(R.id.hunt_xiangqing_tv);

        Intent intent = getIntent();
        String title = intent.getStringExtra("sousuo");
        hunt_xiangqing_tv.setText(title);
        hunt_xiangqing_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HuntXiangQingActivity.this,HuntActivity.class));
                finish();
            }
        });
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.23.18/mobile/index.php?act=goods&op=goods_list&page=100").build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }
                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String result = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                Gson gson = new Gson();
                                BigRolex bigRolex = gson.fromJson(result, BigRolex.class);
                                List<BigRolex.DatasBean.GoodsListBean> goods_list = bigRolex.getDatas().getGoods_list();
                                HuntXiangQingListViewAdapter huntXiangQingListViewAdapter = new HuntXiangQingListViewAdapter(HuntXiangQingActivity.this,goods_list);
                                hunt_xiangqing_listview.setAdapter(huntXiangQingListViewAdapter);
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
