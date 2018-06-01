package java_knowledge.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>类 名 称：Genericity.java
 * <p>功能说明：
 * <p>开发时间：2018年06月01日
 *
 * @author ：weinh
 */
public class Genericity {

    class Container<K, V> {
        private K key;
        private V value;

        public Container(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void testExtends() {
        List<? extends Fruit> list = null;
        list = new ArrayList<Apple>();
        list = new ArrayList<Banana>();
        list = initBanana();
        // 正确
        Fruit fruit = list.get(0);
        // 错误
        // Banana banana = list.get(0);
        // 错误
        // list.add(new Banana());
    }

    public void testSuper() {
        List<? super Apple> list = null;
        list = new ArrayList<Fruit>();
        // 正确
        list.add(new Apple());
        // 正确
        list.add(new RedApple());
        // 错误
        // Fruit fruit = list.get(0);
        // 错误
        // Apple apple = list.get(0);
    }

    private List<Banana> initBanana() {
        List<Banana> list = new ArrayList<Banana>();
        list.add(new Banana());
        return list;
    }

    class Fruit {

    }

    class Apple extends Fruit {

    }


    class RedApple extends Apple {

    }

    class Banana extends Fruit {

    }
}
