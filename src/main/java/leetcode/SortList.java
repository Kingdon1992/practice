package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-02-23 18:12:54
 */
public class SortList {
    public static void main(String[] args) {
        SortList s = new SortList();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next=new ListNode(3);
        b.next=new ListNode(4);
        ListNode merge = s.merge(a, b);
        System.out.println();
    }

    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head,ListNode tail){
        if(head==null){
            return null;
        }
        if(head.next==tail){
            head.next=null;
            return head;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=tail){
            slow=slow.next;
            fast=fast.next;
            if(fast!=tail){
                fast=fast.next;
            }
        }
        ListNode mid=slow;
        ListNode a=sortList(head,mid);
        ListNode b=sortList(mid,tail);
        return merge(a,b);
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode pre = new ListNode();
        ListNode node = pre;
        while (a != null || b != null) {
            if (a == null) {
                node.next = b;
                b = b.next;
            } else if (b == null) {
                node.next = a;
                a = a.next;
            } else if (a.val < b.val) {
                node.next = a;
                a = a.next;
            } else {
                node.next = b;
                b = b.next;
            }
            node = node.next;
        }
        return pre.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
