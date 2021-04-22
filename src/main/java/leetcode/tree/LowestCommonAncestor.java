package leetcode.tree;

import leetcode.tree.model.TreeNode;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-20 18:19:04
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {
        LowestCommonAncestor l = new LowestCommonAncestor();
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t8 = new TreeNode(8);
        TreeNode t4 = new TreeNode(4);
        t6.left = t2;
        t6.right = t8;
        t2.left = new TreeNode(0);
        t2.right = t4;
        t4.left = new TreeNode(3);
        t4.right = new TreeNode(5);
        t8.left = new TreeNode(7);
        t8.right = new TreeNode(9);

        TreeNode node = l.lowestCommonAncestor(t6, new TreeNode(2), new TreeNode(4));
        System.out.println(node.val);
    }

    TreeNode target;
    int min;
    int max;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        target = null;
        min = Math.min(p.val, q.val);
        max = Math.max(p.val, q.val);
        LRD(root);
        return target;
    }

    boolean LRD(TreeNode node) {
        if (node == null) {
            return false;
        }
        if (node.val < min) {
            return LRD(node.right);
        } else if (node.val > max) {
            return LRD(node.left);
        } else {
            boolean left = LRD(node.left);
            boolean right = LRD(node.right);
            boolean flag = (node.val == min || node.val == max);
            if (
                    (left && right) ||
                            (left && flag) ||
                            (right && flag)
            ) {
                target = node;
                return false;
            }
            return left || right || flag;
        }
    }

   /* 解法一
    List<Integer> list;
    TreeNode target;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        list=new ArrayList<>();
        build(root,p.val);
        get(root,q.val);
        return target;
    }

    void build(TreeNode node,int value){
        while(node!=null){
            list.add(node.val);
            int compare=value-node.val;
            if(compare<0){
                node=node.left;
            }else if(compare>0){
                node=node.right;
            }else{
                return;
            }
        }
    }

    void get(TreeNode node,int value){
        while(node!=null){
            if(list.contains(node.val)){
                target=node;
            }
            int compare=value-node.val;
            if(compare<0){
                node=node.left;
            }else if(compare>0){
                node=node.right;
            }else{
                return;
            }
        }
    }*/
}
