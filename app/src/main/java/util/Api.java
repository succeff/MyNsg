package util;


/**
 * 类的描述：
 * 时间：  2017/9/1.14:31
 * 姓名：chenlong
 */

public class Api {
    public static boolean isOnline = false;
    public static final String PRODUCT = "http://www.baidu.com";
    public static final String DEVELOP = "http://169.254.120.105/";
    public static final String HOST = isOnline ? PRODUCT : DEVELOP;


    public static final String LINK_MOBILE = DEVELOP + "mobile/index.php?act=";
    public static final String LINK_MOBILE_CLASS = LINK_MOBILE+"goods_class";
    public static final String  LINK_MOBILE_GOODS_SEARCH =DEVELOP +"mobile/index.php?act=goods&op=goods_list&page=100";
    public static final String LINK_MOBILE_GOODS_BODY =DEVELOP+"mobile/index.php?act=goods&op=goods_body&goods_id=100009";
    public static final String LINK_MOBILE_REG = DEVELOP +"mobile/index.php?act=login&op=register";
    public  static final String LINK_lOGIN = DEVELOP +"mobile/index.php?act=login";
}
