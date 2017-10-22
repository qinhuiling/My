package tools;

import android.app.Application;

import com.bwie.taobao.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by lenovo on 2017/10/9.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800) //即保存的每个缓存文件的最大长宽
                .threadPriority(Thread.NORM_PRIORITY - 2) //线程池中线程的个数
                .denyCacheImageMultipleSizesInMemory() //禁止缓存多张图片
                .memoryCache(new LRULimitedMemoryCache(40*1024*1024)) //缓存策略
                .memoryCacheSize(50 * 1024 * 1024) //设置内存缓存的大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //缓存文件名的保存方式
                .diskCacheSize(200 * 1024 * 1024) //磁盘缓存大小
                .diskCacheFileCount(200) //缓存的文件数量
                .build();//开始构建
        //构建加载图片配置
        ImageLoader.getInstance().init(config);
    }
    public static DisplayImageOptions getOptions(){
        //加载图片
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //配置下载网络图片失败的图片
                .showImageOnFail(R.mipmap.ic_launcher)
                //配置一个正在加载图片时候显示的一张默认图片
                .showImageOnLoading(R.mipmap.ic_launcher)
                //当图片地址为空的时候，会显示你默认配置的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .build();
        return options;
    }
}
