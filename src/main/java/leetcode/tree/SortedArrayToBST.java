package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * @author : wangqingsong
 * @since : 2021-02-19 11:51:36
 */
public class SortedArrayToBST {
    TreeNode root;
    int[] array;

    public TreeNode sortedArrayToBST(int[] nums) {
        array = nums;
        return sortedArrayToBST(0, array.length - 1);
    }

    public TreeNode sortedArrayToBST(int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        if (lo == hi) {
            return new TreeNode(array[lo]);
        }
        int mid = (lo + hi) / 2;
        TreeNode node = new TreeNode(array[mid]);
        node.left = sortedArrayToBST(lo, mid - 1);
        node.right = sortedArrayToBST(mid + 1, hi);
        return node;
    }

    public TreeNode sortedArrayToBSTA(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            root = put(root, nums[i]);
        }
        return root;
    }

    private TreeNode put(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        int compare = value - node.val;
        if (compare < 0) {
            node.left = put(node.left, value);
        } else if (compare > 0) {
            node.right = put(node.right, value);
        }
        return balance(node);
    }

    private TreeNode balance(TreeNode node) {
        int BF = degree(node.left) - degree(node.right);
        if (BF < -1) {
            if (node.right.right == null) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else if (BF > 1) {
            if (node.left.left == null) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }

    private TreeNode rotateLeft(TreeNode node) {
        TreeNode right = node.right;
        node.right = right.left;
        right.left = node;
        return right;
    }

    private TreeNode rotateRight(TreeNode node) {
        TreeNode left = node.left;
        node.left = left.right;
        left.right = node;
        return left;
    }

    private int degree(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(degree(node.left), degree(node.right)) + 1;
    }
}
