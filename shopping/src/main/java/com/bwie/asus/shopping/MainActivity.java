package com.bwie.asus.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CheckBox checkBox;
    private TextView tv_price;
    private Button jiesuan;

    private List<Bean> list;

    private int count = 0;
    private int nums = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        tv_price = (TextView) findViewById(R.id.tv_price);
        jiesuan = (Button) findViewById(R.id.jiesuan);

        EventBus.getDefault().register(this);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Bean("我是第"+i+"条数据",500+i,false));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = checkBox.isChecked();
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setB(checked);
                    if (checked){
                        count++;
                        nums += list.get(i).getPrice();
                    }
                }

                tv_price.setText("￥"+nums);
                jiesuan.setText("结算("+count+")");


                nums = 0;
                count = 0;

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                MyAdapter adapter = new MyAdapter(MainActivity.this,list);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @Subscribe
    public void onEvent(EventBean bean){
        boolean all = bean.is_all();
        list = bean.getList();

        checkBox.setChecked(all);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getB()){
                count++;
                nums += list.get(i).getPrice();
            }
        }

        tv_price.setText("￥"+nums);
        jiesuan.setText("结算("+count+")");

        nums = 0;
        count = 0;

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        MyAdapter adapter = new MyAdapter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);

    }
}
