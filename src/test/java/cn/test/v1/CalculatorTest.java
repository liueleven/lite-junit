package cn.test.v1;

import cn.liueleven.junit.Assert;
import cn.liueleven.junit.core.TestCaseStater;
import cn.liueleven.junit.model.TestCase;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 21:18
 * @author: 十一
 */
public class CalculatorTest extends TestCase {

    public static void main(String[] args) {
        new TestCaseStater().start(CalculatorTest.class);
    }

    Calculator calculator = null;

    @Override
    public void setUp() {
        calculator = new Calculator();
    }

    @Override
    public void tearDown() {
    }

    public void testAdd() {
        calculator.add(5);
        Assert.assertEquals(5, calculator.getResult());
    }

    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(5);
        Assert.assertEquals(5, calculator.getResult());
    }
}
