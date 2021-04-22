package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-19 14:48:55
 */
public class RightSideView {
    int max;
    List<Integer> list;

    public List<Integer> rightSideView(TreeNode root) {
        max = 0;
        list = new ArrayList<>();
        rightSideView(root, 0);
        return list;
    }

    void rightSideView(TreeNode node, int index) {
        if (node == null) {
            return;
        }
        if (index >= max) {
            list.add(node.val);
            max++;
        } else {
            list.set(index, node.val);
        }
        rightSideView(node.left, index + 1);
        rightSideView(node.right, index + 1);
    }
}
