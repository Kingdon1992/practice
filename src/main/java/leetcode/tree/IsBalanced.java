package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * @author : wangqingsong
 * @since : 2021-02-19 14:40:32
 */
public class IsBalanced {
    boolean flag;

    public boolean isBalanced(TreeNode root) {
        flag = true;
        process(root);
        return flag;
    }

    int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = process(node.left);
        int right = process(node.right);
        if (Math.abs(left - right) > 1) {
            flag = false;
        }
        return Math.max(left, right) + 1;
    }
}
