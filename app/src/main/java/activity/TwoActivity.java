package activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.baway.mynsg.R;

import java.util.ArrayList;
import java.util.List;

import adapter.FragAdapter;
import fragment.CatFragment;
import fragment.FenFragment;
import fragment.FirstFragment;
import fragment.MeFragment;

/**
 * 类的描述：
 * 时间：  2017/8/31.14:40
 * 姓名：chenlong
 */

public class TwoActivity extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager vp;
    String[] name = {"首页","分类","购物车","个人"};
    List<String> list_tab = new ArrayList<>();
    List<Fragment> list_frag = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_activity);
        tabVeiw();
    }

    private void tabVeiw() {
         tabLayout = (TabLayout) findViewById(R.id.tab_layout);
         vp = (ViewPager) findViewById(R.id.view_pager);
        for (String bean:name) {
            list_tab.add(bean);
            tabLayout.addTab(tabLayout.newTab().setText(bean));
        }


        FirstFragment firstFragment = new FirstFragment();
        FenFragment fenFragment = new FenFragment();
        CatFragment catFragment = new CatFragment();
        MeFragment meFragment = new MeFragment();

        list_frag.add(firstFragment);
        list_frag.add(fenFragment);
        list_frag.add(catFragment);
        list_frag.add(meFragment);

        FragAdapter adapter=new FragAdapter(getSupportFragmentManager(), list_frag,list_tab);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);

        tabLayout.getTabAt(0).setText(list_tab.get(0)).setIcon(R.drawable.first);
        tabLayout.getTabAt(1).setText(list_tab.get(1)).setIcon(R.drawable.fenlei);
        tabLayout.getTabAt(2).setText(list_tab.get(2)).setIcon(R.drawable.gouwc);
        tabLayout.getTabAt(3).setText(list_tab.get(3)).setIcon(R.drawable.mex);


    }
}
