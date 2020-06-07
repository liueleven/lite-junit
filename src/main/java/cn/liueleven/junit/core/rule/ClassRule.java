package cn.liueleven.junit.core.rule;

import cn.liueleven.junit.constant.TestCaseConstants;

/**
 * @description: 类规则
 * @date: 2020-06-07 13:21
 * @author: 十一
 */
public class ClassRule implements Rule {

    private final static  String KEY = "classRule";


    public String getRuleName() {
        return KEY;
    }



    public boolean execute(Object info) {
        Class clazz = (Class) info;
        // 获取类的全名，例如：cn.liueleven.junit.util.core.TestCaseStaterTest$FooTest
        String name = clazz.getName();
        if (name.startsWith(TestCaseConstants.PACKAGE)) {
            return true;
        }
        return false;
    }
}
