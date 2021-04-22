package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangqingsong
 * @since : 2021-02-24 16:03:08
 */
public class GrayCode {
    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        System.out.println(grayCode.grayCodeB(3));
    }
    public List<Integer> grayCode(int n) {
        List<Integer> list=new ArrayList<>(1<<n);
        list.add(0);
        if(n==0){
            return list;
        }
        List<Integer> subList=grayCode(n-1);
        int subLength = subList.size();
        int data=1<<n-1;
        list.addAll(subList);
        for (int i = 0; i< subLength; i++){
            int index= subLength +i;
            int e=subList.get(subLength-1-i)+data;
            list.add(index,e);
        }
        return list;
    }

    public List<Integer> grayCodeB(int n) {
        List<Integer> list=new ArrayList<>(1<<n);
        list.add(0);
        if(n==0){
            return list;
        }
        for (int i=1;i<=n;i++){
            int x=1<<i-1;
            int y=x-1;
            int hi=1<<i-1;
            while(y>=0){
                list.add(x++,list.get(y--)+hi);
            }
        }
        return list;
    }
}
