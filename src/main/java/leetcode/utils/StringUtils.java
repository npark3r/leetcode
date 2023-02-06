package leetcode.utils;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import java.io.InvalidClassException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

    private StringUtils() throws InvalidClassException {
        throw new InvalidClassException("Util class, may not create instance of.");
    }

    /**
     * 942. DI String Match
     *
     * A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string s of length n where:
     *
     * s[i] == 'I' if perm[i] < perm[i + 1], and
     * s[i] == 'D' if perm[i] > perm[i + 1].
     * Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm, return any of them.
     *
     *  Notes:
     *   First attempt, slow and lots of memeory
     * @param s
     * @return
     */
    public static int[] diStringMatch1(String s) {
        int length = s.length();

        List<Integer> list = IntStream.range(0, length + 1).boxed().toList();

        LinkedList<Integer> linkedList = new LinkedList<>(list);

        int[] intArray = new int[length + 1];

        for (int index = 0; index < length; index++) {
            switch (s.charAt(index)) {
                case 'I':
                    intArray[index] = linkedList.removeFirst();
                    break;
                case 'D':
                    intArray[index] = linkedList.removeLast();
                    break;
                default:
                    break;
            }

        }
        intArray[length] = linkedList.removeLast();
        return intArray;
    }

    /**
     * 942. DI String Match
     *
     * A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string s of length n where:
     *
     * s[i] == 'I' if perm[i] < perm[i + 1], and
     * s[i] == 'D' if perm[i] > perm[i + 1].
     * Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm, return any of them.
     *
     *  Notes:
     *   Second attempt
     *   Beats 97% runtime
     *   Beats 70% memory
     * @param s
     * @return
     */
    public static int[] diStringMatch(String s) {
        int length = s.length();

        int low = 0;
        int high = length;

        int[] intArray = new int[length + 1];

        for (int index = 0; index < length; index++) {
            switch (s.charAt(index)) {
                case 'I':
                    intArray[index] = low;
                    low++;
                    break;
                case 'D':
                    intArray[index] = high;
                    high--;
                    break;
                default:
                    break;
            }

        }
        intArray[length] = low;
        return intArray;
    }

    /**
     * 1061. Lexicographically Smallest Equivalent String
     *
     * Result: Accepted
     * Runtime beats: 7.39%
     * Memory beats: 15.34%
     *
     * Notes:
     * None-tree approach. Performance is poor comparatively.
     *
     * @param s1
     * @param s2
     * @param baseStr
     * @return
     */
    public static String smallestEquivalentString(String s1, String s2, String baseStr) {

        List<Set<Character>> listOfOrderedSets = new ArrayList<>(s1.length());

        for (int i = 0; i < s1.length(); i++) {
            char currentS1Forward = s1.charAt(i);
            char currentS2Forward = s2.charAt(i);
            char currentS1Backward = s1.charAt(s1.length()-i-1);
            char currentS2Backward = s2.charAt(s2.length()-i-1);

            boolean foundSetForward = false;
            boolean foundSetBackward = false;

            for (Set<Character> charSet : listOfOrderedSets) {
                if (charSet.contains(currentS1Forward) || charSet.contains(currentS2Forward)) {
                    charSet.add(currentS1Forward);
                    charSet.add(currentS2Forward);
                    foundSetForward = true;
                }
                if (charSet.contains(currentS1Backward) || charSet.contains(currentS2Backward)) {
                    charSet.add(currentS1Backward);
                    charSet.add(currentS2Backward);
                    foundSetBackward = true;
                }
            }
            if (!foundSetForward) {
                SortedSet<Character> newCharSet = new TreeSet<>();
                newCharSet.add(currentS1Forward);
                newCharSet.add(currentS2Forward);
                listOfOrderedSets.add(newCharSet);
            }
            if (!foundSetBackward) {
                SortedSet<Character> newCharSet = new TreeSet<>();
                newCharSet.add(currentS1Backward);
                newCharSet.add(currentS2Backward);
                listOfOrderedSets.add(newCharSet);
            }
        }

        StringBuilder smallestEquivalentString = new StringBuilder();

        char candidate = 'a';

        for (int j = 0; j < baseStr.length(); j++) {
            char currentBase = baseStr.charAt(j);
            boolean foundInSets = false;
            for (Set<Character> charSet : listOfOrderedSets) {
                if (charSet.contains(currentBase)) {
                    if(foundInSets) {
                        candidate = charSet.stream().findFirst().get() > candidate ? candidate : charSet.stream().findFirst().get();
                    } else {
                        candidate = charSet.stream().findFirst().get();
                    }
                    foundInSets = true;
                }
            }
            if (!foundInSets) {
                smallestEquivalentString.append(currentBase);
            } else {
                smallestEquivalentString.append(candidate);
            }
        }

        return smallestEquivalentString.toString();
    }

    /**
     * 392. Is Subsequence:
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
