package leetcode.utils;

import java.io.InvalidClassException;

public class ArrayUtils {

    private ArrayUtils() throws InvalidClassException {
        throw new InvalidClassException("Util class, may not create instance of.");
    }

    /**
     * 1480. Isomorphic Strings
     * Return array transformed to running sum of all previous elements
     *
     * Result: Accepted
     * Runtime beats: 100%
     * Memory beats: 72.96%
     *
     * Notes:
     * Converge left and right pointers. Prefer left if unequal. Then test shifting merged pointer first left
     * then right.
     *
     * @param nums the array to tranform
     * @return the array of running sums
     */
    public static int pivotIndex(int[] nums) {
        int leftPointer = 0;
        int rightPointer = nums.length - 1;

        int leftTotal = 0;
        int rightTotal = 0;

        while (leftPointer != rightPointer) {
            if (rightPointer - leftPointer == 1) {
                rightTotal += nums[rightPointer];
                rightPointer--;
                continue;
            }

            leftTotal += nums[leftPointer];
            rightTotal += nums[rightPointer];

            leftPointer++;
            rightPointer--;
        }

        boolean noLongerCheckRight = false;

        int foundOrNegative = -1;
        // check left versus right
        if (leftTotal == rightTotal) {
            foundOrNegative = leftPointer;
            noLongerCheckRight = true;
        }

        int leftTotal2 = leftTotal;
        int rightTotal2 = rightTotal;

        for (int j = 1; j <= nums.length / 2; j++) {
            if (leftPointer - j + 1 != 0) {
                // moving left
                rightTotal += nums[leftPointer - j + 1];
                leftTotal -= nums[leftPointer - j];

                if (leftTotal == rightTotal) {
                    noLongerCheckRight = true;
                    foundOrNegative = leftPointer - j;
                }
            }
            if (rightPointer + j - 1 != nums.length - 1 && !noLongerCheckRight) {
                // moving right
                rightTotal2 -= nums[rightPointer + j];
                leftTotal2 += nums[rightPointer + j - 1];

                if (leftTotal2 == rightTotal2) {
                    foundOrNegative = rightPointer + j;
                    noLongerCheckRight = true;
                }
            }
        }

        return foundOrNegative;
    }

    /**
     * 1480. Isomorphic Strings
     * Return array transformed to running sum of all previous elements
     * <p>
     * Result: Accepted
     * Runtime beats: 100%
     * Memory beats: 82.94%
     *
     * Modify supplied array with updated running total
     *
     * @param nums the array to tranform
     * @return the array of running sums
     */
    public static int[] runningSum(int[] nums) {
        int runningTotal = 0;
        for (int i = 0; i < nums.length; i++) {
            runningTotal += nums[i];
            nums[i] = runningTotal;
        }
        return nums;
    }
}
