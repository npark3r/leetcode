package leetcode.utils.concurrency;

public class CanSum {

    public static boolean canSum(int target, int[] intArray) {
        if (target < 0) return false;
        if (target == 0) return true;

        for (int number: intArray) {
            if (canSum(target - number, intArray)) return true;
        }

        return false;
    }

    public static boolean canSumMemoized(int target, int[] intArray) {

        return false;
    }

    public static boolean canSumWithMemo(int target, int[] intArray) {

        return false;
    }



}
