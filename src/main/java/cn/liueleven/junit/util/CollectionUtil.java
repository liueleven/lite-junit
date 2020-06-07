package cn.liueleven.junit.util;

import java.util.Collection;
import java.util.Map;

/**
 * @description: 集合工具类
 * @date: 2020-06-07 12:23
 * @author: 十一
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        if(collection == null) {
            return true;
        }
        if (collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Map map) {
        if(map == null) {
            return true;
        }
        if (map.size() == 0) {
            return true;
        }
        return false;
    }

}
