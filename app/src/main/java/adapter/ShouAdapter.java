package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.mynsg.R;
import com.bumptech.glide.Glide;

import java.util.List;

import Bean.ShouBean;

/**
 * 类的描述：
 * 时间：  2017/9/5.14:43
 * 姓名：chenlong
 */
public class ShouAdapter extends BaseAdapter{
    Context context;
    List<ShouBean.DatasBean.GoodsListBean> list;

    public ShouAdapter(Context context, List<ShouBean.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
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
             ViewHolder holder=null;
             if(convertView==null){
                 convertView=View.inflate(context, R.layout.item_class_show,null);
                 holder=new ViewHolder();
                 holder.imaeds = convertView.findViewById(R.id.imaed);
                 holder.text_titles = convertView.findViewById(R.id.biao_title);
                 holder.text_pay = convertView.findViewById(R.id.biao_money);
                 holder.text_count = convertView.findViewById(R.id.biao_count);

                 convertView.setTag(holder);
             }else{
                 holder=(ViewHolder)convertView.getTag();
             }

             Glide.with(context).load(list.get(position).getGoods_image_url()).into(holder.imaeds);
             holder.text_titles.setText(list.get(position).getGoods_name());
             holder.text_pay.setText("¥"+list.get(position).getGoods_marketprice());
             holder.text_count.setText("已售："+list.get(position).getEvaluation_count()+"件");

             return convertView;
         }

         class ViewHolder{
             ImageView imaeds;
             TextView text_titles;
             TextView text_pay;
             TextView text_count;

         }
}
