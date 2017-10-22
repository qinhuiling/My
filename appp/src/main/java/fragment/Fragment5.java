package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.taobao.R;

/**
 * Created by lenovo on 2017/9/28.
 */

public class Fragment5 extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view !=null){
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if(viewGroup!=null){
                viewGroup.removeView(view);
            }
        }else {
            view = inflater.inflate(R.layout.fragment5_main, null);
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("555555555555555555555");
    }
}
