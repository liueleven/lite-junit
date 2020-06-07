package cn.liueleven.junit.model;

import java.util.Map;

/**
 * @description: 类元数据信息
 * @date: 2020-06-07 12:07
 * @author: 十一
 */
public class ClassMetaData {

    private String className;

    private Map<String,Class> methodMap;
    /**
     * json 格式，用于扩展预留
     */
    private String extInfo;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, Class> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Class> methodMap) {
        this.methodMap = methodMap;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}
