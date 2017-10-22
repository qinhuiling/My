package com.bwie.taobao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import fragment.Fragment5;

/**
 * Created by lenovo on 2017/9/28.
 */

public class HomePageActivity extends FragmentActivity implements View.OnClickListener {

    private RadioButton sy_bt,wt_bt,xx_bt,mt_bt,car_bt;
    private FrameLayout frameLayout;
    private FragmentManager fm;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private List<RadioButton> buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_main);
        initview();
        init();
    }
    //初始化控件
    public void initview(){
        sy_bt = (RadioButton) findViewById(R.id.sy_bt);
        wt_bt = (RadioButton) findViewById(R.id.wt_bt);
        xx_bt = (RadioButton) findViewById(R.id.xx_bt);
        car_bt = (RadioButton) findViewById(R.id.car_bt);
        mt_bt = (RadioButton) findViewById(R.id.mt_bt);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        //监听事件
        sy_bt.setOnClickListener(this);
        wt_bt.setOnClickListener(this);
        xx_bt.setOnClickListener(this);
        car_bt.setOnClickListener(this);
        mt_bt.setOnClickListener(this);
    }
    public void init(){
        List<Fragment> list = new ArrayList<>();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        list.add(fragment5);
        fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        beginTransaction.replace(R.id.framelayout, fragment1).commit();
        buttonList = new ArrayList<>();
        buttonList.add(sy_bt);
        buttonList.add(wt_bt);
        buttonList.add(xx_bt);
        buttonList.add(car_bt);
        buttonList.add(mt_bt);
        sy_bt.setChecked(true);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sy_bt:
                fm.beginTransaction().replace(R.id.framelayout,fragment1).commit();
                radioButton(0);
                break;
            case R.id.wt_bt:
                fm.beginTransaction().replace(R.id.framelayout,fragment2).commit();
                radioButton(1);
                break;
            case R.id.xx_bt:
                fm.beginTransaction().replace(R.id.framelayout,fragment3).commit();
                radioButton(2);
                break;
            case R.id.car_bt:
                fm.beginTransaction().replace(R.id.framelayout,fragment4).commit();
                radioButton(3);
                break;
            case R.id.mt_bt:
                fm.beginTransaction().replace(R.id.framelayout,fragment5).commit();
                radioButton(4);
                break;
        }
    }
    public void radioButton(int index){
        for (int i = 0; i < buttonList.size(); i++) {
            RadioButton radioButton = buttonList.get(i);
            if(index == i){
                radioButton.setChecked(true);
            }else {
                radioButton.setChecked(false);
            }
        }
    }
}
