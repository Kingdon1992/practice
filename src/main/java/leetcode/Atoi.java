package leetcode;

/**
 * @author : wangqingsong
 * @since : 2020-12-22 18:18:30
 */
public class Atoi {

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("200000000000000000"));
        System.out.println(atoi.myAtoi("-200000000000000000"));
        System.out.println(atoi.myAtoi("3147483647"));
        System.out.println(atoi.myAtoi("4147483647"));
        System.out.println(atoi.myAtoi("8147483647"));
        System.out.println(atoi.myAtoi("9147483647"));
        System.out.println(atoi.myAtoi("-3147483647"));
        System.out.println(atoi.myAtoi("-4147483647"));
        System.out.println(atoi.myAtoi("-8147483647"));
        System.out.println(atoi.myAtoi("-9147483647"));
    }

    public int myAtoi(String s) {
        char[] chs = s.toCharArray();
        int i = 0;
        while (i < chs.length && chs[i] == ' ') {
            i++;
        }
        if (i == chs.length) {
            return 0;
        }
        int positive = 1;
        if (chs[i] == '-') {
            i++;
            positive = -1;
        } else if (chs[i] == '+') {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (; i < chs.length; i++) {
            if (chs[i] >= '0' && chs[i] <= '9') {
                sb.append(chs[i]);
            } else {
                break;
            }
        }
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        if (sb.length() == 0) {
            return 0;
        }
        if (sb.length() > 10 || (sb.length() == 10 && sb.charAt(0) > 50)) {
            if (positive > 0) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
        int result = (sb.charAt(sb.length() - 1) - 48) * positive;
        int zero = 10;
        for (int x = sb.length() - 2; x >= 0; x--) {
            int c = sb.charAt(x) - 48;
            if (c > 0) {
                if (positive > 0) {
                    if (zero > Integer.MAX_VALUE / c || result > Integer.MAX_VALUE - zero * c) {
                        result = Integer.MAX_VALUE;
                        break;
                    }
                    result += zero * c;
                } else {
                    if (zero > Integer.MAX_VALUE / c || result < Integer.MIN_VALUE + zero * c) {
                        result = Integer.MIN_VALUE;
                        break;
                    }
                    result -= zero * c;
                }
            }
            zero *= 10;
        }
        return result;
    }
}
