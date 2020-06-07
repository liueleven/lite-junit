package cn.liueleven.junit.util;

import cn.liueleven.junit.core.exception.TestCaseException;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static cn.liueleven.junit.constant.TestCaseConstants.PARAM_ERROR;

/**
 * @description: 一定要写注释啊
 * @date: 2020-06-07 13:32
 * @author: 十一
 */
public class ClassUtil {

    /**
     * 从类list中找到哪些类是子类
     *
     * @param classes 子类list
     * @param target  父类
     * @return
     */
    public static List<Class> getAllSubClass(Set<Class<?>> classes, Class target) {
        if (classes == null) {
            throw new TestCaseException(PARAM_ERROR);
        }
        List<Class> subList = new ArrayList<Class>(classes.size());
        Iterator iterator = classes.iterator();
        while (iterator.hasNext()) {
            Class cls = ((Class) iterator.next());
            Class<?> superclass = cls.getSuperclass();
            if (superclass == null)  {
                continue;
            }
            if (superclass.getName().equals(target.getName())) {
                subList.add(cls);
            }
        }
        return subList;
    }

    private ClassUtil() {
    }


    /**
     * 获取某个包下的所有类
     */
    public static Set<Class<?>> getClasses(String packageName) throws IOException {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (url != null) {
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classSet, packagePath, packageName);
                } else if (protocol.equals("jar")) {
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    if (jarURLConnection != null) {
                        JarFile jarFile = jarURLConnection.getJarFile();
                        if (jarFile != null) {
                            Enumeration<JarEntry> jarEntries = jarFile.entries();
                            while (jarEntries.hasMoreElements()) {
                                JarEntry jarEntry = jarEntries.nextElement();
                                String jarEntryName = jarEntry.getName();
                                if (jarEntryName.endsWith(".class")) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                    doAddClass(classSet, className);
                                }
                            }
                        }
                    }
                }
            }
        }

        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (StringUtil.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtil.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static ClassLoader getClassLoader() {
        // 只需要获取当前线程中的ClassLoader即可
        return Thread.currentThread().getContextClassLoader();
    }
    
    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }


}
