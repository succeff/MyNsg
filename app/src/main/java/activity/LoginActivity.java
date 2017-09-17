package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.mynsg.R;

import Bean.RegisterandLoginBean;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import presenter.IMainView;
import presenter.MainPresenter;
import util.Api;

/**
 * 类的描述：
 * 时间：  2017/9/6.9:21
 * 姓名：chenlong
 */
public class LoginActivity extends AppCompatActivity implements IMainView<RegisterandLoginBean> {

    /**
     * 请输入账号
     */
    private EditText mUsername;
    /**
     * 请输入密码
     */
    private EditText mPassword;
    /**
     * 登录
     */
    private Button mLogin;
    /**
     * 注册账号
     */
    private TextView mZhc;
    /**
     * 找回密码
     */
    private TextView mForget;
    private String namese;
    private MainPresenter mainPresenter;
    private TextView tv_backe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();
        initData();
    }

    private void initData() {
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        Intent intent = getIntent();
        if(intent!=null){
            namese = intent.getStringExtra("username");
            mUsername.setText(namese);
        }
        }


    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mZhc = (TextView) findViewById(R.id.zhc);
        mForget = (TextView) findViewById(R.id.forget);
        tv_backe = (TextView) findViewById(R.id.deng_back);
        mZhc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ZhuActivity.class);
                startActivity(intent);
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogin.setText("登录中...");
                login();
            }
        });
        tv_backe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void login() {
        String s1 = mUsername.getText().toString();
        String s = mPassword.getText().toString();
        if(TextUtils.isEmpty(s1)){
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(s)){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        RequestBody requestBody = new FormBody.Builder()
                .add("act", "login")
                .add("username", s1)
                .add("password", s)
                .add("client", "android")
                .build();
        mainPresenter.loadDataFromPostServer(Api.LINK_lOGIN, RegisterandLoginBean.class, 1, requestBody);
    }

    @Override
    public void success(RegisterandLoginBean bean, int code) {
        int code1 = bean.getCode();
        if (code1==200){
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, CatActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void error(String message) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.dettachView();
    }
}
