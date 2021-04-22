package leetcode;

import java.util.Stack;

/**
 * 逆波兰表示
 * 四则运算
 * @author : wangqingsong
 * @since : 2020-10-22 11:00:33
 */
public class Calculate {

    public static void main(String[] args) {
        String calculation = "( 10 * ( 10 - 7 + 5 ) * 8 ) / 8";
        //String calculation = "9 + ( 3 - 1 ) * 3 + 10 / 2";
        String suffixExpression = suffixExpression(calculation);
        int result = calculate(suffixExpression);
        System.out.println(result);
    }

    private static int calculate(String suffixExpression) {
        String[] elements = suffixExpression.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < elements.length; i++) {
            String element = elements[i];
            if (!symbolCharacter(element)) {
                stack.push(element);
                continue;
            }
            int suffix = Integer.parseInt(stack.pop());
            int prefix = Integer.parseInt(stack.pop());
            stack.push(calculate(prefix, suffix, element) + "");
        }
        return Integer.parseInt(stack.pop());
    }

    private static int calculate(int prefix, int suffix, String element) {
        if (element.equals("+")) {
            return prefix + suffix;
        }
        if (element.equals("-")) {
            return prefix - suffix;
        }
        if (element.equals("/")) {
            return prefix / suffix;
        }
        if (element.equals("*")) {
            return prefix * suffix;
        }
        return 0;
    }

    private static String suffixExpression(String calculation) {
        StringBuilder builder = new StringBuilder();
        String[] elements = calculation.split(" ");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < elements.length; i++) {
            String element = elements[i];
            if (!symbolCharacter(element)) {
                //非符号类型，直接输出
                builder.append(element).append(" ");
                continue;
            }

            if (element.equals(")")) {
                //当前元素为【右括号】，栈一直弹出至【左括号】,此时不必担心栈为空，在元素为右括号的情况下，栈中一定有左括号
                String pop = stack.pop();
                while (!pop.equals("(")) {
                    builder.append(pop).append(" ");
                    pop = stack.pop();
                }
                continue;
            }

            if (element.equals("(")) {
                //当前元素为【左括号】，入栈即可
                stack.push(element);
                continue;
            }

            while (!stack.empty()) {
                String pop = stack.pop();

                if (pop.equals("(")) {
                    stack.push(pop);
                    break;
                }

                int compare = compare(element, pop);
                if (compare < 1) {
                    //当前元素不大于栈顶元素，弹出栈顶元素进行输出
                    builder.append(pop).append(" ");
                } else {
                    stack.push(pop);
                    break;
                }
            }
            stack.push(element);
        }

        //把栈中剩余符号全部弹出
        while (!stack.empty()) {
            builder.append(stack.pop()).append(" ");
        }
        return builder.toString().trim();
    }

    private static int compare(String a, String b) {
        if ("*/".contains(a) && "+-".contains(b)) {
            return 1;
        }
        if ("+-".contains(a) && "*/".contains(b)) {
            return -1;
        }
        return 0;
    }

    private static boolean symbolCharacter(String data) {
        return "+-*/()".contains(data);
    }
}
