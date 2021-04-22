package leetcode;

/**
 * @author : wangqingsong
 * @since : 2020-12-22 17:49:04
 */
public class Reverse {

    public static void main(String[] args) {
        int reverse = reverse(-123);
        System.out.println(reverse);
    }

    public static int reverse(int x) {
        int data = 0;
        while (Math.abs(x) > 9) {
            if (x > 0) {
                if ((x % 10 + data) > Integer.MAX_VALUE / 10) {
                    return 0;
                }
            } else if (x < 0) {
                if ((x % 10 + data) < Integer.MIN_VALUE / 10) {
                    return 0;
                }
            }
            data = (x % 10 + data) * 10;
            x = x / 10;
        }
        data += x;
        if (x > 0) {
            if (data > Integer.MAX_VALUE - x) {
                return 0;
            }
        } else if (x < 0) {
            if (data < Integer.MIN_VALUE - x) {
                return 0;
            }
        }
        return data;
    }


    public static int first(int x) {
        int data = 0;
        while (Math.abs(x) > 9) {
            if (x > 0) {
                if ((x % 10 + data) > Integer.MAX_VALUE / 10) {
                    return 0;
                }
            } else if (x < 0) {
                if ((x % 10 + data) < Integer.MIN_VALUE / 10) {
                    return 0;
                }
            }
            data = (x % 10 + data) * 10;
            x = x / 10;
        }
        data += x;
        if (x > 0) {
            if (data > Integer.MAX_VALUE - x) {
                return 0;
            }
        } else if (x < 0) {
            if (data < Integer.MIN_VALUE - x) {
                return 0;
            }
        }
        return data;
    }
}
