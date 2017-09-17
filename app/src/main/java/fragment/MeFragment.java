package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.mynsg.R;

import activity.LoginActivity;

/**
 * 类的描述：
 * 时间：  2017/9/4.20:06
 * 姓名：chenlong
 */
public class MeFragment extends Fragment {

    private View view;
    /**
     * 点击登录
     */
    private TextView mUserHeadImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mefragment, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        mUserHeadImage = (TextView) view.findViewById(R.id.user_head_image);
        mUserHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
