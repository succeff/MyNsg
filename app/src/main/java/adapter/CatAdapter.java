package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.mynsg.R;
import com.bumptech.glide.Glide;

import java.util.List;

import Bean.ShopCatBean;
import Bean.ShouBean;
import activity.TwoActivity;
import fragment.CatFragment;
import sqliter.ShopCat;

/**
 * 类的描述：
 * 时间：  2017/9/8.9:44
 * 姓名：chenlong
 */
public class CatAdapter extends BaseAdapter{
    private Context context;
    private List<ShopCatBean> list;
    private TextView mCartCount;
    private int count;
    private int count1;
    private float c1;
    private float c;
    private ShopCatBean bean;
    public delete deletadata;

    public void setDeletadata(delete deletadata) {
        this.deletadata = deletadata;
    }

    public interface delete{
        public  void onclick(int position,View v);
    }


    public CatAdapter(Context context, List<ShopCatBean> list, TextView mCartCount) {
        this.context = context;
        this.list = list;
        this.mCartCount = mCartCount;
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
         public View getView(final int position, View convertView, ViewGroup parent) {
             ViewHolder holder=null;
             if(convertView==null){
                 convertView=View.inflate(context, R.layout.cat_list_activity,null);
                 holder=new ViewHolder();
                 holder.name = (TextView)convertView.findViewById(R.id.cart_item_name1);
                 holder.price = (TextView)convertView.findViewById(R.id.cart_item_price1);
                 holder.image = (ImageView)convertView.findViewById(R.id.cart_item_image1);
                 holder.shu = (TextView)convertView.findViewById(R.id.cart_item_shushu);
                 holder.jia = (Button)convertView.findViewById(R.id.cart_item_jiajia);
                 holder.jian = (Button)convertView.findViewById(R.id.cart_item_jianjian);
                 holder.shu = (TextView)convertView.findViewById(R.id.cart_item_shushu);
                 holder.num = (TextView)convertView.findViewById(R.id.cart_item_numnum);
                 holder.DGZJ = (TextView)convertView.findViewById(R.id.cart_item_zongji1);
                 holder.delet = convertView.findViewById(R.id.cart_item_delete1);

                 holder.delet.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         deletadata.onclick(position,view);
                     }
                 });
                 convertView.setTag(holder);
             }else{
                 holder=(ViewHolder)convertView.getTag();
             }

             Glide.with(context).load(list.get(position).getImage()).into(holder.image);
             holder.name.setText(list.get(position).getTitle());
             holder.DGZJ.setText("共计1件商品，共计"+list.get(position).getMoney()+"元");
             holder.price.setText(list.get(position).getMoney());
             final ViewHolder Holder = holder;
             final ViewHolder Holder1 = holder;
             final ViewHolder Holder2 = holder;
             final ViewHolder Holder3 = holder;
             holder.jia.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     //获得当前数量
                     count = Integer.parseInt(Holder3.shu.getText().toString());
                     //每点击一次数量加一次
                     count++;
                     Holder.shu.setText(""+ count);
                     Holder1.num.setText("X"+ count);
                     //计算当前数量商品的总价
                     float b=Float.parseFloat(list.get(position).getMoney());
                     c = b* count;
                     Holder2.DGZJ.setText("共计"+ count +"件商品，共计"+ c +"元");
                     mCartCount.setText("共计"+ count +"件商品，共计"+ c+"元");
                 }
             });
             holder.jian.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     count1 = Integer.parseInt(Holder3.shu.getText().toString());
                     if(count1 >0){
                         count1--;
                         Holder.shu.setText(""+ count1);
                         Holder1.num.setText("X"+ count1);
                         //计算当前数量商品的总价
                         float b1=Float.parseFloat(list.get(position).getMoney());
                         c1 = b1* count1;
                         Holder2.DGZJ.setText("共计"+ count1 +"件商品，共计"+ c1 +"元");
                         mCartCount.setText("共计"+ count1 +"件商品，共计"+ c1+"元");

                     }else {
                         Toast.makeText(context,"数量不能为零",Toast.LENGTH_SHORT).show();
                     }

                 }
             });
             holder.delet.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {


                 }
             });

             return convertView;
         }

         class ViewHolder{
             Button jia,jian;
             TextView name,price,shu,num,DGZJ,delet;
             ImageView image;
         }
    //删除
    public void btnDelete(){
        ShopCat shopCat = new ShopCat(context);
        boolean result = shopCat.delete(bean.getId());
        if(result){
            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }
}
