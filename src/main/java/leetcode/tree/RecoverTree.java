package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.List;

/**
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-09 16:14:10
 */
public class RecoverTree {

    public static void main(String[] args) {
        RecoverTree recoverTree = new RecoverTree();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(2);
        a.left = b;
        b.right = c;
        recoverTree.recoverTree(a);
        System.out.println();
    }

    TreeNode firstNode;
    TreeNode secondNode;
    TreeNode lastNode;

    public void recoverTree(TreeNode root) {
        high(root);
        int mid = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = mid;
    }

    public void high(TreeNode node) {
        if (node == null) {
            return;
        }
        high(node.left);
        if (lastNode != null && node.val < lastNode.val) {
            if (firstNode == null) {
                firstNode = lastNode;
                secondNode = node;
            } else {
                secondNode = node;
            }
        }
        lastNode = node;
        high(node.right);
    }

    public void LDR(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        LDR(node.left, list);
        if (list.size() > 0) {
            if (node.val < list.get(list.size() - 1).val) {
                if (firstNode == null) {
                    firstNode = list.get(list.size() - 1);
                    secondNode = node;
                } else {
                    secondNode = node;
                }
            }
        }
        list.add(node);
        LDR(node.right, list);
    }
}
