package cn.liueleven.junit.util.core;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 15:28
 * @author: 十一
 */
public class MethodInvokeTest {

    public static void main(String[] args) {



    }
    public static class Foo {
        private String name;

        public String getName() {
            System.out.println("invoke getName");
            return name;
        }

    }
}
