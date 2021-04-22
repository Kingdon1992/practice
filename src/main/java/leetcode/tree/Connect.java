package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : wangqingsong
 * @since : 2021-02-18 17:54:32
 */
public class Connect {


    public Node connectA(Node root) {
        Node nextRowFirstNode = root;
        Node nextRowNode;
        Node rowNode;

        while (nextRowFirstNode != null) {
            rowNode = nextRowFirstNode;
            nextRowFirstNode = null;
            nextRowNode = null;
            while (rowNode != null) {
                if (rowNode.left != null) {
                    if (nextRowFirstNode == null) {
                        nextRowFirstNode = rowNode.left;
                        nextRowNode = rowNode.left;
                    } else {
                        nextRowNode.next = rowNode.left;
                        nextRowNode = nextRowNode.next;
                    }
                }
                if (rowNode.right != null) {
                    if (nextRowFirstNode == null) {
                        nextRowFirstNode = rowNode.right;
                        nextRowNode = rowNode.right;
                    } else {
                        nextRowNode.next = rowNode.right;
                        nextRowNode = nextRowNode.next;
                    }
                }
                rowNode = rowNode.next;
            }
        }
        return root;
    }

    List<Node> list;
    int max;

    public Node connect(Node root) {
        max = 0;
        list = new ArrayList<>();
        process(root, 0);
        return root;
    }

    public void process(Node node, int levelIndex) {
        if (node == null) {
            return;
        }
        if (levelIndex >= max) {
            list.add(node);
            max++;
        } else {
            list.get(levelIndex).next = node;
            list.set(levelIndex, node);
        }
        process(node.left, levelIndex + 1);
        process(node.right, levelIndex + 1);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
