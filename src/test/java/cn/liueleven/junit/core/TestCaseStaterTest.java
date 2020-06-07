package cn.liueleven.junit.core;

import cn.liueleven.junit.Assert;
import cn.liueleven.junit.model.TestCase;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 18:21
 * @author: 十一
 */
public class TestCaseStaterTest {

    public static void main(String[] args) {
        new TestCaseStater().start(TestCaseStaterTest.class);
    }


    // mock  -- 2
    public static class Foo2Test extends TestCase {
        private String msg = " Foo2Test.default";

        public void testHello1() {
            Foo foo = new Foo();
            foo.hello2(msg);
        }

        public void testHello2() {
            Foo foo = new Foo();
            foo.hello2(msg);
            throw new RuntimeException("occur exception");
        }
    }

    // mock  -- 1

    public static class FooTest extends TestCase {

        private String msg = "default";

        @Override
        public void setUp() {
            msg = "my junit";
        }

        @Override
        public void tearDown() {
            msg = null;
        }

        public void testHello() {
            Foo foo = new Foo();
            Assert.assertEquals(msg,foo.hello(msg));
            Assert.assertEquals("hello"+msg,foo.hello2(msg));
        }

        public void testHello2() {
            Foo foo = new Foo();
            foo.hello(msg);
        }
    }


    public static class Foo extends Bar {

        public String hello(String msg) {
            return msg;
        }

        public String hello2(String msg) {
            return "hello"+msg;
        }
    }

    public static class Bar {
        public void setUp() {
//            System.out.println("Bar.setUp");
        }

        public void tearDown() {
//            System.out.println("Bar.tearDown");
        }
    }
}
