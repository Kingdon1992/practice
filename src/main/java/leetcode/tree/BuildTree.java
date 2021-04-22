package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-10 10:56:35
 */
public class BuildTree {
    public static void main(String[] args) {
        int[] preorder = new int[]{3, 1, 2, 4};
        int[] inorder = new int[]{1, 2, 3, 4};
        BuildTree buildTree = new BuildTree();
        TreeNode treeNode = buildTree.buildTree(preorder, inorder);
        System.out.println();
    }

    int preIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        preIndex = 0;
        return buildTree(preorder, inorder, 0, preorder.length - 1);
    }

    TreeNode buildTree(int[] preorder, int[] inorder, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int nodeValue = preorder[preIndex++];
        TreeNode treeNode = new TreeNode(nodeValue);
        int inIndex = indexOf(inorder, nodeValue);
        treeNode.left = buildTree(preorder, inorder, lo, inIndex - 1);
        treeNode.right = buildTree(preorder, inorder, inIndex + 1, hi);
        return treeNode;
    }

    int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
