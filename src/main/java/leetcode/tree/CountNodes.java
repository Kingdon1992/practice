package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-19 15:00:48
 */
public class CountNodes {

    public static void main(String[] args) {
        CountNodes countNodes = new CountNodes();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = new TreeNode(4);
        b.right = new TreeNode(5);
        c.left = new TreeNode(6);
        System.out.println(countNodes.countNodes(a));
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = level(root);
        int lo = 1 << level - 1;
        int hi = (1 << level) - 1;
        int mid;
        while (lo < hi) {
            mid = (lo + hi) / 2;
            boolean flag = get(root, mid, level - 2);
            if (flag) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (get(root, lo, level - 2)) {
            return lo;
        }
        return lo - 1;
    }

    boolean get(TreeNode node, int value, int p) {
        if (p < 0) {
            if (node == null) {
                return false;
            }
            return true;
        }
        int tag = (value >> p) & 1;
        if (tag == 0) {
            return get(node.left, value, p - 1);
        } else {
            return get(node.right, value, p - 1);
        }
    }

    int level(TreeNode node) {
        int level = 0;
        while (node != null) {
            level++;
            node = node.left;
        }
        return level;
    }

    /*int count;
    public int countNodes(TreeNode root) {
        count=0;
        process(root);
        return count;
    }

    void process(TreeNode node){
        if (node==null){
            return;
        }
        count++;
        process(node.left);
        process(node.right);
    }*/
}
