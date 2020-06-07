package cn.test.v1;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 21:17
 * @author: 十一
 */
public class Calculator {

    private int result = 0;
    public void add(int value){
        result += value;
    }
    public void subtract(int value){
        result -= value;
    }
    public int getResult(){
        return result;
    }
}
