package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * @author : wangqingsong
 * @since : 2021-02-08 18:23:52
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int right = maxDepth(root.right);
        int left = maxDepth(root.left);
        return (right > left ? right : left) + 1;
    }
}
