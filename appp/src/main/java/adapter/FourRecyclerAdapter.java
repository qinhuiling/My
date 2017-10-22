package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwie.taobao.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;
import bean.BigBanner;
import tools.MyApplication;

/**
 * Created by lenovo on 2017/10/9.
 */

public class FourRecyclerAdapter extends RecyclerView.Adapter{
    private final List<BigBanner.DataBean.SubjectsBean.GoodsListBean> list;
    private final Context context;

    public FourRecyclerAdapter(Context context, List<BigBanner.DataBean.SubjectsBean.GoodsListBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fragment1_four_item, null);
        FourRecyclerViewHolder fourRecyclerViewHolder = new FourRecyclerViewHolder(view);
        return fourRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FourRecyclerViewHolder)holder).four_recycler_price.setText("￥"+list.get(position).getShop_price()+"");
        ((FourRecyclerViewHolder)holder).four_recycler_title.setText(list.get(position).getGoodsName());
        ImageLoader.getInstance().displayImage(list.get(position).getGoodsImage(),((FourRecyclerViewHolder)holder).four_recycler_pic, MyApplication.getOptions());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
    class FourRecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView four_recycler_title,four_recycler_price;
        ImageView four_recycler_pic;

        public FourRecyclerViewHolder(View itemView) {
            super(itemView);
            four_recycler_pic = (ImageView) itemView.findViewById(R.id.four_recycler_pic);
            four_recycler_title = (TextView) itemView.findViewById(R.id.four_recycler_title);
            four_recycler_price = (TextView) itemView.findViewById(R.id.four_recycler_price);
        }
    }