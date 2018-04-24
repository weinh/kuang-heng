package jvm.memory;

import jvm.LoaderClass;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * 类 名 称：JDK8OOM.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月24日
 */
public class JDK8OOM {

    static String base = "string";

    public static void main(String[] args) {
//        stringConstantInHeap();
        metaSpaceOOM();
    }

    /**
     * 字符串常量分配在堆上，设置堆大小为10M：-Xmx10m
     */
    private static void stringConstantInHeap() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

    /**
     * 类加载元数据信息保存到元空间（本地内存），设置元空间大小8M：-XX:MaxMetaspaceSize=8m
     */
    private static void metaSpaceOOM() {
        try {
            //获取有关类型加载的JMX接口
            ClassLoadingMXBean loadingBean = ManagementFactory.getClassLoadingMXBean();
            //用于缓存类加载器
            List<ClassLoader> classLoaders = new ArrayList<ClassLoader>();
            while (true) {
                //加载类型并缓存类加载器实例
                ClassLoader classLoader = new LoaderClass.MyClassLoader();
                classLoaders.add(classLoader);
                classLoader.loadClass("jvm.LoaderClass");
                //显示数量信息（共加载过的类型数目，当前还有效的类型数目，已经被卸载的类型数目）
                System.out.println("total: " + loadingBean.getTotalLoadedClassCount());
                System.out.println("active: " + loadingBean.getLoadedClassCount());
                System.out.println("unloaded: " + loadingBean.getUnloadedClassCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}