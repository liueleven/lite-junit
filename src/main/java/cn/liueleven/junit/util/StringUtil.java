package cn.liueleven.junit.util;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 19:04
 * @author: 十一
 */
public class StringUtil {


    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.length() == 0;
    }


    public static boolean isNotEmpty(String str) {
       return !isEmpty(str);
    }
}
