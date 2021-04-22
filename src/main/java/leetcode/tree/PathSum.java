package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-18 15:25:59
 */
public class PathSum {

    public static void main(String[] args) {
        PathSum p = new PathSum();
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(-2);
        TreeNode b = new TreeNode(1);
        b.left = new TreeNode(-1);
        a.left = b;
        a.right = new TreeNode(3);
        root.left = a;
        TreeNode c = new TreeNode(-3);
        c.left = new TreeNode(-2);
        root.right = c;
        System.out.println(p.pathSum(root, 2));
    }

    List<List<Integer>> result;
    List<Integer> singleResult;
    int target;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        result = new ArrayList<>();
        singleResult = new ArrayList<>();
        process(root, 0);
        return result;
    }

    void process(TreeNode node, int nowSum) {
        if (node == null) {
            return;
        }
        singleResult.add(node.val);
        nowSum = nowSum + node.val;
        if (node.left == null && node.right == null && nowSum == target) {
            result.add(copy(singleResult));
        }
        process(node.left, nowSum);
        process(node.right, nowSum);
        singleResult.remove(singleResult.size() - 1);
    }

    List<Integer> copy(List<Integer> src) {
        List<Integer> list = new ArrayList<>(src.size());
        for (int i = 0; i < src.size(); i++) {
            list.add(src.get(i));
        }
        return list;
    }
}
