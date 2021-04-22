package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-18 14:38:13
 */
public class MaxPathSum {

    public static void main(String[] args) {
        MaxPathSum m = new MaxPathSum();
        TreeNode treeNode = new TreeNode(-3);
        System.out.println(m.maxPathSum(treeNode));
    }

    int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = root.val;
        process(root);
        return maxSum;
    }

    private int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = process(node.left);
        int right = process(node.right);
        int nodeMaxSum = node.val;
        if (left > 0) {
            nodeMaxSum += left;
        }
        if (right > 0) {
            nodeMaxSum += right;
        }
        maxSum = Math.max(nodeMaxSum, maxSum);
        if (left < 0 && right < 0) {
            return node.val;
        }
        return left > right ? left + node.val : right + node.val;
    }
}
