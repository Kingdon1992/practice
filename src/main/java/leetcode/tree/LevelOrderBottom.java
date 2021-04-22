package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层序遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-10 14:44:33
 */
public class LevelOrderBottom {
    int max;
    List<List<Integer>> list;

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        max = -1;
        list = new ArrayList<>();
        levelOrderBottom(root, 0);
        return list;
    }

    void levelOrderBottom(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level > max) {
            ArrayList<Integer> data = new ArrayList<>();
            list.add(0, data);
            max = level;
        }
        list.get(max - level).add(node.val);
        levelOrderBottom(node.left, level + 1);
        levelOrderBottom(node.right, level + 1);
    }

}
