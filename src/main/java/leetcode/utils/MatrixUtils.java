package leetcode.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixUtils {

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
