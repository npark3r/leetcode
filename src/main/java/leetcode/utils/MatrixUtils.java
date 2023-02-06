package leetcode.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixUtils {

    /**
     *  892. Surface Area of 3D Shapes
     *
     *  You are given an n x n grid where you have placed some 1 x 1 x 1 cubes. Each value v = grid[i][j] represents a tower of v cubes placed on top of cell (i, j).
     *
     * After placing these cubes, you have decided to glue any directly adjacent cubes to each other, forming several irregular 3D shapes.
     *
     * Return the total surface area of the resulting shapes.
     *
     * Beats around 60% in runtime and memory.
     * To increase performance, simply memo the diffs between cells and fetch from HashMap
     *
     * @param grid
     * @return
     */
    public static int surfaceArea(int[][] grid) {
        int surfaceArea = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // for each cell find its contribution to area
                if (grid[row][col] == 0) continue;
                // top + bottom
                surfaceArea += 2;
                // up
                if (row - 1 >= 0) {
                    int diff = grid[row][col] - grid[row - 1][col];
                    if (diff > 0) {
                        surfaceArea += diff;
                    }
                } else {
                    surfaceArea += grid[row][col] ;
                }

                // left
                if (col - 1 >= 0) {
                    int diff = grid[row][col] - grid[row][col - 1];
                    if (diff > 0) {
                        surfaceArea += diff;
                    }
                } else {
                    surfaceArea += grid[row][col] ;
                }

                // down
                if (row + 1 < grid.length) {
                    int diff = grid[row][col] - grid[row + 1][col];
                    if (diff > 0) {
                        surfaceArea += diff;
                    }
                } else {
                    surfaceArea += grid[row][col] ;
                }

                // right
                if (col + 1 < grid[0].length) {
                    int diff = grid[row][col] - grid[row][col + 1];
                    if (diff > 0) {
                        surfaceArea += diff;
                    }
                } else {
                    surfaceArea += grid[row][col] ;
                }
            }
        }
        return surfaceArea;
    }

    /**
     * ???. Spiral order
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {


        int height = matrix.length;
        int length = matrix[0].length;

        Map<Direction, Integer> directionBoundsMap = new HashMap<>();
        directionBoundsMap.put(Direction.RIGHT, length);
        directionBoundsMap.put(Direction.DOWN, height);
        directionBoundsMap.put(Direction.LEFT, -1);
        directionBoundsMap.put(Direction.UP, 0);

        Direction currentDirection = Direction.RIGHT;
        int currentRow = 0;
        int currentColumn = 0;

        boolean[][] checkedMatrix = new boolean[height][length];

        List<Integer> spiralOrder = new ArrayList<>();

        boolean canMoveRight;
        boolean canMoveDown;
        boolean canMoveLeft;
        boolean canMoveUp;

        while (true) {
            spiralOrder.add(matrix[currentRow][currentColumn]);
            checkedMatrix[currentRow][currentColumn] = true;

            canMoveRight = !checkedMatrix[currentRow][Math.min(currentColumn + 1, length - 1)];
            canMoveDown = !checkedMatrix[Math.min(currentRow + 1, height - 1)][currentColumn];
            canMoveLeft = !checkedMatrix[currentRow][Math.max(currentColumn - 1, 0)];
            canMoveUp = !checkedMatrix[Math.max(currentRow - 1, 0)][currentColumn];

            if (!canMoveRight
                    && !canMoveDown
                    && !canMoveLeft
                    && !canMoveUp) {
                break;
            }
            switch (currentDirection) {
                case RIGHT:
                    if (!canMoveRight) {
                        currentDirection = Direction.DOWN;
                        currentRow++;
                        directionBoundsMap.put(Direction.RIGHT, currentColumn);
                    } else {
                        currentColumn++;
                    }
                    break;
                case DOWN:
                    if (!canMoveDown) {
                        currentDirection = Direction.LEFT;
                        currentColumn--;
                        directionBoundsMap.put(Direction.DOWN, currentRow);
                    } else {
                        currentRow++;
                    }
                    break;
                case LEFT:
                    if (!canMoveLeft) {
                        currentDirection = Direction.UP;
                        currentRow--;
                        directionBoundsMap.put(Direction.LEFT, currentColumn);
                    } else {
                        currentColumn--;
                    }
                    break;
                case UP:
                    if (!canMoveUp) {
                        currentDirection = Direction.RIGHT;
                        currentColumn++;
                        directionBoundsMap.put(Direction.UP, currentRow);
                    } else {
                        currentRow -= 1;
                    }
                    break;
            }
        }
        return spiralOrder;
    }

    public enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }
}
