package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-03-03 16:41:47
 */
public class ValidateStackSequences {
    public static void main(String[] args) {
        ValidateStackSequences v = new ValidateStackSequences();
        int[] pushed = {0, 2, 1};
        int[] popped = {0, 1, 2};
        boolean b = v.validateStackSequences(pushed, popped);
        System.out.println(b);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = popped.length;
        if (len <= 2) {
            return true;
        }
        Deque<Integer> stack = new LinkedList<>();
        int j = 0;
        int i = 0;
        while (i < len) {
            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                if(stack.isEmpty()){
                    stack.push(pushed[i]);
                    i++;
                }else{
                    if(stack.peek() == popped[j]){
                        stack.pop();
                        j++;
                    }else {
                        stack.push(pushed[i]);
                        i++;
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != popped[j]) {
                return false;
            }
            j++;
        }
        return true;
    }
}
