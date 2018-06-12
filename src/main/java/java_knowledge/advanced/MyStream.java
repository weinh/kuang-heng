package java_knowledge.advanced;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>类 名 称：MyStream.java
 * <p>功能说明：
 * <p>开发时间：2018年06月08日
 *
 * @author ：weinh
 */
public class MyStream {
    public static void main(String[] args) {
        String[] strings = new String[]{"abc", "def", "abcd", "defg", "1", "2"};
        Optional<Integer> a = Stream.of(strings).filter(s -> s.startsWith("a")).map(String::length).sorted().max(Integer::compareTo);

        int maxLength = 0;
        for (String string : strings) {
            if (string.startsWith("a")) {
                int length = string.length();
                maxLength = Math.max(length, maxLength);
            }
        }
    }
}
