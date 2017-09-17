package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.mynsg.R;

import java.util.ArrayList;
import java.util.List;

import Bean.DataBean;
import Bean.DatasBean;
import Bean.GoodsClassBean;
import activity.SouActivity;
import adapter.ClassOneAdapter;
import adapter.ClassThreeAdapter;
import adapter.ClassTwoAdapter;
import presenter.IMainView;
import presenter.MainPresenter;
import util.Api;

import static android.media.CamcorderProfile.get;

/**
 * 类的描述：
 * 时间：  2017/8/31.15:01
 * 姓名：chenlong
 */

public class FenFragment extends Fragment implements IMainView<DataBean>{
    TextView ed;
    private ListView listviewOne;
    private ListView listviewTwo;
    private ClassOneAdapter adapter1;
    private MainPresenter mMainPresenter;
    private List<GoodsClassBean> listleft = new ArrayList<>();
    private List<GoodsClassBean> listright = new ArrayList<>();
    private List<GoodsClassBean> mGoodsClassBeanList3 = new ArrayList<>();
    private final int LIST1 = 1;
    private final int LIST2 = 2;
    private final int LIST3 = 3;
    private ClassTwoAdapter adapter2;
    private ClassThreeAdapter adapter3;
    private int positions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fenfragment, container, false);
        edVeiw(view);
        initView(view);
        initData();
        initDataFromServer();

        return view;
    }

    private void initData() {
        adapter1 = new ClassOneAdapter(getContext(),listleft);
        listviewOne.setAdapter(adapter1);
        adapter2 = new ClassTwoAdapter(getContext(),listright);
        listviewTwo.setAdapter(adapter2);


    }

    private void initView(View view) {
        listviewOne = view.findViewById(R.id.listview_one);
        listviewTwo = view.findViewById(R.id.listview_two);
        //一级列表点击item事件
        listviewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapter2.setData(mGoodsClassBeanList3, -1);
                initDataFromServerClass2(position);
                positions = position;
            }
        });
        //二级列表点击item事件
        listviewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapter3 = new ClassThreeAdapter(getContext(),mGoodsClassBeanList3);
                initDataFromServerClass3(position,positions);
                adapter2.setData(mGoodsClassBeanList3,position);

            }
        });
    }

    private void edVeiw(View view) {
        ed = view.findViewById(R.id.ed_text);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SouActivity.class);
                startActivity(intent);
            }
        });
    }
    //一级列表获取数据
    private void initDataFromServer() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        listright.clear();
        mMainPresenter.loadDataFromGetServer(Api.LINK_MOBILE_CLASS, DataBean.class, LIST1);
    }
    //二级列表获取数据
    public void initDataFromServerClass2(int position){
        GoodsClassBean goodsClassBean = listleft.get(position);
        String gc_id = goodsClassBean.getGc_id();
        listright.clear();
        mMainPresenter.loadDataFromGetServer(Api.LINK_MOBILE_CLASS+"&gc_id="+gc_id,DataBean.class,LIST2);
    }
    //三级列表获取数据
    private void initDataFromServerClass3(int position, int positions) {
        GoodsClassBean goodsClassBean = listleft.get(positions);
        GoodsClassBean goodsClassBean1 = listright.get(position);
        String gc_id = goodsClassBean.getGc_id();
        String gc_id1 = goodsClassBean1.getGc_id();
        mGoodsClassBeanList3.clear();
        mMainPresenter.loadDataFromGetServer(Api.LINK_MOBILE_CLASS + "&gc_id=" + gc_id + "&gc_id=" + gc_id1, DataBean.class, LIST3);
    }




    @Override
    public void success(DataBean dataBean, int code) {
        if (dataBean != null){
            DatasBean datas = dataBean.getDatas();
            if (datas!=null){
              switch (code){
                  case LIST1:
                      listleft.addAll(datas.getClass_list());
                      adapter1.notifyDataSetChanged();
                      getActivity().runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                             mMainPresenter.loadDataFromGetServer(Api.LINK_MOBILE_CLASS+ "&gc_id=" + 1, DataBean.class, LIST2);
                          }
                      });
                      break;
                  case LIST2:
                      listright.addAll(datas.getClass_list());
                      adapter2.notifyDataSetChanged();
                      break;
                  case  LIST3:
                      mGoodsClassBeanList3.addAll(datas.getClass_list());
                      adapter2.notifyDataSetChanged();
                      break;

              }
            }

        }

    }

    @Override
    public void error(String message) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.dettachView();
    }
}
