package cn.liueleven.junit.core.rule;

import cn.liueleven.junit.constant.TestCaseConstants;
import cn.liueleven.junit.core.exception.TestCaseException;

import java.lang.reflect.Method;

/**
 * @description: 方法规则
 * @date: 2020-06-07 13:21
 * @author: 十一
 */
public class MethodRule implements Rule {

    private final static  String KEY = "methodRule";


    public String getRuleName() {
        return KEY;
    }


    /**
     * 1. 非 test 开头的方法不执行
     * 2. Object 中的方法不执行
     * 3. setUp 和 tearDown 不执行
     * 4. 有传参的不执行
     * @param info
     * @return
     */
    public boolean execute(Object info) {
        Method method = (Method) info;
        String name = method.getName();
        // 1.
        if (!name.startsWith(TestCaseConstants.METHOD_START)) {
            return false;
        }

        // 2.
        if (method.getName().contains(TestCaseConstants.METHOD_JAVA_LAGN)) {
            return false;
        }
        // 3.
        if (method.getName().contains(TestCaseConstants.METHOD_SET_UP)
                || method.getName().contains(TestCaseConstants.METHOD_TEAR_DOWN)) {
            return false;
        }
        // 4. 不允许测试用例传参
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 0) {
            throw new TestCaseException(name + ", 待测试方法不允许传参！参见《阿里巴巴Java手册》");
        }
        return true;
    }
}
