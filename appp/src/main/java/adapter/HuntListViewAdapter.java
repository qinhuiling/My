package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.taobao.R;

import java.util.List;

import static android.R.id.list;

/**
 * Created by lenovo on 2017/10/10.
 */

public class HuntListViewAdapter extends BaseAdapter {
    private final List<String> list;
    private final Context context;

    public HuntListViewAdapter(Context context, List<String> list) {
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
            convertView = View.inflate(context, R.layout.hunt_listview_item,null);
            viewHolder = new ViewHolder();
            //找到控件
            viewHolder.title = (TextView) convertView.findViewById(R.id.hunt_listview_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //为控件赋值
        viewHolder.title.setText(list.get(position));
        return convertView;
    }
    //viewholder类
    class ViewHolder{
        private TextView title;
    }
}
