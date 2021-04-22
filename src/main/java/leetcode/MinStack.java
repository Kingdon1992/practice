package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-02-23 16:41:11
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();
        minStack.pop();
        int top = minStack.top();
        int min1 = minStack.getMin();
        System.out.println();
    }
    Node head;
    Node minHead;
    /** initialize your data structure here. */
    public MinStack() {
        head=new Node();
        minHead=new Node();
    }

    public void push(int x) {
        Node next=head.next;
        head.next=new Node(x);
        head.next.next=next;

        Node min=minHead;
        while(min.next!=null&&min.next.val<x){
            min=min.next;
        }
        Node minNext=min.next;
        min.next=new Node(x);
        min.next.next=minNext;
    }

    public void pop() {
        if(head.next==null){
            return;
        }
        int val=head.next.val;
        head.next=head.next.next;
        Node min=minHead;
        while(min.next!=null){
            if(min.next.val==val){
                min.next=min.next.next;
                break;
            }
            min=min.next;
        }
    }

    public int top() {
        return head.next.val;
    }

    public int getMin() {
        return minHead.next.val;
    }

    class Node{
        int val;
        Node next;
        Node(){}
        Node(int val){
            this.val=val;
        }
    }
}
