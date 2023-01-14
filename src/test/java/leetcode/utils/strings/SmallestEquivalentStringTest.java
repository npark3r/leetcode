package leetcode.utils.strings;

import leetcode.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmallestEquivalentStringTest {

    @Test
    void test1() {
        String input1 = "cgokcgerolkgksgbhgmaaealacnsshofjinidiigbjerdnkolc";
        String input2 = "rjjlkbmnprkslilqmbnlasardrossiogrcboomrbcmgmglsrsj";
        String base = "bxbwjlbdazfejdsaacsjgrlxqhiddwaeguxhqoupicyzfeupcn";
        Assertions.assertEquals("axawaaaaazaaaaaaaaaaaaaxaaaaawaaauxaaauaaayzaauaaa", StringUtils.smallestEquivalentString(input1, input2, base));
    }
}
