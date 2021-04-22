package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/eight-queens-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-03-01 14:31:16
 */
public class SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new LinkedList<>();
        LinkedList<Integer> now = new LinkedList<>();
        dfs(n, 0, result, now);
        return result;
    }

    void dfs(int n, int index, List<List<String>> result, LinkedList<Integer> now) {
        if (index == n) {
            result.add(build(now, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < now.size() && flag; j++) {
                int tag = now.get(j);
                if (i == tag || tag - index + j == i || tag + index - j == i) {
                    flag = false;
                }
            }
            if (!flag) {
                continue;
            }
            now.add(i);
            dfs(n, index + 1, result, now);
            now.removeLast();
        }
    }

    List<String> build(List<Integer> now, int n) {
        List<String> board = new ArrayList<String>();
        char[] row = new char[n];
        Arrays.fill(row, '.');
        for (int i = 0; i < n; i++) {
            row[now.get(i)] = 'Q';
            board.add(new String(row));
            row[now.get(i)] = '.';
        }
        return board;
    }
}
