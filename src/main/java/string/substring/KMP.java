package string.substring;

/**
 * @author : wangqingsong
 * @since : 2020-10-13 18:06:36
 */
public class KMP {
    public int KMP(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();
        int i = 0;
        int j = 0;
        int[][] dfa = dfa(pattern);
        for (; i < m && j < n; i++) {
            j = dfa[text.charAt(i)][j];
        }
        if (j == n) {
            return i - n;
        } else {
            return -1;
        }
    }

    private int[][] dfa(String pattern) {
        int m = pattern.length();
        int r = 256;
        int[][] dfa = new int[r][m];
        dfa[pattern.charAt(0)][0] = 1;
        int x = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < r; j++) {
                dfa[j][i] = dfa[j][x];
            }
            dfa[pattern.charAt(i)][i] = i + 1;
            x = dfa[pattern.charAt(i)][x];
            /*
             *这一行代码应该如何理解呢？
             * 当text的第n位和pattern的第m位比较时，证明pattern[0,m-1]位和text[n-m,n-1]都匹配成功，此时如果
             * ①两者匹配，则匹配输入pattern[m],加上状态m，应该得到新状态m+1
             * 状态[m]+输入pattern[m]=状态m+1
             * dfa[pattern.charAt(m)][m]=m+1
             *
             * 所以整个重点讨论的都是当不匹配时，需要回滚的位置
             *
             * ②两者不匹配，则匹配需要变动
             * 比如原先匹配是
             * index:    0  1  2  3  4  " 5"  6  7
             * text:     A  B  A  B  A  "!C"  ?  ?
             * pattern:  A  B  A  B  A  " C"
             * 此时确定从0开始的匹配失败了，匹配失败的位置在5，可得以下信息
             * 此时状态为5，输入不为C导致失败，且text的前5个文本都为已知状态
             * 我们需要明确的问题是
             * 状态5+输入不为C=状态？
             * 失败会从1重新开始匹配,0位置的值被舍弃,已知文本变为4个,即BABA
             * index:    0  1  2  3  4  5  6  7  8
             * text:     X  B  A  B  A !C  ?  ?
             * pattern:     A  B  A  B  A  C
             * 那么BABA匹配pattern[0,3]的结果是多少？会发现结论之前我们讨论过结果，经过一波递归的反推，有以下结论
             * BAB A 匹配pattern[0,3]的结果=BAB匹配pattern[0,2]的结果+输入A
             * BA B 匹配pattern[0,2]的结果=BA匹配pattern[0,1]的结果+输入B
             * B A 匹配pattern[0,1]的结果=B匹配pattern[0,0]的结果+输入A
             * B匹配pattern[0]的结果  即状态0输入B的结果，而状态0+任何输入的结论我们早就已知，即状态0+输入pattern[0]=状态1，其余全部为0
             *
             *
             * 当匹配失败位置在0（即目前为状态0），text需要从第1位开始，没有任何基于之前匹配的信息，全部重新匹配
             * 结论：
             *   - 状态0的所有不匹配输入的回滚位置=0
             *   - 状态0匹配输入的位置=1
             * 综上，状态0+所有输入的新状态都为已知
             *
             * 当匹配失败位置在1（即目前为状态1），text需要从第1位开始，已知
             *   - ①text第0位等于pattern[0],但是text第0位已经不参与匹配，该信息无用
             * 结论：
             *   - 状态1的所有不匹配输入的回滚位置=状态0输入相同输入时的回滚位置
             *   - 状态1的匹配输入位置=2
             * 综上，状态1+所有输入的新状态都为已知
             *
             * 当匹配失败位置在2（即目前为状态2），text需要从第1位开始，已知
             *   - ①同上
             *   - ②已知text第1位等于pattern[1],现在拿text第1位匹配pattern[0]，即状态为0+输入text第1位（pattern的第0位，已知）
             *       因为状态0+任意输入的新状态我们都知道（除了匹配输入使得状态+1，其他全部归0），所以可以得到已知状态，假设为I（I<=1）
             * 结论：
             *   - 状态2的【所有不匹配输入的回滚位置】= 状态I(I<=1)的【所有不匹配输入的回滚位置】
             *   - 状态2的匹配输入位置=3
             *
             * 当匹配失败位置在3（即目前为状态3），text需要从第1位开始
             *   - ①同上
             *   - ②同上
             *   - 已知text第2位等于pattern[2],现在拿text第2位匹配状态I，即状态I+输入text第2位（pattern的第1位，已知）
             *     因为状态I+任意输入的新状态我们都已知（I<=1,状态0、状态1+任意输入的结论都已经，见之前推导），所以可以得到已知状态，假设为J（J<=2）
             * 结论：
             *   - 状态3的【所有不匹配输入的回滚位置】= 状态J(J<=2)的【所有不匹配输入的回滚位置】
             *   - 状态3的匹配输入位置=4
             *
             * 以此类推即可得，当pattern第i位（即状态i）与text第m为不匹配时，状态i不匹配回滚位置x，都等于第i-1位不匹配时的回滚位置+输入text[m-1](即pattern[i-1])
             * x[i]=dfa[pattern.charAt(i-1)][x[i-1]],带入i=i+1可得
             * x[i+1]=dfa[pattern.charAt(i)][x[i]]
             */
        }
        return dfa;
    }
}
