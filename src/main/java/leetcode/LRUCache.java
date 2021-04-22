package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangqingsong
 * @since : 2021-02-24 18:28:22
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.get(2);
        System.out.println(lruCache.toString());
        lruCache.put(2,6);
        System.out.println(lruCache.toString());
        lruCache.get(1);
        System.out.println(lruCache.toString());
        lruCache.put(1,5);
        System.out.println(lruCache.toString());
        lruCache.put(1,2);
        System.out.println(lruCache.toString());
        lruCache.get(1);
        System.out.println(lruCache.toString());
        lruCache.get(2);
        System.out.println(lruCache.toString());
    }

    Map<Integer,Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        map=new HashMap<>();
        head=new Node();
        tail=new Node();
        head.next=tail;
        tail.pre=head;
        this.capacity=capacity;
    }

    public int get(int key) {
        Node node=map.get(key);
        if(node==null){
            return -1;
        }
        LRU(node);
        return node.val;
    }

    void LRU(Node node){
        removeNode(node);
        addToHead(node);
    }

    public void put(int key, int value) {
        if(map.size()>=capacity){
            Node last = removeLast();
            map.remove(last.key);
        }
        Node node=new Node(key,value);
        map.put(key,node);
        addToHead(node);
    }

    void addToHead(Node node){
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
        node.pre=head;
    }

    void removeNode(Node node){
        Node pre=node.pre;
        Node next=node.next;
        pre.next=next;
        next.pre=pre;
    }

    Node removeLast(){
        Node remove=tail.pre;
        Node removePre=remove.pre;
        removePre.next=tail;
        tail.pre=removePre;
        return remove;
    }


    class Node{
        int key;
        int val;
        Node next;
        Node pre;
        Node(){}
        Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
}
