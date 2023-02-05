package leetcode.utils.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GridTraveler {

    public static int gridTraveler(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        if (m == 1 && n == 1) {
            return 1;
        }

        int possible = 0;
        if (m > 1) {
            possible += gridTraveler(m - 1, n);
        }
        if (n > 1) {
            possible += gridTraveler(m, n - 1);
        }
        return possible;
    }

    private static final String SEPARATOR = ",";

    public static int gridTravelerMemoized(int m, int n) {

        Map<String, Integer> visitedGrids = new HashMap<>(m*n);

        String key = m + SEPARATOR + n;

        if (m <= 0 || n <= 0) {
            return 0;
        }
        visitedGrids.put(1 + SEPARATOR + 1, 1);

        int possible = gridTravelerWithMemo(m - 1, n, visitedGrids) + gridTravelerWithMemo(m, n - 1, visitedGrids);
        visitedGrids.put(key, possible);
        return possible;
    }

    public static int gridTravelerWithMemo(int m, int n, Map<String, Integer> memo) {
        String key = m + SEPARATOR + n;

        if (m == 0 || n == 0) {
            return 0;
        }

        if (memo.get(key) != null) {
            return memo.get(key);
        }

        int possible = gridTravelerWithMemo(m - 1, n, memo) + gridTravelerWithMemo(m, n - 1, memo);
        memo.put(key, possible);
        return possible;
    }
}
