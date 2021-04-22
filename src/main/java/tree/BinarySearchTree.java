package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangqingsong
 * @since : 2020-09-16 11:39:38
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }

        /**
         * 键
         */
        private Key key;
        /**
         * 值
         */
        private Value value;
        /**
         * 左子节点
         */
        private Node leftNode;
        /**
         * 右子节点
         */
        private Node rightNode;
        /**
         * 以该节点为根节点的子树 的节点总数
         */
        private int N;
    }

    /**
     * 获取树的节点数量
     *
     * @return 树的节点数量
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return get(node.leftNode, key);
        } else if (compare > 0) {
            return get(node.rightNode, key);
        } else {
            return node.value;
        }
    }

    /**
     * 插入键值对
     *
     * @param key   键
     * @param value 值
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.leftNode = put(node.leftNode, key, value);
        } else if (compare > 0) {
            node.rightNode = put(node.rightNode, key, value);
        } else {
            node.value = value;
        }
        node.N = size(node.leftNode) + size(node.rightNode) + 1;
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.leftNode = delete(node.leftNode, key);
        } else if (compare > 0) {
            node.rightNode = delete(node.rightNode, key);
        } else {
            if (node.leftNode == null) {
                return node.rightNode;
            }
            if (node.rightNode == null) {
                return node.leftNode;
            }
            Node x = node;
            node = min(x.rightNode);
            node.rightNode = deleteMin(x.rightNode);
            node.leftNode = x.leftNode;
        }
        node.N = size(node.leftNode) + size(node.rightNode) + 1;
        return node;
    }

    private Node min(Node node) {
        if (node.leftNode == null) {
            return node;
        }
        return min(node.leftNode);
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.leftNode != null) {
            node.leftNode = deleteMin(node.leftNode);
            node.N = 1 + size(node.leftNode) + size(node.rightNode);
            return node;
        }
        return node.rightNode;
    }

    /**
     * 前序遍历
     * @return 返回所有key
     */
    public List<Key> DLR() {
        List<Key> KeyList = new ArrayList<>(root.N);
        DLR(root, KeyList);
        return KeyList;
    }

    private void DLR(Node node, List<Key> keyList) {
        if (node == null) {
            return;
        }
        keyList.add(node.key);
        DLR(node.leftNode, keyList);
        DLR(node.rightNode, keyList);
    }

    /**
     * 中序遍历
     * @return 返回所有key
     */
    public List<Key> LDR() {
        List<Key> KeyList = new ArrayList<>(root.N);
        LDR(root, KeyList);
        return KeyList;
    }

    private void LDR(Node node, List<Key> keyList) {
        if (node == null) {
            return;
        }
        LDR(node.leftNode, keyList);
        keyList.add(node.key);
        LDR(node.rightNode, keyList);
    }
}
