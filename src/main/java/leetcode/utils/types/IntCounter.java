package leetcode.utils.types;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class IntCounter {
    Integer[][] integerMatrix;

    public IntCounter(Integer[][] integerMatrix) {
        this.integerMatrix = integerMatrix;
    }

    public void incrementLocationToValue(int row, int column, int value) {
        try {
            Thread.sleep(new Random().nextLong(100, 200));
        } catch (InterruptedException exception) {
        }
        integerMatrix[row][column] = integerMatrix[row][column] + value;
    }
}