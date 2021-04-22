package leetcode;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @author : wangqingsong
 * @since : 2020-12-23 17:13:03
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(isPalindrome(Integer.MAX_VALUE));
    }

    /**
     * 暴力算法
     */
    public static boolean isPalindrome(int x) {
        if (x<0){
            return false;
        }
        int oldX=x;
        int newX=0;
        while(x>0){
            int lastValue=x%10;
            newX=newX*10+lastValue;
            x=x/10;
        }
        return newX==oldX;
    }
}
