package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.taobao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.BigBanner;
import bean.BigRolex;
import tools.MyApplication;

/**
 * Created by lenovo on 2017/10/8.
 */

public class HuntXiangQingListViewAdapter extends BaseAdapter {
    private final List<BigRolex.DatasBean.GoodsListBean> list;
    private final Context context;

    public HuntXiangQingListViewAdapter(Context context, List<BigRolex.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //判断从converview不为空
        if(convertView == null){
            //装换布局
            convertView = View.inflate(context, R.layout.huntxiangqing_listview_item,null);
            viewHolder = new ViewHolder();
            //找到控件
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.huntxiangqing_item_pic);
            viewHolder.title = (TextView) convertView.findViewById(R.id.huntxiangqing_item_title);
            viewHolder.price = (TextView) convertView.findViewById(R.id.huntxiangqing_item_price);
            viewHolder.address = (TextView) convertView.findViewById(R.id.huntxiangqing_item_address);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //为控件赋值
        ImageLoader.getInstance().displayImage(list.get(position).getGoods_image_url(),viewHolder.pic, MyApplication.getOptions());
        viewHolder.title.setText(list.get(position).getGoods_name());
        viewHolder.address.setText(list.get(position).getStore_name());
        viewHolder.price.setText("￥"+list.get(position).getGoods_price());
        return convertView;
    }
    //viewholder类
    class ViewHolder{
        private ImageView pic;
        private TextView title;
        private TextView price;
        private TextView address;
    }
}
