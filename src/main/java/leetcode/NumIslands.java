package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : wangqingsong
 * @since : 2021-02-25 10:40:07
 */
public class NumIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        int[] pi = {0, 0, 1, -1};
        int[] pj = {1, -1, 0, 0};
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Deque<Integer> stacki = new LinkedList<>();
                Deque<Integer> stackj = new LinkedList<>();
                stacki.push(i);
                stackj.push(j);
                if (grid[i][j] == '1') {
                    count++;
                }
                while (!stacki.isEmpty()) {
                    int nowi = stacki.pop();
                    int nowj = stackj.pop();
                    if (nowi < 0 || nowj < 0 || nowi == m || nowj == n || grid[nowi][nowj] == '0') {
                        continue;
                    }
                    grid[nowi][nowj] = '0';
                    for (int x = 0; x < 4; x++) {
                        stacki.push(nowi + pi[x]);
                        stackj.push(nowj + pj[x]);
                    }
                }

            }
        }
        return count;
    }
}
