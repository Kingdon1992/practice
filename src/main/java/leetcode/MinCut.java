package leetcode;

import java.util.Arrays;

/**
 * @author : wangqingsong
 * @since : 2021-03-08 14:47:19
 */
public class MinCut {
    public static void main(String[] args) {
        MinCut minCut = new MinCut();
        int i = minCut.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp");
        System.out.println(i);
    }
    public int minCut(String s) {
        int n=s.length();
        //记录是否为回文字符串
        boolean[][] tag=new boolean[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(tag[i],true);
        }
        for(int len=1;len<n;len++){
            for(int i=0;i+len<n;i++){
                int j=i+len;
                tag[i][j]=(s.charAt(i)==s.charAt(j))&&tag[i+1][j-1];
            }
        }

        int[] dp=new int[n];
        dp[0]=0;
        for(int i=1;i<n;i++){
            if(tag[0][i]){
                dp[i]=0;
                continue;
            }
            int min=Integer.MAX_VALUE;
            for(int j=0;j<i;j++){
                if(tag[j+1][i]){
                    min=Math.min(dp[j],min);
                }
            }
            dp[i]=min+1;

        }
        return dp[n-1];
    }
}
