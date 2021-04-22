package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * @author : wangqingsong
 * @since : 2021-02-19 10:02:22
 */
public class BSTIterator {

    TreeNode root;

    //-----------解法②--------------

    public BSTIterator(TreeNode root) {
        this.root = root;
    }

    public int next() {
        int value;
        TreeNode node = root;
        while (node.left != null) {
            if (node.left.left == null) {
                value = node.left.val;
                node.left = node.left.right;
                return value;
            }
            node = node.left;
        }
        value = root.val;
        root = root.right;
        return value;
    }

    public boolean hasNext() {
        if (root == null) {
            return false;
        }
        return true;
    }

    //-----------解法①--------------

    /*List<Integer> list;
    int index;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        index = 0;
        LDR(root);
    }

    private void LDR(TreeNode node) {
        if (node == null) {
            return;
        }
        LDR(node.left);
        list.add(node.val);
        LDR(node.right);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        if (index < list.size()) {
            return true;
        }
        return false;
    }*/
}
