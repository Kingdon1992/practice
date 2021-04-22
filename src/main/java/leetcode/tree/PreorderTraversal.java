package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历
 *
 * @author : wangqingsong
 * @since : 2021-02-18 10:16:47
 */
public class PreorderTraversal {
    List<Integer> list;

    public static void main(String[] args) {
        PreorderTraversal p = new PreorderTraversal();
        TreeNode treeNode = new TreeNode(1);
        List<Integer> result = p.preorderTraversal(treeNode);
        System.out.println(result);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        list = new ArrayList<>();
        process(root);
        return list;
    }

    public void process(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
}
