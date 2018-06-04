package java_knowledge.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

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

    public void checkCompile() {
        List fruits0 = new ArrayList();
        List<Fruit> fruits1 = new ArrayList<>();
        List<Fruit> fruits3 = new ArrayList();
        List fruits2 = new ArrayList<>();

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple());
        apples.add(new RedApple());
        // 错误，保留上界，检查不通过
        // apples.add(new Banana());
        // 错误
        // 从下面的例子看，很明显，objects1可以是任意类型，做了赋值引用后，strings1必定会出现类型转换错误，所以避免了这种情况
        // ArrayList<String> list1 = new ArrayList<Object>();
        // ArrayList<Object> objects1 = new ArrayList<>();
        // objects1.add("0");
        // objects1.add(0);
        // ArrayList<String> strings1 = objects1;
        //
        // 从下面的例子看，有两个问题1、本身泛型就算为了方便使用，而不需要强制转换2、如果objects1操作add，到时候取出来的是String还是Object呢
        // ArrayList<Object> list2 = new ArrayList<String>();
        // ArrayList<String> strings1 = new ArrayList<>();
        // strings1.add("1");
        // strings1.add("2");
        // ArrayList<Object> objects1 = strings1;
    }

    class Plate<T> {
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    class FruitPlate extends Plate<Fruit> {
        @Override
        public Fruit getValue() {
            return super.getValue();
        }

        @Override
        public void setValue(Fruit value) {
            super.setValue(value);
        }
    }

    public void polymorphism() {
        FruitPlate fruitPlate = new FruitPlate();
        fruitPlate.setValue(new Fruit());
        // 错误，证明这个不是重载，是重写
        // fruitPlate.setValue(new Object());
    }
}
