package leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : wangqingsong
 * @since : 2021-02-28 22:50:26
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String a = longestPalindrome.longestPalindromeB("asdgabcdcbadaew");
        System.out.println(a);
    }
    public String longestPalindrome(String s) {
        int n=s.length();
        String result="";
        boolean[][] tag=new boolean[n][n];
        for(int len=0;len<n;len++){
            for(int i=0;i<n-len;i++){
                int j=len+i;
                if(len==0){
                    tag[i][j]=true;
                }else if(len==1){
                    tag[i][j]=s.charAt(i)==s.charAt(j);
                }else{
                    tag[i][j]=tag[i+1][j-1]&&s.charAt(i)==s.charAt(j);
                }
                if(tag[i][j]){
                    result=s.substring(i,j+1);
                }
            }
        }
        return result;
    }
    public String longestPalindromeB(String s) {
        int n=s.length()-1;
        String result="";
        for(int i=0;i<=n;i++){
            int sin=process(s,i,i);
            int dou=process(s,i,i+1);
            int max=Math.max(sin,dou);
            if(max>result.length()){
                int lo=i-(max-1)/2;
                int hi=i+max/2;
                result=s.substring(lo,hi+1);
            }
        }
        return result;
    }

    int process(String s,int i,int j){
        int len=0;
        if(i==j){
            len=-1;
        }
        while(i>=0&&j<s.length()&&s.charAt(i--)==s.charAt(j++)){
            len+=2;
        }
        return len;
    }
}
