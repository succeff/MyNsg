package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.mynsg.R;

import java.util.ArrayList;
import java.util.List;

import Bean.ShopCatBean;
import adapter.CatAdapter;
import sqliter.ShopCat;

import static android.R.attr.data;

/**
 * 类的描述：
 * 时间：  2017/8/31.15:03
 * 姓名：chenlong
 */

public class CatFragment extends Fragment{
    private View view;
    private ListView mCartListview;
    /**
     * 共0件商品，共计0元
     */
    private TextView mCartCount;
    /**
     * 去结算
     */
    private Button mCartJiesuan;
    private CatAdapter catAdapter;
    private List<ShopCatBean> list = new ArrayList<>();
    private ShopCat shopCat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.catfragment, container, false);
        initView(view);
        //loadView();
        btnFindOption(view);
        return view;

    }
    public void btnFindOption(View v){
        shopCat = new ShopCat(getContext());
        list = shopCat.findAll();
        catAdapter = new CatAdapter(getContext(),list,mCartCount);
        mCartListview.setAdapter(catAdapter);
        catAdapter.notifyDataSetChanged();



        mCartListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"点击",Toast.LENGTH_SHORT).show();
            }
        });




    }


    private void initView(View view) {
        mCartListview = (ListView) view.findViewById(R.id.cart_listview);
        mCartCount = (TextView) view.findViewById(R.id.cart_count);
        mCartJiesuan = (Button) view.findViewById(R.id.cart_jiesuan);
    }

    @Override
    public void onResume() {
        super.onResume();
        btnFindOption(view);
    }
}
