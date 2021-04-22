package leetcode;

/**
 * 编写一个程序，通过填充空格来解决数独问题。9X9
 *
 * @author : wangqingsong
 * @since : 2021-02-26 17:34:57
 */
public class SolveSudoku {
    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        SolveSudoku solveSudoku = new SolveSudoku();
        solveSudoku.solveSudoku(board);
    }

    public void solveSudoku(char[][] board) {
        int[][] line = new int[9][9];
        int[][] row = new int[9][9];
        int[][] s = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    line[i][c - '0' - 1] = 1;
                    row[j][c - '0' - 1] = 1;
                    s[getS(i, j)][c - '0' - 1] = 1;
                }
            }
        }
        bfs(board, line, row, s, 0, 0);
    }


    boolean bfs(char[][] board, int[][] line, int[][] row, int[][] s, int i, int j) {
        if (i == 9) {
            return true;
        }
        if (j == 9) {
            return bfs(board, line, row, s, i + 1, 0);
        } else {
            char c = board[i][j];
            if (c == '.') {
                boolean bfs = false;
                for (int k = 0; k < 9; k++) {
                    if (line[i][k] == 0 && row[j][k] == 0 && s[getS(i, j)][k] == 0) {
                        board[i][j] = (char) (k + '0' + 1);
                        line[i][k] = 1;
                        row[j][k] = 1;
                        s[getS(i, j)][k] = 1;
                        bfs = bfs(board, line, row, s, i, j + 1);
                        if (!bfs) {
                            line[i][k] = 0;
                            row[j][k] = 0;
                            s[getS(i, j)][k] = 0;
                            board[i][j] = '.';
                        }
                    }
                }
                return bfs;
            } else {
                return bfs(board, line, row, s, i, j + 1);
            }
        }
    }

    int getS(int i, int j) {
        return i / 3 + j / 3 * 3;
    }
}
