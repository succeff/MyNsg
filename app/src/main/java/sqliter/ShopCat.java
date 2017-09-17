package sqliter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Bean.ShopCatBean;


/**
 * 类的描述：
 * 时间：  2017/9/14.15:17
 * 姓名：chenlong
 */

public class ShopCat {
    private SQLiteDatabase db;
    private List<ShopCatBean> list;
    public ShopCat(Context context){
        DatabaseHelper helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }
    //添加数据
    public boolean add(String image,String title,String money){
//参数1：表名
//参数2: 某些可有可无
//参数3：ContentValues 类型 类似map结合  key/value  key : 列  /   value ： 绑定的值
        ContentValues values = new ContentValues();
        values.put("image", image);
        values.put("title", title);
        values.put("money",money);
//
        long result = db.insert("shopCat", null, values);
        //result 不等于-1 代表插入数据库成功
        if(result != -1){
            return true;
        }else{
            return false;
        }
    }
    //查询数据
    public List<ShopCatBean> findAll(){
        Cursor cursor = db.query("shopCat", null, null, null, null, null, null);
       //StringBuffer sb = new StringBuffer();
        list = new ArrayList();
        while(cursor.moveToNext()){
            String iamge = cursor.getString(cursor.getColumnIndex("image"));
            String tit = cursor.getString(cursor.getColumnIndex("title"));
            String money = cursor.getString(cursor.getColumnIndex("money"));

           //创建对象
            ShopCatBean scb = new ShopCatBean(iamge,tit,money);
            scb.setImage(iamge);
            scb.setTitle(tit);
            scb.setMoney(money);
          //添加到集合
            list.add(scb);
        }
        return list;
    }
    //删除
    public boolean delete(int id){
        int result = db.delete("shopCat", "id = ?", new String[]{String.valueOf(id)});
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }



}
