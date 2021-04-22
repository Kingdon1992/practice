package tree;

/**
 * 单词查找树
 *
 * @author : wangqingsong
 * @since : 2021-04-14 09:40:48
 */
public class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        int len = word.length();
        Node node = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            Node next=node.children[convert(c)];
            if(next==null){
                next=new Node(false);
                node.children[convert(c)]=next;
            }
            node=next;
        }
        node.flag=true;
    }

    public boolean search(String word) {
        int len = word.length();
        Node node = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            Node next=node.children[convert(c)];
            if(next==null){
                return false;
            }
            node=next;
        }
        return node.flag;
    }

    public boolean startsWith(String prefix) {
        int len = prefix.length();
        Node node = root;
        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            Node next=node.children[convert(c)];
            if(next==null){
                return false;
            }
            node=next;
        }
        return true;
    }

    private int convert(char c) {
        return c - 'a';
    }

    static class Node {
        boolean flag;
        Node[] children;

        Node() {
            children = new Node[26];
        }

        Node(boolean flag) {
            this.flag = flag;
            children = new Node[26];
        }
    }
}
