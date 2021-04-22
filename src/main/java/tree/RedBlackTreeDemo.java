package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangqingsong
 * @since : 2020-09-16 11:39:38
 */
public class RedBlackTreeDemo<K extends Comparable<K>, V> {
    private static final String RED = "red";
    private static final String BLACK = "black";
    private Node<K, V> root;
    private Integer degree;

    static class Node<K, V> {
        private Node(K key, V value, String color, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parentNode = parent;
            this.n = 1;
        }

        /**
         * 键
         */
        K key;
        /**
         * 值
         */
        V value;
        /**
         * 指向该节点的连线颜色
         */
        String color;
        /**
         * 左子节点
         */
        Node<K, V> leftNode;
        /**
         * 右子节点
         */
        Node<K, V> rightNode;
        /**
         * 父节点
         */
        Node<K, V> parentNode;
        /**
         * 以该节点为根节点的子树 的节点总数
         */
        int n;

    }

    public int size() {
        return size(root);
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.n;
    }

    public void put(K key, V value) {
        root = put(root, key, value, null);
        root.color = BLACK;
    }

    private Node<K, V> put(Node<K, V> node, K key, V value, Node<K, V> parent) {
        if (node == null) {
            return new Node<>(key, value, RED, parent);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.leftNode = put(node.leftNode, key, value, node);
        } else if (compare > 0) {
            node.rightNode = put(node.rightNode, key, value, node);
        } else {
            node.value = value;
        }

        return balance(node);
    }

    private Node<K, V> balance(Node<K, V> node) {
        if (isRed(node.rightNode)) {
            node = rotateLeft(node);
        }
        //左连续两红，右旋
        if (isRed(node.leftNode) && isRed(node.leftNode.leftNode)) {
            node = rotateRight(node);
        }
        //右红左红，变色
        if (isRed(node.leftNode) && isRed(node.rightNode)) {
            flipColorsToBRB(node);
        }
        node.n = size(node.leftNode) + size(node.rightNode) + 1;
        return node;
    }

    private boolean isRed(Node<K, V> node) {
        if (node == null) {
            return false;
        }
        return RED.equals(node.color);
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> r = node.rightNode;
        //扭转
        node.rightNode = r.leftNode;
        r.leftNode = node;
        //变色
        r.color = node.color;
        node.color = RED;
        //设置父节点
        if (node.rightNode != null) {
            node.rightNode.parentNode = node;
        }
        r.parentNode = node.parentNode;
        node.parentNode = r;
        //设置子树节点数
        r.n = node.n;
        node.n = size(node.leftNode) + size(node.rightNode) + 1;
        return r;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> l = node.leftNode;
        //扭转
        node.leftNode = l.rightNode;
        l.rightNode = node;
        //变色
        l.color = node.color;
        node.color = RED;
        //设置父节点
        if (node.leftNode != null) {
            node.leftNode.parentNode = node;
        }
        l.parentNode = node.parentNode;
        node.parentNode = l;
        //设置子树节点数
        l.n = node.n;
        node.n = size(node.leftNode) + size(node.rightNode) + 1;
        return l;
    }

    private void flipColorsToBRB(Node<K, V> node) {
        node.color = RED;
        node.leftNode.color = BLACK;
        node.rightNode.color = BLACK;
    }

    private void flipColorsToRBR(Node<K, V> node) {
        node.color = BLACK;
        node.leftNode.color = RED;
        node.rightNode.color = RED;
    }

    public List<K> LDR() throws Exception {
        List<K> keyList = new ArrayList<>(root.n);
        degree = null;
        LDR(root, keyList);
        return keyList;
    }

    private void LDR(Node<K, V> node, List<K> keyList) throws Exception {
        if (node == null) {
            return;
        }
        LDR(node.leftNode, keyList);
        if (size(node) <= 2) {
            check(node);
        }
        keyList.add(node.key);
        LDR(node.rightNode, keyList);
    }

    private void check(Node<K, V> node) throws Exception {
        int count = 0;
        boolean lastNodeIsRed = false;
        while (node != null) {
            if (isRed(node)) {
                if (lastNodeIsRed) {
                    throw new Exception("连续2个红色节点!");
                }
                lastNodeIsRed = true;
            } else {
                count++;
                lastNodeIsRed = false;
            }
            node = node.parentNode;
        }

        if (degree == null) {
            degree = count;
        } else {
            if (!degree.equals(count)) {
                throw new Exception("叶子节点到根节点所经历的黑色节点数不同!");
            }
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
        int compare;
        while (node != null) {
            compare = key.compareTo(node.key);
            if (compare < 0) {
                node = node.leftNode;
            } else if (compare > 0) {
                node = node.rightNode;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            if (node.leftNode == null) {
                return node;
            }
            if (!isRed(node.leftNode) && !isRed(node.leftNode.leftNode)) {
                node = processLeftNodeOfTwo(node);
            }
            node.leftNode = delete(node.leftNode, key);
        } else {
            /*
             * "<"和"="进行合并的原因
             * ①当找到目标节点X时，首先从其右子树中找到最小节点，替换被删除节点，之后要删除右子树的最小节点
             * ②如果此时直接调用deleteMin(Node<K,V> node)方法，此时【向下寻找的变换】只进行到了【X节点的父节点】，然后就跳到了【X节点的右子节点】
             * ③【X节点】本身并没有参与【向下寻找的变换】
             * ④所以在找到X节点时，因为明确下一个目标节点（右子树的最小值）一定在X节点的右子树中，所以先做一波compare>0的变换，然后替换X节点、删除X节点右子树的最小节点
             */
            if (isRed(node.leftNode)) {
                node = rotateRight(node);
            }
            /*
             * 为什么不先判空再做其他操作？
             * ①扭转后，右子节点一定不为空（因为当前变成了右子节点）
             * ②在上一步的保证下，node节点的左子节点，一定不是红色节点，在"左偏红黑树"定义保证下，可以得出结论——右子节点为空时，左子节点一定为空
             * ③此时的node节点一定是最底部的节点
             *   - 如果是目标节点，直接删除即可
             *   - 如果不是目标节点，说明找不到要删除的目标，返回即可
             */
            if (node.rightNode == null) {
                if (key.compareTo(node.key) == 0) {
                    return null;
                } else {
                    return node;
                }
            }
            if (!isRed(node.rightNode) && !isRed(node.rightNode.leftNode)) {
                node = processRightNodeOfTwo(node);
            }
            if (key.compareTo(node.key) == 0) {
                Node<K, V> min = min(node.rightNode);
                node.rightNode = deleteMin(node.rightNode);
                node.key = min.key;
                node.value = min.value;
                node.n--;
            } else {
                node.rightNode = delete(node.rightNode, key);
            }
        }
        return balance(node);
    }

    private Node<K, V> max(Node<K, V> node) {
        while (node.rightNode != null) {
            node = node.rightNode;
        }
        return node;
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (isRed(node.leftNode)) {
            node = rotateRight(node);
        }
        if (node.rightNode == null) {
            return null;
        }
        if (!isRed(node.rightNode) && !isRed(node.rightNode.leftNode)) {
            //右子节点为2-节点时
            node = processRightNodeOfTwo(node);
        }
        node.rightNode = deleteMax(node.rightNode);
        return balance(node);
    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.leftNode != null) {
            node = node.leftNode;
        }
        return node;
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.leftNode == null) {
            return null;
        }
        if (!isRed(node.leftNode) && !isRed(node.leftNode.leftNode)) {
            //处理左子节点为2节点的情况
            node = processLeftNodeOfTwo(node);
        }
        node.leftNode = deleteMin(node.leftNode);
        return balance(node);
    }

    private Node<K, V> processLeftNodeOfTwo(Node<K, V> node) {
        flipColorsToRBR(node);
        if (isRed(node.rightNode.leftNode)) {
            node.rightNode = rotateRight(node.rightNode);
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> processRightNodeOfTwo(Node<K, V> node) {
        flipColorsToRBR(node);
        return node;
    }
}


