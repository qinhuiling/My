package com.bwie.taobao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import adapter.HuntListViewAdapter;
import tools.ToolsSqlite;

public class HuntActivity extends AppCompatActivity {

    private ImageView hunt_back;
    private EditText hunt_ed;
    private ListView hunt_listview;
    private TextView hunt_sousuo;
    private ToolsSqlite toolsSqlite;
    private List<String> list;
    private String title;
    private boolean flag;
    private Button hunt_clear;
    private HuntListViewAdapter huntListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hunt_main);
        hunt_back = (ImageView) findViewById(R.id.hunt_back);
        hunt_ed = (EditText) findViewById(R.id.hunt_ed);
        hunt_listview = (ListView) findViewById(R.id.hunt_listview);
        hunt_sousuo = (TextView) findViewById(R.id.hunt_sousuo);
        hunt_clear = (Button) findViewById(R.id.hunt_clear);

        toolsSqlite = new ToolsSqlite(this);
        list = toolsSqlite.selectHunt();
        hunt_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = hunt_ed.getText().toString().trim();
                if(!TextUtils.isEmpty(title)){
                    if(list.size() != 0){
                        for (int i = 0; i < list.size(); i++) {
                            if(title.equals(list.get(i))){
                                flag = true;
                            }
                        }
                        if(flag == false){
                            toolsSqlite.addHunt(title);
                        }
                    }else {
                        toolsSqlite.addHunt(title);
                    }
                }
                Intent intent = new Intent(HuntActivity.this, HuntXiangQingActivity.class);
                intent.putExtra("sousuo",title);
                startActivity(intent);
            }
        });
        huntListViewAdapter = new HuntListViewAdapter(this,list);
        hunt_listview.setAdapter(huntListViewAdapter);
        hunt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HuntActivity.this,HomePageActivity.class));
                finish();
            }
        });
        hunt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolsSqlite.deleteHunt(0,"");
                list = toolsSqlite.selectHunt();
                huntListViewAdapter = new HuntListViewAdapter(HuntActivity.this,list);
                hunt_listview.setAdapter(huntListViewAdapter);
            }
        });
        hunt_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(HuntActivity.this);
                dialog.setMessage("确认删除该历史记录？");
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toolsSqlite.deleteHunt(1,list.get(position));
                        list = toolsSqlite.selectHunt();
                        huntListViewAdapter = new HuntListViewAdapter(HuntActivity.this,list);
                        hunt_listview.setAdapter(huntListViewAdapter);
                    }
                });
                dialog.create().show();
                return true;
            }
        });
        hunt_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hunt_ed.setText(list.get(position));
                Intent intent = new Intent(HuntActivity.this, HuntXiangQingActivity.class);
                intent.putExtra("sousuo",list.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
