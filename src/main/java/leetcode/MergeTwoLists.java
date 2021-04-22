package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-02-08 15:51:36
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(4)));
        ListNode l2 = new ListNode(1,new ListNode(3,new ListNode(4)));
        MergeTwoLists m = new MergeTwoLists();
        ListNode listNode = m.mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode returnNode;
        if (l1.val<=l2.val){
            returnNode=l1;
            l1=l1.next;
        }else {
            returnNode=l2;
            l2=l2.next;
        }
        ListNode now=returnNode;
        ListNode next;
        while (true){
            if (l1==null){
                now.next=l2;
                break;
            }else if(l2==null){
                now.next=l1;
                break;
            }else if(l1.val<=l2.val){
                next=l1.next;
                now.next=l1;
                l1.next=null;
                now=now.next;
                l1=next;
            }else{
                next=l2.next;
                now.next=l2;
                l2.next=null;
                now=now.next;
                l2=next;
            }
        }
        return returnNode;
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