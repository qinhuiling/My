package presenter;

import com.google.gson.Gson;

import bean.BigBanner;
import model.UserModel;
import view.UserView;

/**
 * Created by lenovo on 2017/10/8.
 */

public class UserPresenter {

    UserModel userModel;
    UserView userView;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        this.userModel = new UserModel();
    }
    public void showBullder(){
        userModel.loaderNetContent(new UserModel.NetShuju() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                BigBanner bigBanner = gson.fromJson(str, BigBanner.class);
                userView.setImageList(bigBanner);
            }
        });
    }
}
