package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-03-01 11:07:08
 */
public class Partition {
    public static void main(String[] args) {
        String str = "abcba";
        char[] chars = str.toCharArray();
        Partition p = new Partition();
        String s = new String(chars, 1, 2);
    }

    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        List<List<String>> result = new LinkedList<>();
        LinkedList<String> now = new LinkedList<>();
        dfs(chars, 0, result, now);
        return result;
    }

    void dfs(char[] chars, int index, List<List<String>> result, LinkedList<String> now) {
        if (index == chars.length) {
            result.add(new ArrayList<>(now));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            if (huiwen(chars, index, i)) {
                now.add(new String(chars, index, i - index + 1));
                dfs(chars, i + 1, result, now);
                now.removeLast();
            }
        }
    }

    boolean huiwen(char[] chars, int i, int j) {
        while (i < j) {
            if (chars[i] == chars[j]) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
