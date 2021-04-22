package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : wangqingsong
 * @since : 2021-03-04 10:09:31
 */
public class MaxEnvelopes {
    public static void main(String[] args) {
        MaxEnvelopes m = new MaxEnvelopes();
        int[][] envelopes={
                {2,3},{2,10},{5,4},{5,7},{6,1},{6,2}
        };
        System.out.println(m.maxEnvelopes(envelopes));
        Deque<Integer> stack=new LinkedList<>();
        stack.size();
    }

    int max;
    public int maxEnvelopes(int[][] envelopes) {
        max=0;
        Arrays.sort(envelopes,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1,int[] o2){
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        for(int i=0;i<envelopes.length;i++){
            dfs(envelopes,i,1);
        }
        return max;
    }

    void dfs(int[][] envelopes,int lastIndex,int count){
        max=Math.max(count,max);
        if(lastIndex==envelopes.length-1){
            return;
        }
        for(int i=lastIndex+1;i<envelopes.length;i++){
            int[] last=envelopes[lastIndex];
            int[] now=envelopes[i];
            if(now[0]<=last[0]||now[1]<=last[1]){
                continue;
            }
            dfs(envelopes,i,count+1);
        }
    }
}
