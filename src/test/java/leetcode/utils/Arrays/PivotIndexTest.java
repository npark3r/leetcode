package leetcode.utils.Arrays;

import leetcode.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PivotIndexTest {

    @Test
    void test1() {
        int[] nums = {1,7,3,6,5,6};
        Assertions.assertEquals(3, ArrayUtils.pivotIndex(nums));
    }

    @Test
    void test2() {
        int[] nums = {1,2,3};
        Assertions.assertEquals(-1, ArrayUtils.pivotIndex(nums));
    }

    @Test
    void test3() {
        int[] nums = {2,1,-1};
        Assertions.assertEquals(0, ArrayUtils.pivotIndex(nums));
    }
}
