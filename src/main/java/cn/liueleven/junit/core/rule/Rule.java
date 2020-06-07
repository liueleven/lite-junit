package cn.liueleven.junit.core.rule;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 12:00
 * @author: 十一
 */
public interface Rule {

    String getRuleName();

    boolean execute(Object info);
}
