package cn.liueleven.junit.core;

import cn.liueleven.junit.collect.CollectInfo;
import cn.liueleven.junit.constant.TestCaseConstants;
import cn.liueleven.junit.constant.TestCaseContextConstants;
import cn.liueleven.junit.core.context.TestCaseContext;
import cn.liueleven.junit.core.exception.TestCaseException;
import cn.liueleven.junit.model.TestCase;
import cn.liueleven.junit.core.rule.ClassRule;
import cn.liueleven.junit.core.rule.MethodRule;
import cn.liueleven.junit.core.rule.RulePipeline;
import cn.liueleven.junit.util.ClassUtil;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cn.liueleven.junit.constant.TestCaseContextConstants.TEST_CASE_COLLECT;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 11:59
 * @author: 十一
 */
public class TestCaseStater extends TestCaseStartService{

    private List<Class> executeClassList;

    private Class cls;

    public void start(Class cls) {
        this.cls = cls;
        super.run();
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        // 测试数据初始化
        CollectInfo collectInfo = new CollectInfo();
        TestCaseContext.add(TEST_CASE_COLLECT,collectInfo);
        // 规则加载
        RulePipeline rulePipeline = new RulePipeline();
        ClassRule classRule = new ClassRule();
        rulePipeline.add(classRule.getRuleName(),classRule);
        MethodRule methodRule = new MethodRule();
        rulePipeline.add(methodRule.getRuleName(),methodRule);
        TestCaseContext.add(TestCaseContextConstants.TET_RULE,rulePipeline);
    }

    /**
     * 资源释放关闭
     */
    @Override
    protected void destroy() {

    }

    @Override
    protected void printResult() {
        CollectInfo collectInfo = (CollectInfo) TestCaseContext.get(TEST_CASE_COLLECT);
        collectInfo.print();
    }

    @Override
    protected void execute() {
        for (Class clazz : executeClassList) {
            Method[] methods = clazz.getMethods();
            Object newInstance = null;
            try {
                newInstance = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new TestCaseException(clazz.getName() + " 反射创建类实例失败");

            }
            invokeMethodByName(clazz,newInstance, TestCaseConstants.METHOD_SET_UP);
            for (Method method : methods) {
                MethodInvoke methodInvoke = new MethodInvoke();
                methodInvoke.invoke(method,newInstance);
            }
            invokeMethodByName(clazz,newInstance, TestCaseConstants.METHOD_TEAR_DOWN);
        }
    }


    private String getPackageRoot() {
        String name = cls.getPackage().getName();
        int i = name.indexOf(".");
        return name.substring(0,i);
    }

    @Override
    protected void statisticTestCase() {

        Set<Class<?>> classes = null;
        try {
            classes = ClassUtil.getClasses(getPackageRoot());
        } catch (IOException e) {
            e.printStackTrace();
            throw new TestCaseException("获取所有类失败");

        }
        List<Class> subClsList = ClassUtil.getAllSubClass(classes, TestCase.class);
        executeClassList = new ArrayList<Class>(subClsList.size());
        CollectInfo collectInfo = (CollectInfo) TestCaseContext.get(TEST_CASE_COLLECT);
        for (Class clazz : subClsList) {
            RulePipeline rulePipeline
                    = (RulePipeline) TestCaseContext.get(TestCaseContextConstants.TET_RULE);

            if (!rulePipeline.checkRule(clazz)) {
               continue;
            }
            executeClassList.add(clazz);
            collectInfo.addClass(clazz.getName(),clazz);
        }
    }

    private void invokeMethodByName(Class clazz,Object newInstance,String name) {
        Method setUpMethod = null;
        try {
            setUpMethod = clazz.getMethod(name);
            setUpMethod.invoke(newInstance);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(setUpMethod.getName() + " " + name + " 执行失败");
        }
    }
}
