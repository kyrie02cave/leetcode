package fundamentalsOfAlgorithms.dataStruture.linkList;

import org.junit.Test;

public class leetcode206 {
    public ListNode reverseList01(ListNode head) {

        ListNode tmp;
        // 哨兵
        tmp = head.next;
        head.next = null;
        ListNode last = head;
        head = tmp;
        while(head.next!=null){
            tmp = head.next;
            head.next = last;
            last = head;
            head = tmp;
        }
        head.next = last;
        return head;
    }

    @Test
    public void testReverseList(){
        ListNode head = new ListNode(1);
        ListNode start = head;
        for (int i = 1; i < 10; i++) {
            ListNode node = new ListNode(i + 1);
            head.next = node;
            head = node;
        }

        ListNode newStart = reverseList01(start);
        while(newStart!=null){
            System.out.println(newStart.val);
            newStart = newStart.next;
        }
    }
}
