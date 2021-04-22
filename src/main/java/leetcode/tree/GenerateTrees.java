package leetcode.tree;

import leetcode.tree.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-09 14:39:04
 */
public class GenerateTrees {
    public static void main(String[] args) {
        GenerateTrees generateTrees = new GenerateTrees();
        List<TreeNode> treeNodeList = generateTrees.generateTrees(0);
        System.out.println();
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int lo, int hi) {
        List<TreeNode> list = new ArrayList<>();
        if (lo > hi) {
            list.add(null);
            return list;
        }
        for (int i = lo; i <= hi; i++) {
            List<TreeNode> left = generateTrees(lo, i - 1);
            List<TreeNode> right = generateTrees(i + 1, hi);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = l;
                    treeNode.right = r;
                    list.add(treeNode);
                }
            }
        }
        return list;
    }
}
