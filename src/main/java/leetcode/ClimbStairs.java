package leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author : wangqingsong
 * @since : 2021-02-24 14:57:10
 */
public class ClimbStairs {
    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        System.out.println(climbStairs.climbStairs(6));
        System.out.println(climbStairs.climbStairsX(6));
    }

    public int climbStairs(int n) {
        int cur = 1;
        int last = 1;
        for (int i = 2; i <= n; i++) {
            int change = cur;
            cur = last + cur;
            last = change;
        }
        return cur;
    }

    /**
     * 变种，不能连续跳2个台阶
     */
    public int climbStairsX(int n) {
        int lastY = 0;
        int lastF = 1;
        int currY = 0;
        int currF = 1;
        for (int i = 2; i <= n; i++) {
            int changeY = currY;
            currY = lastF-lastY;

            int changeF = currF;
            currF = currF+lastF - lastY;

            lastY = changeY;
            lastF = changeF;
        }
        return currF;
    }
}
