package cn.liueleven.junit.collect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 18:08
 * @author: 十一
 */
public class CollectInfo {

    /**
     * 执行成功的数量
     */
    private int successCount = 0;

    /**
     * 执行失败的数量
     */
    private int failCount = 0;

    /**
     * 执行过的方法，排除setUp和tearDown
     */
    private Map<String, Method> methodMap = new HashMap<String, Method>(64);

    /**
     * 所有可以执行的 class
     */
    private Map<String, Class> clazzMap = new HashMap<String, Class>(64);

    /**
     * @return
     */
    public int successCountIncrement() {
        return successCount++;
    }

    public int failCountIncrement() {
        return failCount++;
    }

    public void addMethod(String key, Method method) {
        methodMap.put(key, method);
    }

    public void addClass(String key, Class clazz) {
        clazzMap.put(key, clazz);
    }

    /**
     * 收集信息打印
     */
    public void print() {

        int totalMethod = successCount + failCount;

        System.out.println("-------------------- TestCase result -------------------------");
        format("run test class total: ", methodMap.size());
        format("run test method total: ", totalMethod);
        format("run test success method total: ", successCount);
        format("run test fail method total: ", failCount);
        System.out.println("-------------------- TestCase result -------------------------");
    }

    private void format(String title, Object msg) {
        System.out.printf("%-60s", title);
        System.out.printf(msg + "\n");
    }


}
