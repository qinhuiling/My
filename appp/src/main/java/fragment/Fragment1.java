package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.taobao.HuntActivity;
import com.bwie.taobao.R;
import com.yalantis.phoenix.PullToRefreshView;

import adapter.RecyclerAdapter;
import bean.BigBanner;
import presenter.UserPresenter;
import view.UserView;

/**
 * Created by lenovo on 2017/9/28.
 */

public class Fragment1 extends Fragment implements UserView,View.OnClickListener{

    private static final long REFRESH_DELAY = 2000;
    private View view;
    private PullToRefreshView mPullToRefreshView;
    private RecyclerView recyclerView;
    private LinearLayout shouye_head;
    private ImageView sousuo_pic;
    private EditText sousuo_content;

    //http://i.haijin.com/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view !=null){
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if(viewGroup!=null){
                viewGroup.removeView(view);
            }
        }else {
            view = inflater.inflate(R.layout.fragment1_main, null);
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        shouye_head = (LinearLayout) view.findViewById(R.id.shouye_head);
        sousuo_pic = (ImageView) view.findViewById(R.id.sousuo_pic);
        sousuo_content = (EditText) view.findViewById(R.id.sousuo_content);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //刷新
        shuxin();
        //数据展示
        showView();
        sousuo_pic.setOnClickListener(this);
        sousuo_content.setOnClickListener(this);
    }
    //刷新
    public void shuxin(){
        mPullToRefreshView = (PullToRefreshView)view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shouye_head.setVisibility(View.GONE);
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        shouye_head.setVisibility(View.VISIBLE);
                    }
                }, REFRESH_DELAY);
            }
        });
    }
    //数据展示
    public void showView(){
        UserPresenter presenter = new UserPresenter(this);
        presenter.showBullder();
    }

    @Override
    public void setImageList(final BigBanner bigBanner) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),bigBanner);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sousuo_content:
                startActivity(new Intent(getContext(), HuntActivity.class));
                break;
        }
    }
}
