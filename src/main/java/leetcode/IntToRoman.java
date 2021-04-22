package leetcode;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 来源：力扣（LeetCode）
 *
 * @author : wangqingsong
 * @since : 2020-12-23 17:27:41
 */
public class IntToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(49));
    }

    public static String intToRoman(int num) {
        StringBuilder builder=new StringBuilder();
        int thousand=num/1000;
        num=num-thousand*1000;
        int hundred=num/100;
        num=num-hundred*100;
        int ten=num/10;
        int single=num-ten*10;
        for (int i=0;i<thousand;i++){
            builder.append("M");
        }
        process(builder, hundred,"C","D","M");
        process(builder, ten,"X","L","C");
        process(builder, single,"I","V","X");
        return builder.toString();
    }

    private static void process(StringBuilder builder, int data,String a,String b,String c) {
        int m = data / 5;
        int n = data % 5;
        if (m ==1){
            if(n==4){
                builder.append(a).append(c);
            }else{
                builder.append(b);
                for (int i=0;i<n;i++){
                    builder.append(a);
                }
            }
        }else{
            if(n==4){
                builder.append(a).append(b);
            }else{
                for (int i=0;i<n;i++){
                    builder.append(a);
                }
            }
        }
    }
}
