package leetcode;

/**
 * @author : wangqingsong
 * @since : 2021-02-23 16:22:08
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        boolean flagA = true;
        boolean flagB = true;
        while (true) {
            if (a == b) {
                return a;
            }
            if (a != null) {
                a = a.next;
            } else {
                if (flagA) {
                    a = headB;
                    flagA = false;
                }else {
                    return null;
                }
            }
            if (b != null) {
                b = b.next;
            } else {
                if (flagB) {
                    b = headA;
                    flagB = false;
                }else {
                    return null;
                }
            }
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
