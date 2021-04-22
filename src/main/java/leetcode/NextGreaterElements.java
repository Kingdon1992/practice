package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangqingsong
 * @since : 2021-03-06 11:29:54
 */
public class NextGreaterElements {
    public static void main(String[] args) {
        NextGreaterElements n = new NextGreaterElements();
        int[] ints = n.nextGreaterElements(new int[]{1,1,1,1,1});
        System.out.println(Arrays.toString(ints));
    }
    public int[] nextGreaterElements(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int len=nums.length;
        int[] result=new int[len];
        int maxIndex=0;
        for(int i=0;i<len;i++){
            if(nums[i]>nums[maxIndex]){
                maxIndex=i;
            }
        }
        map.put(nums[maxIndex],null);
        result[maxIndex]=-1;
        for(int i=1;i<len;i++){
            int y=(maxIndex-i+len)%len;
            int now=nums[y];
            Integer compare=nums[(y+1)%len];
            while(compare!=null&&now>=compare){
                compare=map.get(compare);
            }
            result[y]=compare==null?-1:compare;
            map.put(nums[y],compare);
        }
        return result;
    }
}
