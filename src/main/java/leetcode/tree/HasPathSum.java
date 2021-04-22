package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-10 15:26:46
 */
public class HasPathSum {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(-2);
        TreeNode b = new TreeNode(-3);
        a.right = b;
        HasPathSum hasPathSum = new HasPathSum();
        System.out.println(hasPathSum.hasPathSum(a, -5));
    }

    boolean flag;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        flag = false;
        process(root, 0, targetSum);
        return flag;
    }

    public void process(TreeNode node, int now, int targetSum) {
        if (flag || node == null) {
            return;
        }
        now = now + node.val;
        if (now == targetSum && node.left == null && node.right == null) {
            flag = true;
            return;
        }
        process(node.left, now, targetSum);
        process(node.right, now, targetSum);
    }
}
