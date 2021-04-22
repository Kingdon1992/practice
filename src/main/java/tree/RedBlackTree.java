package tree;

import java.util.*;

public class RedBlackTree<K extends Comparable<K>, V> {
    private static final String RED = "red";
    private static final String BLACK = "black";
    private Integer degree;
    private Node<K, V> root;

    static class Node<K, V> {
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        K key;
        V value;
        String color;
        int n;

        Node(K key, V value, String color, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
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
        root = put(root, key, value, null);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value, Node<K, V> parent) {
        if (node == null) {
            return new Node<>(key, value, RED, parent);
        }
        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = put(node.left, key, value, node);
        } else if (compare > 0) {
            node.right = put(node.right, key, value, node);
        } else {
            node.value = value;
        }
        return balance(node);
    }

    private Node<K, V> balance(Node<K, V> node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColorToBRB(node);
        }
        node.n = size(node.left) + size(node.right) + 1;
        return node;
    }

    private void flipColorToBRB(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private void flipColorToRBR(Node<K, V> node) {
        node.color = BLACK;
        node.left.color = RED;
        node.right.color = RED;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> right = node.right;
        node.right = right.left;
        right.left = node;

        if (node.right != null) {
            node.right.parent = node;
        }
        right.parent = node.parent;
        node.parent = right;

        right.color = node.color;
        node.color = RED;

        right.n = node.n;
        node.n = size(node.left) + size(node.right) + 1;

        return right;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> left = node.left;
        node.left = left.right;
        left.right = node;

        if (node.left != null) {
            node.left.parent = node;
        }
        left.parent = node.parent;
        node.parent = left;

        left.color = node.color;
        node.color = RED;

        left.n = node.n;
        node.n = size(node.left) + size(node.right) + 1;

        return left;
    }

    private boolean isRed(Node<K, V> node) {
        if (node == null) {
            return false;
        }
        return RED.equals(node.color);
    }

    public List<K> LDR() throws Exception {
        List<K> list = new ArrayList<>(size(root));
        degree = null;
        LDR(root, list);
        return list;
    }

    private void LDR(Node<K, V> node, List<K> list) throws Exception {
        if (node == null) {
            return;
        }
        LDR(node.left, list);
        if (size(node) <= 2) {
            check(node);
        }
        list.add(node.key);
        LDR(node.right, list);
    }

    private void check(Node<K, V> node) throws Exception {
        int count = 0;
        boolean lastNodeIsRed = false;
        while (node != null) {
            if (isRed(node)) {
                if (lastNodeIsRed) {
                    throw new Exception("连续2个红色节点");
                }
                lastNodeIsRed = true;
            } else {
                count++;
                lastNodeIsRed = false;
            }
            node = node.parent;
        }

        if (degree == null) {
            degree = count;
        } else {
            if (!degree.equals(count)) {
                throw new Exception("从叶子节点到根节点所经历的黑色节点数不一致");
            }
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
        while (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = processLeftOfTwo(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    private Node<K, V> processLeftOfTwo(Node<K, V> node) {
        flipColorToRBR(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
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
            if (node.left == null) {
                return node;
            }
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = processLeftOfTwo(node);
            }
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (node.right == null) {
                if (key.compareTo(node.key) == 0) {
                    return null;
                }
                return node;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                flipColorToRBR(node);
            }
            if (key.compareTo(node.key) == 0) {
                Node<K, V> min = min(node.right);
                node.right = deleteMin(node.right);
                node.key = min.key;
                node.value = min.value;
                node.n--;
            } else {
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }

    public static void main(String[] args) throws Exception {
        RedBlackTree<Integer, String> tree = new RedBlackTree<>();
        for (int i = 0; i < 9999; i++) {
            tree.put(i, "this is " + i);
        }
        //检查定义
        System.out.println(tree.LDR());
        //查
        System.out.println(tree.get(1001));
        System.out.println(tree.get(10000));
        //删
        tree.delete(1001);
        System.out.println(tree.LDR());
        System.out.println(tree.get(1001));
        //增
        tree.put(1001, "new 1001");
        System.out.println(tree.LDR());
        System.out.println(tree.get(1001));
        //改
        tree.put(1001, "new new 1001");
        System.out.println(tree.LDR());
        System.out.println(tree.get(1001));
    }
}