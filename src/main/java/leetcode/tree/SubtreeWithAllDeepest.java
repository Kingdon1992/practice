package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * @author : wangqingsong
 * @since : 2021-03-05 15:07:48
 */
public class SubtreeWithAllDeepest {
    public static void main(String[] args) {
        SubtreeWithAllDeepest s = new SubtreeWithAllDeepest();
        TreeNode N2 = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        TreeNode N1 = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        TreeNode N5 = new TreeNode(5, new TreeNode(6), N2);
        TreeNode root = new TreeNode(3, N5, N1);
        TreeNode treeNode = s.subtreeWithAllDeepest(root);
        System.out.println();
    }

    int maxDegree;
    TreeNode target;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        target = null;
        maxDegree = 0;
        LDR(root, 1);
        return target;
    }

    int LDR(TreeNode node, int degree) {
        if (node == null) {
            return degree - 1;
        }
        if (degree > maxDegree) {
            maxDegree = degree;
        }
        int left = LDR(node.left, degree + 1);
        int right = LDR(node.right, degree + 1);
        if (left == right && right == maxDegree) {
            target = node;
        }
        return Math.max(left, right);
    }

}
