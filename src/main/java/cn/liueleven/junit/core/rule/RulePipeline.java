package cn.liueleven.junit.core.rule;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 规则校验，校验是否具备可测试的条件
 * @date: 2020-06-07 12:00
 * @author: 十一
 */
public class RulePipeline {

    private Map<String,Rule> ruleMap;

    public RulePipeline() {
        ruleMap = new HashMap<String,Rule>(16);
    }

    public boolean checkRule(Object info) {
        if (info instanceof Class) {
            Rule rule = ruleMap.get("classRule");
            boolean result = rule.execute((Class) info);
            if (result) {
                return true;
            }
        }
        if (info instanceof Method) {
            Rule rule = ruleMap.get("methodRule");
            boolean result = rule.execute((Method)info);
            if (result) {
                return true;
            }

        }
        return false;
    }


    public void add(String ruleKey,Rule rule) {
        ruleMap.put(ruleKey,rule);
    }


}
