package leetcode.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestThings {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        List<String> list = new ArrayList<>();
        list.add("Test");
        list.add("Second");

        System.out.println(32 >> 3);

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime);
    }

    public static boolean canJump(int[] nums) {
        if (nums.length < 2) return true;

        int currentIndex = nums.length - 2;
        int neededJumps;
        while (currentIndex >= 0) {
            neededJumps = 1;
            while (neededJumps > nums[currentIndex]) {
                neededJumps++;
                currentIndex--;
                if (currentIndex < 0) return false;
            }
            if (currentIndex == 0 && nums[currentIndex] >= 1) return true;
            currentIndex--;
        }
        return false;
    }
}
