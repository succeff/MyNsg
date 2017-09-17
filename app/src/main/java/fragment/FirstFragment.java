package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.mynsg.MainActivity;
import com.baway.mynsg.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import activity.SouActivity;


/**
 * 类的描述：
 * 时间：  2017/8/31.14:59
 * 姓名：chenlong
 */

public class FirstFragment extends Fragment {
    TextView ed;
    ImageView ima;
    private int REQUEST_CODE=111;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fristfragment, container, false);
        edVeiw(view);
        showErrorInfo(getContext());


        return view;
    }


    private void showErrorInfo(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("请求数据有误，请稍后再试！");
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", null);
        builder.show();
    }


    private void edVeiw(View view) {
        ed = view.findViewById(R.id.ed_text);
        ima = view.findViewById(R.id.zxing_img);
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SouActivity.class);
                startActivity(intent);
            }
        });
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}
