package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

import activity.TwoActivity;

/**
 * 类的描述：
 * 时间：  2017/8/31.15:09
 * 姓名：chenlong
 */

public class FragAdapter extends FragmentPagerAdapter {
    List<Fragment>list;
    List<String>list_tab;

    public FragAdapter(FragmentManager fm, List<Fragment> list, List<String> list_tab) {
        super(fm);
        this.list = list;
        this.list_tab = list_tab;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return list_tab.get(position);
    }

}
