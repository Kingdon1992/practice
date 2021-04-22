package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 层序遍历
 *
 * @author : wangqingsong
 * @since : 2021-02-10 10:23:27
 */
public class LevelOrder {

    ArrayList<List<Integer>> list;
    int max;

    public List<List<Integer>> levelOrder(TreeNode root) {
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
        data.add(node.val);
        levelOrder(node.left, levelIndex + 1);
        levelOrder(node.right, levelIndex + 1);
    }
}
