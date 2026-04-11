public interface IntUnaryFunction {
    int apply(int x);
}
public class TenX implements IntUnaryFunction {
    public int apply(int x) {
        return 10 * x;
    }
}

public class HofDemo {
    public static int do_twice(IntUnaryFunction f, int x)
}