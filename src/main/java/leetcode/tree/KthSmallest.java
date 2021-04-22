package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *  
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-20 18:01:32
 */
public class KthSmallest {
    int count;
    Integer value;
    int k;

    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        this.k = k;
        kthSmallest(root);
        return value;
    }

    public void kthSmallest(TreeNode node) {
        if (node == null || value != null) {
            return;
        }
        kthSmallest(node.left);
        count++;
        if (count == k) {
            value = node.val;
        }
        kthSmallest(node.right);
    }
}
