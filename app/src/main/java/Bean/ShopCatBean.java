package Bean;

import static android.R.attr.id;

/**
 * 类的描述：
 * 时间：  2017/9/14.15:47
 * 姓名：chenlong
 */

public class ShopCatBean {
    public  int  id;
    private String image;
    private String title;
    private String money;



    public ShopCatBean(String image, String title, String money) {
        this.image = image;
        this.title = title;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
