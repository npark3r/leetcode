package leetcode.utils;

import java.io.InvalidClassException;
import java.util.HashSet;
import java.util.Set;

public class IntegerUtils {

    private IntegerUtils() throws InvalidClassException {
        throw new InvalidClassException("Util class, may not create instance of.");
    }


    public static boolean isHappy(int n) {
        Set<Integer> seenCombination = new HashSet<>();
        int currentInt = n;
        while(currentInt != 1) {
            int sum = findSquareSumOfDigits(currentInt);
            if (seenCombination.contains(sum)) {
                return false;
            }
            seenCombination.add(sum);
            currentInt = sum;
        }
        return true;
    }

    static int findSquareSumOfDigits(int number) {
        if (number == 0) return 0;
        int firstDigit = number % 10;
        return (int) (Math.pow(firstDigit, 2) + findSquareSumOfDigits(number / 10));
    }
}

/*
    2^2 = 4
    4^2 = 16
    1^2 + 6^2 = 37
    3^2 + 7^2 = 58
    5^2 + 7^2 = 25 + 49 = 74
    7^2 + 4^2 = 49 + 16 = 65
    6^2 + 5^2 = 36 + 25 = 61
    6^2 + 1^2 = 36 + 1 = 37 --> loop
 */
