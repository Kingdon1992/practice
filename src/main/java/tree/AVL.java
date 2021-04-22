package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangqingsong
 * @since : 2021-02-19 10:37:21
 */
public class AVL<K extends Comparable<K>, V> {

    Node<K, V> root;

    static class Node<K, V> {
        Node<K, V> left;
        Node<K, V> right;
        K key;
        V value;
        int n;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.n = 1;
        }
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.n;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value);
        } else if (compare > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return balance(node);
    }

    private Node<K, V> balance(Node<K, V> node) {
        int BF = BF(node);
        if (BF < -1) {
            if (BF(node.right)==1) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else if (BF > 1) {
            if (BF(node.left)==-1) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> right = node.right;
        node.right = right.left;
        right.left = node;

        right.n = node.n;
        node.n = size(node.left) + size(node.right) + 1;

        return right;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> left = node.left;
        node.left = left.right;
        left.right = node;

        left.n = node.n;
        node.n = size(node.left) + size(node.right) + 1;

        return left;

    }


    private int BF(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return degree(node.left) - degree(node.right);
    }

    private int degree(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(degree(node.left), degree(node.right)) + 1;
    }

    public List<K> LDR() throws Exception {
        List<K> list = new ArrayList<>(size(root));
        LDR(root, list);
        return list;
    }

    private void LDR(Node<K, V> node, List<K> list) throws Exception {
        if (node == null) {
            return;
        }
        LDR(node.left, list);
        list.add(node.key);
        LDR(node.right, list);
    }

    public static void main(String[] args) throws Exception {
        AVL<Integer, String> avl = new AVL<>();
        avl.put(3,null);
        avl.put(2,null);
        avl.put(1,null);
        avl.put(4,null);
        avl.put(5,null);
        avl.put(6,null);
        avl.put(7,null);
        avl.put(10,null);
        avl.put(9,null);
        avl.put(8,null);
        System.out.println(avl.LDR());
    }
}
