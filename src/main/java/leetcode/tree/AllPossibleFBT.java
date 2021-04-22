package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 * <p>
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 * <p>
 * 答案中每个树的每个结点都必须有 node.val=0。
 * <p>
 * 你可以按任何顺序返回树的最终列表。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-possible-full-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-24 14:13:36
 */
public class AllPossibleFBT {
    public static void main(String[] args) {
        AllPossibleFBT allPossibleFBT = new AllPossibleFBT();
        List<TreeNode> treeNodes = allPossibleFBT.allPossibleFBT(5);
        System.out.println();
    }

    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> list = new ArrayList<>();
        if (N % 2 == 0) {
            return list;
        }
        if (N == 1) {
            list.add(new TreeNode(0));
            return list;
        }
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i - 1);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode node = new TreeNode(0);
                    node.left = l;
                    node.right = l;
                    list.add(node);
                }
            }
        }
        return list;
    }
}
