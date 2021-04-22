package leetcode;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-10 09:51:41
 */
public class NumTrees {
    public static void main(String[] args) {
        NumTrees numTrees = new NumTrees();
        System.out.println(numTrees.numTrees(1));
        System.out.println(numTrees.numTrees(2));
        System.out.println(numTrees.numTrees(3));
        System.out.println(numTrees.numTrees(4));
        System.out.println(numTrees.numTrees(5));
        System.out.println(numTrees.numTrees(6));
        System.out.println(numTrees.numTrees(7));
        System.out.println(numTrees.numTrees(8));
    }

    int[] cacheArray;

    public int numTrees(int n) {
        cacheArray = new int[n];
        return process(n);
    }

    public int process(int n) {
        if (n <= 1) {
            return 1;
        }
        int cache = cacheArray[n - 1];
        if (cache > 0) {
            return cache;
        }
        int num = 0;
        int left;
        int right;
        for (int i = 1; i <= n; i++) {
            left = process(i - 1);
            right = process(n - i);
            num += left * right;
        }
        cacheArray[n - 1] = num;
        return num;
    }

}
