package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-02-08 15:06:44
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2, 4};
        FindMedianSortedArrays f = new FindMedianSortedArrays();
        System.out.println(f.findMedianSortedArrays(nums1, nums2));

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int i = 0;
        int j = 0;
        boolean single = (n + m) % 2 == 1;
        if (single) {
            //奇数个元素
            double target = 0.0;
            int targetIndex = (n + m) / 2;
            while (i + j <= targetIndex) {
                if (i > n - 1) {
                    target = nums2[j++];
                } else if (j > m - 1) {
                    target = nums1[i++];
                } else if (nums1[i] > nums2[j]) {
                    target = nums2[j++];
                } else {
                    target = nums1[i++];
                }
            }
            return target;
        } else {
            //偶数个元素
            int targetIndex = (n + m) / 2;
            double lastValue = 0.0;
            double value = 0.0;
            while (i + j <= targetIndex) {
                lastValue = value;
                if (i > n - 1) {
                    value = nums2[j++];
                } else if (j > m - 1) {
                    value = nums1[i++];
                } else if (nums1[i] > nums2[j]) {
                    value = nums2[j++];
                } else {
                    value = nums1[i++];
                }
            }
            return (value+lastValue)/2;
        }
    }
}
