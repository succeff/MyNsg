package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.mynsg.R;

import java.util.List;

import Bean.GoodsClassBean;
import activity.ShowActivity;

/**
 * 类的描述：
 * 时间：  2017/9/5.13:28
 * 姓名：chenlong
 */
public class ClassThreeAdapter extends BaseAdapter{
    private Context context;
    private List<GoodsClassBean> mList;

    public ClassThreeAdapter(Context context, List<GoodsClassBean> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.item_class_three,null);
            holder = new viewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.class3_item_text);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mList.get(position).getGc_name());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class viewHolder{
        TextView mTextView;
    }
}
