package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-02-08 15:36:16
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        System.out.println(l.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(l.longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(l.longestCommonPrefix(new String[]{}));
        System.out.println(l.longestCommonPrefix(new String[]{"flower", "", "flight"}));
        System.out.println(l.longestCommonPrefix(new String[]{"", "flow", ""}));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String zero = strs[0];
        if (zero.length() == 0) {
            return "";
        }
        StringBuilder prefix = new StringBuilder();
        String str;
        boolean flag;
        for (int i = 0; i < zero.length(); i++) {
            char c = zero.charAt(i);
            flag = true;
            for (int j = 0; j < strs.length; j++) {
                str = strs[j];
                if (i >= str.length()) {
                    flag = false;
                    break;
                }
                if (str.charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                prefix.append(c);
            } else {
                break;
            }
        }
        return prefix.toString();
    }
}
