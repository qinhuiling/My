package com.bwie.asus.shopping;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ASUS on 2017/10/19.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Bean> list;

    public MyAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recycler_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ViewHolder viewHolder = new ViewHolder(holder.itemView);
        viewHolder.check.setChecked(list.get(position).getB());
        //Picasso.with(context).load(list.get(position).getGoodsPic()).into(viewHolder.iv);
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.price.setText(list.get(position).getPrice()+"");

        viewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setB(!list.get(position).getB());

                boolean bo = true;

                for (int i = 0; i < list.size(); i++) {
                    if (!list.get(i).getB()){
                        bo = false;
                    }
                }

                EventBus.getDefault().post(new EventBean(bo,list));

                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox check;
        private final ImageView iv;
        private final TextView name;
        private final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);

            check = itemView.findViewById(R.id.check);
            iv = itemView.findViewById(R.id.iv);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }
    }

}
