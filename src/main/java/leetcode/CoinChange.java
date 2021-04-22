package leetcode;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-03-15 17:28:38
 */
public class CoinChange {
    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int i = c.coinChange2(new int[]{2}, 3);
        System.out.println(i);
    }

    public int coinChange2(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                int res=i-coins[j];
                if(res>=0){
                    dp[i]=Math.min(dp[res]+1,dp[i]);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }


    Integer min;
    public int coinChange(int[] coins, int amount) {
        min=Integer.MAX_VALUE;
        dfs(coins,amount,0,coins.length-1);
        return min==Integer.MAX_VALUE?-1:min;
    }

    void dfs(int[] coins,int target,int count,int index){
        if(target==0){
            min=Math.min(min,count);
            return;
        }

        for(int i=index;i>=0;i--){
            if(target-coins[i]<0){
                continue;
            }
            dfs(coins,target-coins[i],count+1,i);
        }
    }
}
