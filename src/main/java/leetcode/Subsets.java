package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * @author : wangqingsong
 * @since : 2021-02-25 09:50:03
 */
public class Subsets {
    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        List<List<Integer>> dfs = subsets.subsetsB(new int[]{1, 2, 3});
        System.out.println();

    }

    public List<List<Integer>> subsetsB(int[] nums) {
        List<List<Integer>> result= new ArrayList<>();
        List<Integer> now = new ArrayList<>();
        dfs(nums,0,now,result);
        return result;
    }

    public void dfs(int[] nums,int index,List<Integer> now,List<List<Integer>> result) {
        result.add(new ArrayList<>(now));
        for(int i=index;i<nums.length;i++){
            now.add(nums[i]);
            dfs(nums,i+1,now,result);
            now.remove(now.size()-1);
        }
    }

    //-----------解法一

    public List<List<Integer>> subsets(int[] nums) {
        int l=nums.length;
        List<List<Integer>> list=new ArrayList<>(1<<l);
        List<Integer> a=new ArrayList<>();
        List<Integer> b=new ArrayList<>();
        b.add(nums[0]);
        list.add(a);
        list.add(b);
        if(l==1){
            return list;
        }

        for(int i=1;i<l;i++){
            List<List<Integer>> copy=copy(list);
            for(List<Integer> part:copy){
                part.add(nums[i]);
            }
            list.addAll(copy);
        }
        return list;
    }

    public List<List<Integer>> copy(List<List<Integer>> list) {
        List<List<Integer>> aux=new ArrayList<>(list.size());
        for(List<Integer> outer:list){
            List<Integer> part=new ArrayList<>(outer.size());
            for(Integer inner:outer){
                part.add(inner);
            }
            aux.add(part);
        }
        return aux;
    }
}
