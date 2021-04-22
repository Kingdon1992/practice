package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-09 15:16:23
 */
public class IsValidBST {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(6);
        a.left = b;
        a.right = c;
        c.left = d;
        c.right = e;
        IsValidBST isValidBST = new IsValidBST();
        boolean validBST = isValidBST.isValidBST(a);
        System.out.println(validBST);
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return LDR(root, list);
    }

    public boolean LDR(TreeNode node, List<Integer> list) {
        if (node == null) {
            return true;
        }
        boolean left = LDR(node.left, list);
        if (list.size() > 0 && node.val <= list.get(list.size() - 1)) {
            return false;
        }
        list.add(node.val);
        boolean right = LDR(node.right, list);
        return left && right;
    }
}
