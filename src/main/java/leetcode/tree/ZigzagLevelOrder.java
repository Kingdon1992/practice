package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-10 10:47:29
 */
public class ZigzagLevelOrder {
    ArrayList<List<Integer>> list;
    int max;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        list = new ArrayList<>();
        max = -1;
        levelOrder(root, 0);
        return list;
    }

    void levelOrder(TreeNode node, int levelIndex) {
        if (node == null) {
            return;
        }
        List<Integer> data;
        if (levelIndex > max) {
            data = new ArrayList<>();
            list.add(data);
            max = levelIndex;
        } else {
            data = list.get(levelIndex);
        }
        if (levelIndex % 2 == 0) {
            data.add(node.val);
        } else {
            data.add(0, node.val);
        }
        levelOrder(node.left, levelIndex + 1);
        levelOrder(node.right, levelIndex + 1);
    }
}