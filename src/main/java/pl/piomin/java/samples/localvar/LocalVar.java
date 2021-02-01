package pl.piomin.java.samples.localvar;

import java.util.function.BiFunction;

public class LocalVar {

    public String sumOfString() {
        BiFunction<String, String, String> func = (var x, var y) -> x + y;
        return func.apply("abc", "efg");
    }
}
