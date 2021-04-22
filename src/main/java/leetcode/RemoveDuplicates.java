package leetcode;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : wangqingsong
 * @since : 2021-03-02 15:39:45
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        RemoveDuplicates r = new RemoveDuplicates();
        int[] nums={1,1,2};
        r.removeDuplicates(nums);
        System.out.println();
    }
    public int removeDuplicates(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int index=1;
        int min=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]>min){
                nums[index]=nums[i];
                min=nums[i];
            }
        }
        return index-1;
    }
}
