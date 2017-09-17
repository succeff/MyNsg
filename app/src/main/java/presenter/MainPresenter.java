package presenter;

import okhttp3.RequestBody;
import util.HttpUtil;
import util.NetDataCallBack;


public class MainPresenter extends BasePresenter<IMainView> implements NetDataCallBack {

    private int code;
    private HttpUtil httpUtil;

    public MainPresenter() {
        httpUtil = new HttpUtil();
    }

    public <T> void loadDataFromGetServer(String url, Class<T> t, int code) {
        this.code = code;
        httpUtil.initDatafromGetServer(url,this,t);
    }


    public <T> void loadDataFromPostServer(String url, Class<T> t, int code, RequestBody requestBody) {
        this.code = code;
        httpUtil.initDatafromPostServer(url,this,t,requestBody);
    }

    @Override
    public void success(Object o) {
        getMvpView().success(o,code);
    }

    @Override
    public void error(String error) {

    }
}
