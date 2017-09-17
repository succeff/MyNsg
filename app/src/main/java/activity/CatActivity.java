package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.mynsg.R;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import Bean.ShouBean;

import adapter.ShouAdapter;

import presenter.IMainView;
import presenter.MainPresenter;
import sqliter.ShopCat;
import util.Api;

/**
 * 类的描述：
 * 时间：  2017/9/5.16:04
 * 姓名：chenlong
 */

public class CatActivity extends AppCompatActivity implements IMainView<ShouBean> {
    private ImageView imaed;
    private TextView title, mon, count, conts, webadata;
    private ListView mListCat;
    private List<ShouBean.DatasBean.GoodsListBean> list_show = new ArrayList<>();
    private MainPresenter mainPresenter;
    private final int LIST1 = 1;
    private ShouAdapter adapter;
    private TextView catadd;
    private TextView backed;
    private ImageView  mImage;
    private ShopCat shopCat;
    /**
     * agdgagddgdg
     */
    private TextView mTitle;
    /**
     * 13132
     */
    private TextView mMonty;
    private String ima;
    private String titl;
    private String mone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_actitivy);
        initView();
        lint();
        webInit();
        loadData();



    }

    private void webInit() {
        webadata = (TextView) findViewById(R.id.webview);
        backed = (TextView) findViewById(R.id.backs);
        shopCat = new ShopCat(this);
        webadata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
        catadd = (TextView) findViewById(R.id.addcat);
        catadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(CatActivity.this, LoginActivity.class);
//                startActivity(intent);
//                if(intent!=null){
//                    Toast.makeText(CatActivity.this,"未登录，请先登录账号",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(CatActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
//                }
                boolean b = shopCat.add(ima, titl, mone);
                if(b){
                    Toast.makeText(CatActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CatActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                }


            }

        });
        backed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private void lint() {
        imaed = (ImageView) findViewById(R.id.imaeds);
        title = (TextView) findViewById(R.id.titleds);
        mon = (TextView) findViewById(R.id.money);
        count = (TextView) findViewById(R.id.count);
        conts = (TextView) findViewById(R.id.cont);
        Intent intent = getIntent();
        ima = intent.getStringExtra("image");
        Glide.with(CatActivity.this).load(ima).into(imaed);
        titl = intent.getStringExtra("title");
        title.setText(titl);
        String cont = intent.getStringExtra("cont");
        conts.setText(cont);
        mone = intent.getStringExtra("money");
        mon.setText("¥" + mone);
        String count1 = intent.getStringExtra("count");
        count.setText("销量" + count1 + "件");

    }


    private void initView() {
        mListCat = (ListView) findViewById(R.id.list_cat);
        adapter = new ShouAdapter(CatActivity.this, list_show);
        mListCat.setAdapter(adapter);

    }

    private void loadData() {
        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        list_show.clear();
        mainPresenter.loadDataFromGetServer(Api.LINK_MOBILE_GOODS_SEARCH, ShouBean.class, LIST1);
    }


    @Override
    public void success(ShouBean shouBean, int code) {
        if (shouBean != null) {
            switch (code) {
                case LIST1:
                    list_show.addAll(shouBean.getDatas().getGoods_list());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void error(String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.dettachView();
    }

    public void setPopParams(View popParams) {
        mImage = (ImageView) findViewById(R.id.image);
        mTitle = (TextView) findViewById(R.id.title);
        mMonty = (TextView) findViewById(R.id.monty);
    }


}
