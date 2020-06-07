package cn.liueleven.junit.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 13:38
 * @author: 十一
 */
public class ClassUtilTest {

    public static void main(String[] args) {
        Set set = new HashSet<Class>();

        set.add(Boo.class);
        List<Class> list = ClassUtil.getAllSubClass(set, Foo.class);
        Assert.assertTrue(list.size() == 1L);
    }


    // mock
    static class Foo implements Too{

    }

    static class Boo extends Foo {

    }

    static interface Too {

    }


}
