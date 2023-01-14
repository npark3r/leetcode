package leetcode.utils.Arrays;

import leetcode.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RunningSumTest {

    @Test
    void test1() {
        int[] nums = {1,2,3,4};
        Assertions.assertArrayEquals(new int[]{1, 3, 6, 10}, ArrayUtils.runningSum(nums));
    }

    @Test
    void test2() {
        int[] nums = {1,1,1,1,1};
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ArrayUtils.runningSum(nums));
    }

    @Test
    void test3() {
        int[] nums = {3,1,2,10,1};
        Assertions.assertArrayEquals(new int[]{3,4,6,16,17}, ArrayUtils.runningSum(nums));
    }
}
