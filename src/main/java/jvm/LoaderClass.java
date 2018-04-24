package jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类 名 称：LoaderClass.java
 * 功能说明：
 * 开发人员：weinh
 * 开发时间：2018年04月11日
 */
public class LoaderClass {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        showLoaderClass();
        loadClass();
    }

    private static void showLoaderClass() {
        ClassLoader a = LoaderClass.class.getClassLoader();
        do {
            System.out.println(a);
        } while ((a = a.getParent()) != null);
    }

    private static void loadClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object object = new MyClassLoader().loadClass("jvm.LoaderClass").newInstance();
        System.out.println(object.getClass());
        System.out.println(object.getClass().getClassLoader());
        System.out.println(object instanceof LoaderClass);
    }

    public static class MyClassLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException(name);
            }
        }
    }
}
