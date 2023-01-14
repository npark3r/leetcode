package leetcode.utils.strings;

import leetcode.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsSubsequenceTest {

    @Test
    void test1() {
        String input1 = "abc";
        String input2 = "ahbgdc";
        Assertions.assertEquals(true, StringUtils.isSubsequence(input1, input2));
    }

    @Test
    void test2() {
        String input1 = "axc";
        String input2 = "ahbgdc";
        assertEquals(false, StringUtils.isSubsequence(input1, input2));
    }

    @Test
    void test3() {
        String input1 = "";
        String input2 = "ahbgdc";
        assertEquals(true, StringUtils.isSubsequence(input1, input2));
    }
}
