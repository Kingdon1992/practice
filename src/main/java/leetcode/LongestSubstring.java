package leetcode;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 *
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : wangqingsong
 * @since : 2021-02-27 11:19:41
 */
public class LongestSubstring {
    public static void main(String[] args) {
        LongestSubstring l = new LongestSubstring();
        System.out.println(l.longestSubstring("ababacb", 3));
        System.out.println(l.longestSubstring("bbaaacbd", 3));
        System.out.println(l.longestSubstring("baaabcb", 3));
        System.out.println(l.longestSubstring("aaabbb", 3));
    }

    public int longestSubstring(String s, int k) {
        return longestSubstring(s,k,0,s.length());
    }

    public int longestSubstring(String s, int k,int lo,int hi) {
        if (hi-lo<k){
            return 0;
        }
        int[] tag=new int[26];
        for(int i=lo;i<hi;i++){
            tag[s.charAt(i)-'a']++;
        }
        int i=lo;
        int j=lo;
        int max=0;
        while(i<hi&&j<hi){
            if(tag[s.charAt(j)-'a']>0&&tag[s.charAt(j)-'a']<k){
                max=Math.max(max,longestSubstring(s,k,i,j));
                i=j+1;
                j=j+1;
            }else{
                j++;
            }
        }
        if(i==lo){
            max=Math.max(max,j-i);
        }else{
            max=Math.max(max,longestSubstring(s,k,i,j));
        }
        return max;
    }
}
