package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 *
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 *
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : wangqingsong
 * @since : 2021-03-11 10:15:43
 */
public class CalculateV2 {
    public int calculate(String s) {
        int len=s.length();
        int i=0;
        int sign=1;
        char lastChar=' ';
        Deque<Integer> stack=new LinkedList<>();
        while(i<len){
            char c=s.charAt(i);
            if(c==' '){
                i++;
            }else if(c=='+'){
                sign=1;
                i++;
                lastChar='+';
            }else if(c=='-'){
                sign=-1;
                i++;
                lastChar='-';
            }else if(c=='/'){
                i++;
                lastChar='/';
            }else if(c=='*'){
                i++;
                lastChar='*';
            }else{
                //拿到具体数字的值
                int num=0;
                while(i<len&&Character.isDigit(s.charAt(i))){
                    num=num*10+s.charAt(i)-'0';
                    i++;
                }
                //根据符号确定当前值
                if(lastChar=='*'){
                    stack.push(stack.pop()*num);
                }else if(lastChar=='/'){
                    stack.push(stack.pop()/num);
                }else{
                    stack.push(sign*num);
                }
            }
        }
        int result=0;
        while(!stack.isEmpty()){
            result+=stack.pop();
        }
        return result;
    }
}
