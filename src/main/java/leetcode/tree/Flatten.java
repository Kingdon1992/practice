package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *  
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-18 16:19:11
 */
public class Flatten {

    public static void main(String[] args) {
        Flatten f = new Flatten();
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(5);
        root.left = a;
        root.right = b;
        a.left = new TreeNode(3);
        a.right = new TreeNode(4);
        b.right = new TreeNode(6);
        f.flatten(root);
        System.out.println();
    }

    public void flattenA(TreeNode root) {
        TreeNode right;
        TreeNode left;
        while (root != null) {
            if (root.left != null) {
                right = root.right;
                left = root.left;
                root.right = root.left;
                root.left = null;
                while (left.right != null) {
                    left = left.right;
                }
                left.right = right;
            }
            root = root.right;
        }
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root);
    }

    public TreeNode process(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node;
        }
        if (node.left != null) {
            TreeNode left = process(node.left);
            left.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        return process(node.right);
    }
}
