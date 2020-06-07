package cn.liueleven.junit.core;

import cn.liueleven.junit.collect.CollectInfo;
import cn.liueleven.junit.constant.TestCaseContextConstants;
import cn.liueleven.junit.core.context.TestCaseContext;
import cn.liueleven.junit.core.rule.RulePipeline;

import java.lang.reflect.Method;

/**
 * @description: 执行方法，只要这里执行才能统计到结果
 * @date: 2020-06-07 14:40
 * @author: 十一
 */
public class MethodInvoke {


    public void invoke(Method method, Object newInstance) {


        RulePipeline rulePipeline
                = (RulePipeline) TestCaseContext.get(TestCaseContextConstants.TET_RULE);

        if (!rulePipeline.checkRule(method)) {
            return;
        }


        CollectInfo collectInfo = (CollectInfo) TestCaseContext.get(TestCaseContextConstants.TEST_CASE_COLLECT);
        collectInfo.addMethod(method.getName(), method);
        try {
            method.invoke(newInstance);
            collectInfo.successCountIncrement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("方法调用异常：" + method.getDeclaringClass().getName());
            collectInfo.failCountIncrement();
        }


    }
}
