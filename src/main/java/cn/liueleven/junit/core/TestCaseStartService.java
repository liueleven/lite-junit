package cn.liueleven.junit.core;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 13:13
 * @author: 十一
 */
public abstract class TestCaseStartService {

    protected void run() {
        init();

        statisticTestCase();
        execute();
        printResult();

        destroy();
    }

    /**
     * 初始化
     */
    protected abstract  void init();

    /**
     * 资源释放关闭
     */
    protected abstract  void destroy();

    /**
     * 打印结果
     */
    protected abstract void printResult();

    /**
     * 执行测试
     */
    protected abstract void execute();

    /**
     * 扫描统计要执行的测试用例
     */
    protected abstract void statisticTestCase();
}
