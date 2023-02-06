package leetcode.utils.arrays;

import leetcode.utils.ArrayUtils;
import leetcode.utils.RandomOtherClass;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("RunningSumTests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RunningSumTest {

    ArrayUtils arrayUtils;
    RandomOtherClass randomOtherClass;

    @BeforeAll
    public void setup() {
        System.out.println("I got run");

        randomOtherClass = mock(RandomOtherClass.class);

        arrayUtils = new ArrayUtils(randomOtherClass);
    }

    @DisplayName("running sum returns correct array")
    @Test
    void test1() {
        int[] nums = {1,2,3,4};

        when(randomOtherClass.randomOtherMethod(any(String.class))).thenReturn("test12");

        assertArrayEquals(new int[]{1, 3, 6, 10}, arrayUtils.runningSum(nums));
    }

    @DisplayName("running sum returns next correct array")
    @Test
    void test2() {
        int[] nums = {1,2,3,4};

        when(randomOtherClass.randomOtherMethod(any(String.class))).thenReturn("test123");

        ArrayUtils arrayUtils = new ArrayUtils(randomOtherClass);

        assertArrayEquals(new int[]{1, 3, 6, 10}, arrayUtils.runningSum(nums));
    }

//    @Test
//    void test2() {
//        int[] nums = {1,1,1,1,1};
//        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ArrayUtils.runningSum(nums));
//    }
//
//    @Test
//    void test3() {
//        int[] nums = {3,1,2,10,1};
//        assertArrayEquals(new int[]{3,4,6,16,17}, ArrayUtils.runningSum(nums));
//    }
}
