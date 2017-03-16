package Learning.JavaEight;

import static java.util.Arrays.asList;

/**
 * Created by Денис on 14.03.2017.
 */
public class JavaEightOne {
    public static void main(String[] args) {
        asList("A", "BB", "CCC").stream()
                .filter(s -> s.length() > 1)
                .map(String::length)
                .forEach(System.out::println);
    }
}
