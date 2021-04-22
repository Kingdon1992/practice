package leetcode;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author : wangqingsong
 * @since : 2021-02-23 14:09:54
 */
public class Trap {
    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap.trapB(height));
    }

    public int trapB(int[] height) {
        int n = height.length;
        if (n <= 1) {
            return 0;
        }
        int i=0;
        int j=n-1;
        int leftMax=0;
        int rightMax=0;
        int h=0;
        int all=0;
        while(i<j){
            leftMax=Math.max(leftMax,height[i]);
            rightMax=Math.max(rightMax,height[j]);
            int width=j-i+1;
            if (leftMax>rightMax){
                j--;
                all+=(rightMax-h)*width;
                h=rightMax;
            }else{
                i++;
                all+=(leftMax-h)*width;
                h=leftMax;
            }
        }
        all+=height[i]-h;
        for (int x=0;x<n;x++){
            all-=height[x];
        }
        return all;
    }

    public int trap(int[] height) {
        int n = height.length;
        if (n <= 1) {
            return 0;
        }
        int all = 0;
        int part = 0;
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            part += height[i];
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
            all += max;
        }
        int allB = 0;
        int maxB = 0;
        for (int i = n - 1; i > maxIndex; i--) {
            if (height[i] > maxB) {
                maxB = height[i];
            }
            allB += maxB;
        }
        return all - part - (n - maxIndex - 1) * max + allB;
    }
}
