package sqliter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类的描述：
 * 时间：  2017/9/14.14:59
 * 姓名：chenlong
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * 必须要写的构造方法
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        // 四个参数分别表示
        // context：上下文
        // student.db：database的名称
        // null：是一个CursorFactory，cursor表示游标，用来移动查询结果集
        // 1：数据库版本
        super(context, "shopCat.db", null, 1);
    }

    /**
     * 在数据库第一次被创建时调用
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 数据库创建student表语句
        db.execSQL("create table shopCat (id integer primary key autoincrement, image varchar(20), title varchar(20),money varchar(20))");

    }

    /**
     * 当数据库版本有改变时才会调用该方法
     *
     * @param oldVersion
     *            旧版本
     *
     * @param newVersion
     *            新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("版本改为：" + newVersion);
        // 当数据库版本发生变化是时里面的代码才会执行
    }


}
