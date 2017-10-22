package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.taobao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.BigBanner;
import tools.MyApplication;

/**
 * Created by lenovo on 2017/10/8.
 */

public class TwoGridViewAdapter extends BaseAdapter {
    private final List<BigBanner.DataBean.Ad5Bean> list;
    private final Context context;

    public TwoGridViewAdapter(Context context, List<BigBanner.DataBean.Ad5Bean> list) {
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
            convertView = View.inflate(context, R.layout.fragment1_two_item,null);
            viewHolder = new ViewHolder();
            //找到控件
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.two_pic);
            viewHolder.title = (TextView) convertView.findViewById(R.id.two_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //为控件赋值
        ImageLoader.getInstance().displayImage(list.get(position).getImage(),viewHolder.pic, MyApplication.getOptions());
        viewHolder.title.setText(list.get(position).getTitle());
        return convertView;
    }
    //viewholder类
    class ViewHolder{
        private ImageView pic;
        private TextView title;
    }
}
