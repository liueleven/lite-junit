package cn.liueleven.junit.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 上下文
 * @date: 2020-06-07 12:46
 * @author: 十一
 */
public class TestCaseContext {

    private static ThreadLocal<Map<String,Object>> threadLocal
            =  new ThreadLocal<Map<String, Object>>(){

        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(16);
        }
    };


    public static Object get(String key) {
        return threadLocal.get().get(key);
    }

    public static void add (String key,Object val) {
        Map<String, Object> stringObjectMap = threadLocal.get();

        stringObjectMap.put(key,val);
    }
}
