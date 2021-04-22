package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 *  
 *
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 *
 * 输入：S = "12345"
 * 输出：["12345"]
 *  
 *
 * 提示：
 *
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : wangqingsong
 * @since : 2021-02-26 16:20:46
 */
public class LetterCasePermutation {

    public static void main(String[] args) {
        LetterCasePermutation l = new LetterCasePermutation();
        List<String> list = l.letterCasePermutation("a1b2");
        System.out.println();
    }

    public List<String> letterCasePermutation(String S) {
        List<String> result=new ArrayList<>();
        StringBuilder now=new StringBuilder(S);
        dfs(0,result,now);
        return result;
    }

    void dfs(int index,List<String> result,StringBuilder now){
        result.add(now.toString());
        for(int i=index;i<now.length();i++){
            char c=now.charAt(i);
            if(c>'9'){
                if(c>='a'){
                    now.replace(i,i+1,(char)(c-32)+"");
                    dfs(i+1,result,now);
                    now.replace(i,i+1,c+"");
                }else{
                    now.replace(i,i+1,(char)(c+32)+"");
                    dfs(i+1,result,now);
                    now.replace(i,i+1,c+"");
                }
            }
        }
    }
}
