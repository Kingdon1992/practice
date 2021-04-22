package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-10 11:47:59
 */
public class BuildTreeV2 {
    public static void main(String[] args) {
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        BuildTreeV2 buildTree = new BuildTreeV2();
        TreeNode treeNode = buildTree.buildTree(inorder, postorder);
        System.out.println();
    }

    int index;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        index = inorder.length - 1;
        return buildTree(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int nodeValue = postorder[index--];
        TreeNode treeNode = new TreeNode(nodeValue);
        int inIndex = indexOf(inorder, nodeValue);
        treeNode.right = buildTree(inorder, postorder, inIndex + 1, hi);
        treeNode.left = buildTree(inorder, postorder, lo, inIndex - 1);
        return treeNode;
    }

    private int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
