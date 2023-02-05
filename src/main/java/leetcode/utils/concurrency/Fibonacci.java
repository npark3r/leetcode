package leetcode.utils.concurrency;

import java.math.BigInteger;

public class Fibonacci {

    public static BigInteger fibonacci(int element) {
        int size = Math.max(element, 2);
        BigInteger[] calculated = new BigInteger[size];
        calculated[0] = BigInteger.valueOf(1);
        calculated[1] = BigInteger.valueOf(1);

        if (calculated[element - 1] != null) {
            return calculated[element - 1];
        }

        calculated[element - 1] = fibonacciWithMemo(element - 1, calculated).add(fibonacciWithMemo(element - 2, calculated));
        return calculated[element - 1];
    }

    public static BigInteger fibonacciWithMemo(int element, BigInteger[] calculated) {
        if (calculated[element - 1] != null) {
            return calculated[element - 1];
        }

        calculated[element - 1] = fibonacciWithMemo(element - 1, calculated).add(fibonacciWithMemo(element - 2, calculated));
        return calculated[element - 1];
    }

}
