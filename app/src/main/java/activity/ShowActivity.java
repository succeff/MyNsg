package activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.mynsg.R;

import java.util.ArrayList;
import java.util.List;

import Bean.ShouBean;
import adapter.ShouAdapter;
import presenter.IMainView;
import presenter.MainPresenter;
import util.Api;

import static java.security.AccessController.getContext;

/**
 * 类的描述：
 * 时间：  2017/9/5.14:24
 * 姓名：chenlong
 */

public class ShowActivity extends AppCompatActivity implements IMainView<ShouBean> {
    private ListView listview_shou;
    private List<ShouBean.DatasBean.GoodsListBean> list_show = new ArrayList<>();
    private ShouAdapter shouAdapter;
    private MainPresenter mainPresenter;
    private final int LIST1 = 1;
    private TextView ed_texts;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shou_activity);
        loadData();
        init();
    }

    private void init() {
        listview_shou = (ListView) findViewById(R.id.shou_list);
        shouAdapter = new ShouAdapter(ShowActivity.this,list_show);
        ed_texts = (TextView) findViewById(R.id.ed_text);
        listview_shou.setAdapter(shouAdapter);
        listview_shou.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShowActivity.this, CatActivity.class);
                intent.putExtra("image",list_show.get(i).getGoods_image_url());
                intent.putExtra("cont",list_show.get(i).getGoods_jingle());
                intent.putExtra("title",list_show.get(i).getGoods_name());
                intent.putExtra("money",list_show.get(i).getGoods_marketprice());
                intent.putExtra("count",list_show.get(i).getEvaluation_count());
                startActivity(intent);
            }
        });
        ed_texts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowActivity.this, SouActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        mainPresenter =  new MainPresenter();
        mainPresenter.attachView(this);
        list_show.clear();
        mainPresenter.loadDataFromGetServer(Api.LINK_MOBILE_GOODS_SEARCH,ShouBean.class,LIST1);
    }

    @Override
    public void success(ShouBean shouBean, int code) {
        if (shouBean!=null){
            switch (code){
                case LIST1:
                    list_show.addAll(shouBean.getDatas().getGoods_list());
                    shouAdapter.notifyDataSetChanged();
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
}
