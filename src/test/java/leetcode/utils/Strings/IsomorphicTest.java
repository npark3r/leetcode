package leetcode.utils.Strings;

import leetcode.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsomorphicTest {

    @Test
    void test1() {
        String input1 = "egg";
        String input2 = "add";
        Assertions.assertEquals(true, StringUtils.isIsomorphic(input1, input2));
    }

    @Test
    void test2() {
        String input1 = "foo";
        String input2 = "bar";
        assertEquals(false, StringUtils.isIsomorphic(input1, input2));
    }

    @Test
    void test3() {
        String input1 = "paper";
        String input2 = "title";
        assertEquals(true, StringUtils.isIsomorphic(input1, input2));
    }

    @Test
    void test4() {
        String input1 = "badc";
        String input2 = "baba";
        assertEquals(false, StringUtils.isIsomorphic(input1, input2));
    }
}
