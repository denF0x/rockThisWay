package Learning.JavaEight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

/**
 * Created by Денис on 16.03.2017.
 */
public class JavaEightTVExample {
    public static void main(String[] args) {
        partition(new ArrayList<Integer>(asList(1, 2, 3, 4, 5, 6, 7, 8, 9))).stream()
                .flatMap(arg -> partition(arg). stream())
                .filter(lists -> eval(lists) == 100)
                .map(JavaEightTVExample::toStr)
                .forEach(System.out::println);
    }

    public static <T> List<List<List<T>>> partition(List<T> arg) {
        if (arg.size() == 1) {
            return new ArrayList<>(asList(asList(arg)));
        } else {
            T elem = arg.remove(0);
            List<List<List<T>>> last = partition(arg);

            List<List<List<T>>> result = new ArrayList<>();
            for (List<List<T>> variant : last) {
                List<List<T>> var0 = copy2(variant);
                List<List<T>> var1 = copy2(variant);
                var0.add(0, asList(elem));
                var1. get(0).add(0, elem);
                result.add(var0);
                result.add(var1);
            }
            return result;
        }
    }

    private static Integer eval(List<List<List<Integer>>> arg) {
        return asList(arg).stream()
                .map(part2 -> part2.stream()
                        .map(arg2 -> arg2.stream()
                                .map(arg1 -> arg1.stream().reduce(0, (x, y) -> 10 * x + y))
                                .reduce(1, (x, y) -> x *y))
                        .reduce(0, (x, y) -> x +y))
                .findFirst().get();
    }

    private static String toStr(List<List<List<Integer>>> arg) {
        return asList(arg).stream()
                .map(part2 -> part2.stream().map(arg2 -> arg2.stream()
                        .map(arg1 -> arg1.stream()
                                .map(x -> "" + x)
                                .collect(joining("")))
                        .collect(joining("*"))).collect(joining(" + ")))
                .findFirst().get();

    }
    private static <T> List<List<T>> copy2(List<List<T>> arg) {
        return arg.stream().map(ts -> new ArrayList<T>(ts)).collect(Collectors.toList());
    }

}
