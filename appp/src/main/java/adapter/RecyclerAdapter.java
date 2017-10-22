package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.taobao.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import bean.BigBanner;
import tools.ImageLoaderBanner;
import tools.MyApplication;

/**
 * Created by lenovo on 2017/10/8.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    private final BigBanner bigBanner;
    private final Context context;
    LayoutInflater inflater;

    public RecyclerAdapter(Context context, BigBanner bigBanner) {
        this.context = context;
        this.bigBanner = bigBanner;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                View view = inflater.inflate(R.layout.fragment1_one_bundle,parent ,false);
                BannerViewHolder bannerViewHolder = new BannerViewHolder(view);
                return bannerViewHolder;
            case 1:
                View view1 = inflater.inflate(R.layout.fragment1_two, parent, false);
                TwoViewHolder twoViewHolder = new TwoViewHolder(view1);
                return twoViewHolder;
            case 2:
                View view2 = inflater.inflate(R.layout.fragment1_three, parent, false);
                ThreeViewHolder threeViewHolder = new ThreeViewHolder(view2);
                return threeViewHolder;
            case 3:
                View view3 = inflater.inflate(R.layout.fragment1_four, parent, false);
                FourViewHolder fourViewHolder = new FourViewHolder(view3);
                return fourViewHolder;
            case 4:
                View view4 = inflater.inflate(R.layout.fragment1_five, parent, false);
                FiveViewHolder fiveViewHolder = new FiveViewHolder(view4);
                return fiveViewHolder;
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BannerViewHolder){
            ((BannerViewHolder)holder).banner.setImageLoader(new ImageLoaderBanner());
            List<BigBanner.DataBean.Ad1Bean> ad_list = bigBanner.getData().getAd1();
            List<String> imageList = new ArrayList<>();
            for (int i = 0; i < ad_list.size(); i++) {
                imageList.add(ad_list.get(i).getImage());
            }
            ((BannerViewHolder)holder).banner.setImages(imageList);
            ((BannerViewHolder)holder).banner.start();
        }else if(holder instanceof TwoViewHolder){
            TwoGridViewAdapter twoViewHolder = new TwoGridViewAdapter(context,bigBanner.getData().getAd5());
            ((TwoViewHolder)holder).gridview_two.setAdapter(twoViewHolder);
        }else if(holder instanceof ThreeViewHolder){
            List<BigBanner.DataBean.Ad8Bean> ad8 = bigBanner.getData().getAd8();
            ((ThreeViewHolder)holder).three_title1.setText(ad8.get(0).getTitle());
            ((ThreeViewHolder)holder).three_title2.setText(ad8.get(1).getTitle());
            ((ThreeViewHolder)holder).three_title3.setText(ad8.get(2).getTitle());
            ((ThreeViewHolder)holder).three_des1.setText(ad8.get(0).getDescription());
            ((ThreeViewHolder)holder).three_des2.setText(ad8.get(1).getDescription());
            ((ThreeViewHolder)holder).three_des3.setText(ad8.get(2).getDescription());
            ImageLoader.getInstance().displayImage(ad8.get(0).getImage(),((ThreeViewHolder)holder).three_pic1, MyApplication.getOptions());
            ImageLoader.getInstance().displayImage(ad8.get(1).getImage(),((ThreeViewHolder)holder).three_pic2, MyApplication.getOptions());
            ImageLoader.getInstance().displayImage(ad8.get(2).getImage(),((ThreeViewHolder)holder).three_pic3, MyApplication.getOptions());
        }else if(holder instanceof FourViewHolder){
            ImageLoader.getInstance().displayImage(bigBanner.getData().getSubjects().get(position-3).getImage(),((FourViewHolder)holder).four_pic,MyApplication.getOptions());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((FourViewHolder)holder).four_recycler.setLayoutManager(linearLayoutManager);
            List<BigBanner.DataBean.SubjectsBean.GoodsListBean> goodsList = bigBanner.getData().getSubjects().get(position-3).getGoodsList();
            FourRecyclerAdapter fourRecyclerAdapter = new FourRecyclerAdapter(context,goodsList);
            ((FourViewHolder)holder).four_recycler.setAdapter(fourRecyclerAdapter);
        }else {
            List<BigBanner.DataBean.DefaultGoodsListBean> defaultGoodsList = bigBanner.getData().getDefaultGoodsList();
            FiveRecyclerAdapter fiveRecyclerAdapter = new FiveRecyclerAdapter(context,defaultGoodsList);
            ((FiveViewHolder)holder).five_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            ((FiveViewHolder)holder).five_recycler.setAdapter(fiveRecyclerAdapter);
            //item的点击事件
            fiveRecyclerAdapter.setOnItemClickListener(new FiveRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(context, "点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 4+bigBanner.getData().getSubjects().size();
    }
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else if(position == 1){
            return 1;
        }else if(position == 2){
            return 2;
        }else if(position == getItemCount()-1){
            return 4;
        }else {
            return 3;
        }
    }
}
    class BannerViewHolder extends RecyclerView.ViewHolder{
        Banner banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder{
        GridView gridview_two;
        public TwoViewHolder(View itemView) {
            super(itemView);
            gridview_two = (GridView) itemView.findViewById(R.id.gridview_two);
        }
    }
    class ThreeViewHolder extends RecyclerView.ViewHolder{
        TextView three_title1,three_title2,three_title3,three_des1,three_des2,three_des3;
        ImageView three_pic1,three_pic2,three_pic3;
        public ThreeViewHolder(View itemView) {
            super(itemView);
            three_title1 = (TextView) itemView.findViewById(R.id.three_title1);
            three_title2 = (TextView) itemView.findViewById(R.id.three_title2);
            three_title3 = (TextView) itemView.findViewById(R.id.three_title3);
            three_des1 = (TextView) itemView.findViewById(R.id.three_des1);
            three_des2 = (TextView) itemView.findViewById(R.id.three_des2);
            three_des3 = (TextView) itemView.findViewById(R.id.three_des3);
            three_pic1 = (ImageView) itemView.findViewById(R.id.three_pic1);
            three_pic2 = (ImageView) itemView.findViewById(R.id.three_pic2);
            three_pic3 = (ImageView) itemView.findViewById(R.id.three_pic3);
        }
    }
    class FourViewHolder extends RecyclerView.ViewHolder{
        RecyclerView four_recycler;
        ImageView four_pic;
        public FourViewHolder(View itemView) {
            super(itemView);
            four_pic = (ImageView) itemView.findViewById(R.id.four_pic);
            four_recycler = (RecyclerView) itemView.findViewById(R.id.four_recycler);
        }
    }
    class FiveViewHolder extends RecyclerView.ViewHolder{
        RecyclerView five_recycler;
        ImageView four_pic;
        public FiveViewHolder(View itemView) {
            super(itemView);
            five_recycler = (RecyclerView) itemView.findViewById(R.id.five_recycler);
        }
    }

