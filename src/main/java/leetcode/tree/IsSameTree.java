package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * @author : wangqingsong
 * @since : 2021-02-09 15:49:20
 */
public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else {
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

}
