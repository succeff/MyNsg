package activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import util.CheckTextUtil;


/**
 * 类的描述：
 * 时间：  2017/9/6.15:10
 * 姓名：chenlong
 */
public class ZhuActivity extends AppCompatActivity implements IMainView<RegisterandLoginBean> {
    private TextView back;
    /**
     * 注册
     */
    private Button mZhebut;
    private String names;
    private String passeds;
    private String nextpasseds;
    private String email;
    private MainPresenter mainPresenter;
    /**
     * 请输入账号
     */
    private EditText mUsernames;
    /**
     * 请输入密码
     */
    private EditText mPasswords;
    /**
     * 请再次输入密码
     */
    private EditText mNexpasswords;
    /**
     * 请输入邮箱地址
     */
    private EditText mEmails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhe_activity);
        initView();
        fidVeiw();
        initData();
    }


    private void fidVeiw() {
        back = (TextView) findViewById(R.id.register_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出一个对话框
                new AlertDialog.Builder(ZhuActivity.this)
                        .setTitle("确认您的选择")
                        .setMessage("返回将清空您正在输入的内容")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create()
                        .show();
            }
        });
    }

    private void initView() {
        mZhebut = (Button) findViewById(R.id.zhebut);
        mUsernames = (EditText) findViewById(R.id.usernames);
        mPasswords = (EditText) findViewById(R.id.passwords);
        mNexpasswords = (EditText) findViewById(R.id.nexpasswords);
        mEmails = (EditText) findViewById(R.id.emails);

        //注册点击按钮
        mZhebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                names = mUsernames.getText().toString();
                passeds = mPasswords.getText().toString();
                nextpasseds = mNexpasswords.getText().toString();
                email = mEmails.getText().toString();
                if(TextUtils.isEmpty(names)){
                    Toast.makeText(ZhuActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passeds)){
                    Toast.makeText(ZhuActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nextpasseds)){
                    Toast.makeText(ZhuActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(ZhuActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!passeds.equals(nextpasseds)){
                    Toast.makeText(ZhuActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!nextpasseds.equals(passeds)){
                    Toast.makeText(ZhuActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!CheckTextUtil.isEmail(email)){
                    Toast.makeText(ZhuActivity.this,"邮箱格式不正确",Toast.LENGTH_SHORT).show();
                    return;
                }
                register();
            }
        });
    }


    private void register() {
        RequestBody requestBody = new FormBody.Builder()
                .add("act", "login")
                .add("op", "register")
                .add("username", names)
                .add("password", passeds)
                .add("password_confirm", nextpasseds)
                .add("email", email)
                .add("client", "android")
                .build();
        mainPresenter.loadDataFromPostServer(Api.LINK_MOBILE_REG, RegisterandLoginBean.class, 0, requestBody);
    }

    private void initData() {
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);

    }

    @Override
    public void success(final RegisterandLoginBean bean, int code) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int beanCode = bean.getCode();
                //判断返回的code值是不是等于200  如果等于200就带着用户名跳转到登录页面
                if (beanCode == 200) {
                    Toast.makeText(ZhuActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ZhuActivity.this, LoginActivity.class);
                    intent.putExtra("username", names);
                    startActivity(intent);
                }
            }
        });
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
