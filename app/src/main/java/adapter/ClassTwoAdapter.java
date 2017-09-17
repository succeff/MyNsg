package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.mynsg.R;

import java.util.List;

import Bean.GoodsClassBean;
import presenter.MyGridView;

/**
 * 类的描述：
 * 时间：  2017/9/5.10:51
 * 姓名：chenlong
 */
public class ClassTwoAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsClassBean> list;
    private List<GoodsClassBean> gridlist;
    private int positions;

    public ClassTwoAdapter(Context context, List<GoodsClassBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setData(List<GoodsClassBean> list, int position) {
        gridlist = list;
        positions = position;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_class_two, null);
            holder = new ViewHolder();
            holder.tits = convertView.findViewById(R.id.class2_item_text);
            holder.gridView = convertView.findViewById(R.id.class2_item_gridview);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tits.setText(list.get(position).getGc_name());
        if (gridlist != null && position == positions) {
            holder.gridView.setVisibility(View.VISIBLE);
            holder.gridView.setAdapter(new ClassThreeAdapter(context,gridlist));

        }else {
            holder.gridView.setVisibility(View.GONE);
        }


        return convertView;
    }

    class ViewHolder {
        TextView tits;
        MyGridView gridView;
    }
}
