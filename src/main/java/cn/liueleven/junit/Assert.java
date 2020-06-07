package cn.liueleven.junit;

import cn.liueleven.junit.core.exception.TestCaseAssertException;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 12:12
 * @author: 十一
 */
public class Assert {

    public static void assertTrue(boolean expect) {
        if (expect) {
            return;
        }
        exception(msgBuilder("true","false"));
    }


    public static void assertEquals(String expect,String result) {
        if (expect == null && result == null) {
            return;
        }
        if (expect == null || result == null) {
            exception(msgBuilder(expect,result));
        }
        if (expect.equals(result)) {
            return;
        }
        exception(msgBuilder(expect,result));

    }

    public static void assertEquals(long expect,long result) {
        if (expect == 0 && result == 0) {
            return;
        }
        if (expect == 0 || result == 0) {
            exception(msgBuilder(String.valueOf(expect),String.valueOf(result)));
        }
        if (expect == result) {
            return;
        }
        exception(msgBuilder(String.valueOf(expect),String.valueOf(result)));

    }


    private static String msgBuilder(String expect,String result) {
       return String.format("expect is %s, result is %s",expect,result);
    }

    private static void exception(String msg) {
        throw new TestCaseAssertException("TestCase Exception: " + msg);
    }
}
