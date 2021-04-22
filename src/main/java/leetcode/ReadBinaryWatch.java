package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangqingsong
 * @since : 2021-03-01 12:02:13
 */
public class ReadBinaryWatch {
    public static void main(String[] args) {
        ReadBinaryWatch readBinaryWatch = new ReadBinaryWatch();
        System.out.println(readBinaryWatch.readBinaryWatch(1));

    }

    public List<String> readBinaryWatch(int num) {
        List<String> result=new ArrayList<>();
        dfs(num,0,0,result);
        return result;
    }

    void dfs(int num,int index,int count,List<String> result){
        if(num==0){
            result.add(process(count));
            return;
        }
        if(index==10){
            return;
        }
        for(int i=index;i<10;i++){
            count|=1<<i;
            if((count&63)<60&&(count>>6)<12){
                dfs(num-1,i+1,count,result);
            }
            count&=Integer.MAX_VALUE-(1<<i);
        }
    }

    String process(int count){
        int minute=count&63;
        int hour=count>>6;
        StringBuilder sb = new StringBuilder();
        sb.append(hour).append(":");
        if(minute<10){
            sb.append("0");
        }
        sb.append(minute);
        return sb.toString();
    }
}
