package leetcode.utils;

import java.io.InvalidClassException;
import java.util.*;
import java.util.stream.IntStream;

public class ArrayUtils {

    private ArrayUtils() throws InvalidClassException {
        throw new InvalidClassException("Util class, may not create instance of.");
    }

    /**
     * 36. Valid Sudoku
     *
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        Set<String> rowSet = new HashSet<>();
        Set<String> columnSet = new HashSet<>();
        Set<String> subBoxSet = new HashSet<>();

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] != '.') {

                    String valueSting = "(" + board[row][column] + ")";
                    
                    if (!rowSet.add(row + valueSting)) return false;

                    if (!columnSet.add(valueSting + column)) return false;

                    if (!subBoxSet.add(row / 3 + valueSting + column / 3)) return false;
                }
            }
        }
        return true;
    }

    /**
     * 1. Two Sum
     *
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numberIndexMap = new HashMap<>(nums.length);

        int[] answer = new int[2];

        for (int index = 0; index < nums.length; index++) {
            int compliment = target - nums[index];

            if (numberIndexMap.get(compliment) != null) {
                answer[0] = index;
                answer[1] = numberIndexMap.get(compliment);
                return answer;
            }
            numberIndexMap.put(nums[index], index);
        }

         return answer;
    }


    public static int[] twoSumBruteforce(int[] nums, int target) {
        int currentIndex = 0;

        int[] twoSumIndices = new int[2];

        while (currentIndex < nums.length - 1) {
            for (int addIndex = currentIndex + 1; addIndex < nums.length; addIndex++) {
                if (nums[currentIndex] + nums[addIndex] == target) {
                    twoSumIndices[0] = currentIndex;
                    twoSumIndices[1] = addIndex;
                    currentIndex = nums.length;
                    break;
                }
            }
            currentIndex++;
        }
        return twoSumIndices;
    }

    /**
     * 1706. Where Will the Ball Fall
     *
     * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom
     * sides.
     *
     * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right
     * or to the left.
     *
     * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after
     * dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
     *
     * @param grid
     * @return
     */
    public static int[] findBall(int[][] grid) {
        int length = grid[0].length;

        Map<Integer, Integer> ballIndexToCurrentColumn = new HashMap<>(length);
        LinkedList<Integer> ballsInPlay = new LinkedList<>();
        IntStream.range(0, length).forEachOrdered(x -> {
            ballIndexToCurrentColumn.put(x, x);
            ballsInPlay.add(x);
        });

        for (int[] row: grid) {
            ArrayList<Integer> ballsToRemove = new ArrayList<>();
            ballsInPlay.forEach(ball -> {
                int currentColumn = ballIndexToCurrentColumn.get(ball);
                if (row[currentColumn] == 1) {
                    if (currentColumn + 1 >= length || row[currentColumn + 1] == -1) {
                        ballsToRemove.add(ball);
                        ballIndexToCurrentColumn.put(ball, -1);
                    } else {
                        ballIndexToCurrentColumn.put(ball,currentColumn + 1);
                    }
                } else {
                    if (currentColumn - 1 < 0 || row[currentColumn - 1] == 1) {
                        ballsToRemove.add(ball);
                        ballIndexToCurrentColumn.put(ball, -1);
                    } else {
                        ballIndexToCurrentColumn.put(ball,currentColumn - 1);
                    }
                }
            });
            for (Integer ball : ballsToRemove) {
                ballsInPlay.remove(ball);
            }
            ballsToRemove.clear();
            if (ballsInPlay.size() == 0) break;
        }

        int[] exitPosition = new int[length];
        Arrays.fill(exitPosition, -1);
        ballsInPlay.forEach(ball -> exitPosition[ball] = ballIndexToCurrentColumn.get(ball));
        return exitPosition;
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
