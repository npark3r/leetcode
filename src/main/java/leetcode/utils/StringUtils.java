package leetcode.utils;

import java.io.InvalidClassException;
import java.util.HashMap;

public class StringUtils {

    private StringUtils() throws InvalidClassException {
        throw new InvalidClassException("Util class, may not create instance of.");
    }

    /**
     * 392. Is Subsequence
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     *
     * Result: Accepted
     * Runtime beats: 100%
     * Memory beats: 84.89%
     *
     * Notes:
     * Simple recursion to check substrings
     *
     * @param s first string
     * @param t second string
     * @return whether the strings are isomorphic
     */
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() < 1 || s.length() > t.length()) {
            return false;
        }

        int positionOfFirstChar = t.indexOf(s.charAt(0));

        if (positionOfFirstChar == -1) {
            return false;
        }

        String subString = t.substring(positionOfFirstChar + 1);

        return isSubsequence(s.substring(1), subString);
    }

    /**
     * 205. Isomorphic Strings
     * Return whether two strings are isomorphic.
     * <p>
     * Result: Accepted
     * Runtime beats: 71.33%
     * Memory beats: 81.37%
     *
     * Notes:
     * Bi-directional maps
     *
     * @param s first string
     * @param t second string
     * @return whether the strings are isomorphic
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() < 1 || t.length() < 1 || s.length() != t.length()) return false;

        boolean isIsomorphic = true;

        HashMap<Character, Character> characterMapS = new HashMap<>(s.length());
        HashMap<Character, Character> characterMapT = new HashMap<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            char currentS = s.charAt(i);
            char currentT = t.charAt(i);
            Character maybeCharT = characterMapS.get(currentS);
            if (maybeCharT != null && maybeCharT != currentT) {
                isIsomorphic = false;
                break;
            }
            if (maybeCharT == null) {
                characterMapS.put(currentS, currentT);
            }
            Character maybeCharS = characterMapT.get(currentT);
            if (maybeCharS != null && maybeCharS != currentS) {
                isIsomorphic = false;
                break;
            }
            if (maybeCharS == null) {
                characterMapT.put(currentT, currentS);
            }
        }

        return isIsomorphic;
    }
}
