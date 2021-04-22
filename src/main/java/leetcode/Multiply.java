package leetcode;

import java.util.ArrayList;

/**
 * @author : wangqingsong
 * @since : 2021-02-08 11:52:53
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Multiply {
    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        System.out.println(multiply.multiply("7188", "102"));
    }

    public String multiply(String num1, String num2) {
        String first;
        String last;
        if (num1.length() > num2.length()) {
            first = num1;
            last = num2;
        } else {
            first = num2;
            last = num1;
        }
        int len = last.length();
        ArrayList<String> addList = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            int convert = convert(last.charAt(i));
            if (convert == 0) {
                continue;
            }
            StringBuilder builder = single(first, convert);
            for (int j = 1; j < len - i; j++) {
                builder.append(0);
            }
            addList.add(builder.toString());
        }

        String answer = "0";
        String add;
        int gap;
        int result;
        int next;
        StringBuilder builder;
        for (int i = 0; i < addList.size(); i++) {
            next = 0;
            add = addList.get(i);
            gap = add.length() - answer.length();
            builder = new StringBuilder(answer);
            for (int j = 0; j < gap; j++) {
                builder.insert(0, "0");
            }
            answer = builder.toString();
            builder = new StringBuilder();
            for (int j = add.length() - 1; j >= 0; j--) {
                result = convert(add.charAt(j)) + convert(answer.charAt(j)) + next;
                next = result / 10;
                builder.insert(0, result % 10);
            }
            if (next != 0) {
                builder.insert(0, next);
            }
            answer = builder.toString();
        }


        return answer;
    }

    public StringBuilder single(String num1, int num2) {
        StringBuilder builder = new StringBuilder();
        int len = num1.length();
        int next = 0;
        for (int i = len - 1; i >= 0; i--) {
            int result = convert(num1.charAt(i)) * num2 + next;
            next = result / 10;
            builder.insert(0, result % 10);
        }
        if (next != 0) {
            builder.insert(0, next);
        }
        return builder;
    }

    public int convert(char c) {
        return c - 48;
    }
}
