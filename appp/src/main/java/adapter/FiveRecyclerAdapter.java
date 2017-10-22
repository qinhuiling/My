package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwie.taobao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.BigBanner;
import tools.MyApplication;

/**
 * Created by lenovo on 2017/10/9.
 */

public class FiveRecyclerAdapter extends RecyclerView.Adapter {
    private final List<BigBanner.DataBean.DefaultGoodsListBean> list;
    private final Context context;
    private List<Integer> heights;

    public FiveRecyclerAdapter(Context context, List<BigBanner.DataBean.DefaultGoodsListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment1_five_item, null);
        FiveRecyclerViewHolder fiveRecyclerViewHolder = new FiveRecyclerViewHolder(view);
        //给每个view设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意这里使用getTag方法获取position
                mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        });
        return fiveRecyclerViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FiveRecyclerViewHolder)holder).five_recycler_price.setText("￥"+list.get(position).getShop_price()+"");
        ((FiveRecyclerViewHolder)holder).five_recycler_title.setText(list.get(position).getEfficacy());
        ((FiveRecyclerViewHolder)holder).five_recycler_des.setText(list.get(position).getGoods_name());
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),((FiveRecyclerViewHolder)holder).five_recycler_pic, MyApplication.getOptions());

        ViewGroup.LayoutParams params = ((FiveRecyclerViewHolder) holder).five_recycler_pic.getLayoutParams();
        getRandomHeight(list);
        params.height = heights.get(position);//把随机的高度赋予itemView布局
        ((FiveRecyclerViewHolder) holder).five_recycler_pic.setLayoutParams(params);//把params设置给itemView布局
        //将position保存在itemView的Tag中，以便点击时进行获取
        ((FiveRecyclerViewHolder)holder).itemView.setTag(position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class FiveRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView five_recycler_title,five_recycler_price,five_recycler_des;
        ImageView five_recycler_pic;
        public FiveRecyclerViewHolder(View itemView) {
            super(itemView);
            five_recycler_pic = (ImageView) itemView.findViewById(R.id.five_recycler_pic);
            five_recycler_title = (TextView) itemView.findViewById(R.id.five_recycler_title);
            five_recycler_des = (TextView) itemView.findViewById(R.id.five_recycler_des);
            five_recycler_price = (TextView) itemView.findViewById(R.id.five_recycler_price);
        }
    }
    private void getRandomHeight(List<BigBanner.DataBean.DefaultGoodsListBean> lists) {//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int) (200 + Math.random() * 200));
        }
    }
    //通过接口回调设置item的点击事件
    public  OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
