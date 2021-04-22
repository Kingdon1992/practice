package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二维的矩阵，包含 ''X'' 和 ''O''（字母 'O'）。
 * <p>
 * 找到所有被 ''X'' 围绕的区域，并将这些区域里所有的 ''O'' 用 ''X'' 填充。
 * <p>
 * 示例:
 * <p>
 * 'X' 'X' 'X' 'X'
 * 'X' 'O' 'O' 'X'
 * 'X' 'X' 'O' 'X'
 * 'X' 'O' 'X' 'X'
 * 运行你的函数后，矩阵变为：
 * <p>
 * 'X' 'X' 'X' 'X'
 * 'X' 'X' 'X' 'X'
 * 'X' 'X' 'X' 'X'
 * 'X' 'O' 'X' 'X'
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 ''O'' 都不会被填充为 ''X''。 任何不在边界上，或不与边界上的 ''O'' 相连的 ''O'' 最终都会被填充为 ''X''。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-25 10:58:49
 */
public class Solve {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'O', 'X', 'O', 'O'},
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        Solve solve = new Solve();
        solve.solve(board);
        System.out.println();
    }

    public void solve(char[][] board) {
        int m=board.length;
        int n=board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if((i!=0&&i!=m-1&&j!=0&&j!=n-1)||board[i][j]!='O'){
                    continue;
                }
                dfs(board,i,j);
            }
        }
        for(int j=0;j<n;j++){
            dfs(board,0,j);
            dfs(board,m-1,j);
        }
        for(int i=1;i<m-1;i++){
            dfs(board,i,0);
            dfs(board,i,n-1);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='A'){
                    board[i][j]='O';
                }else{
                    board[i][j]='X';
                }
            }
        }
    }

    void dfs(char[][] board,int i,int j){
        if(i<0||j<0||i==board.length||j==board[0].length||board[i][j]!='O'){
            return;
        }
        board[i][j]='A';
        dfs(board,i,j+1);
        dfs(board,i,j-1);
        dfs(board,i+1,j);
        dfs(board,i-1,j);
    }
}
