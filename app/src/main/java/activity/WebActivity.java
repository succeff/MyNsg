package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.baway.mynsg.R;

import util.Api;

import static com.baway.mynsg.R.id.webview;

/**
 * 类的描述：
 * 时间：  2017/9/6.9:20
 * 姓名：chenlong
 */

public class WebActivity extends AppCompatActivity{
    private WebView web;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        web = (WebView) findViewById(R.id.webviews);
        WebSettings webSettings = web.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        web.loadUrl(Api.LINK_MOBILE_GOODS_BODY);
    }
}
