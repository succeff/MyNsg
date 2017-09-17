package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.baway.mynsg.R;

/**
 * 类的描述：
 * 时间：  2017/8/31.17:29
 * 姓名：chenlong
 */

public class SouActivity extends AppCompatActivity {
    ImageView ima_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sou_activity);
        imaView();
    }

    private void imaView() {
        ima_back = (ImageView) findViewById(R.id.back);
        ima_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
