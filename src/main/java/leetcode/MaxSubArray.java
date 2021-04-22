package leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : wangqingsong
 * @since : 2021-03-02 11:13:37
 */
public class MaxSubArray {
    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int[] nums={-2,-1};
        System.out.println(maxSubArray.maxSubArray(nums));
    }
    public int maxSubArray(int[] nums) {
        int len=nums.length;
        int[] sums=new int[len];
        sums[0]=nums[0];
        for(int i=1;i<len;i++){
            sums[i]=sums[i-1]+nums[i];
        }
        int maxIndex=0;
        for(int i=0;i<len;i++){
            maxIndex=sums[i]>sums[maxIndex]?i:maxIndex;
        }
        int min=0;
        for(int i=0;i<maxIndex;i++){
            min=sums[i]<min?sums[i]:min;
        }
        return sums[maxIndex]-min;
    }
}
