package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-03-02 10:11:46
 */
public class NumMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println();
    }

    int[][] dfa;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        dfa = new int[m + 1][n + 1];
        dfa[1][1] = matrix[0][0];
        for (int j = 1; j <= n; j++) {
            dfa[1][j] = dfa[1][j - 1] + matrix[0][j - 1];
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dfa[i][j] = dfa[i][j - 1] + dfa[i - 1][j] - dfa[i-1][j-1]+matrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dfa[row2][col2] - dfa[row2][col1 - 1] - dfa[row1 - 1][col2] + dfa[row1 - 1][col1 - 1];
    }
}
