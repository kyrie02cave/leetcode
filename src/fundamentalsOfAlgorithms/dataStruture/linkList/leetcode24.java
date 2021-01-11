package fundamentalsOfAlgorithms.dataStruture.linkList;

import org.junit.Test;

public class leetcode24 {
    public ListNode swapPairs(ListNode head) {
        /*分析：以两两为单位进行链表反转（错！！！会丢失头节点==>需附带上前一节点）*/
        // 卫语句
        if(head==null){
            return head;
        }
        // 哨兵, 获取交换后链表头节点
        ListNode start = head;
        if(head.next!=null){
            // 交换前两个
            ListNode tmp = head.next;
            head.next = head.next.next;
            tmp.next = head;
            start = tmp;
        }
        // 保留上一节点信息
        reverse(head, head.next);
        return start;
    }

    public void reverse(ListNode last, ListNode node) {
        // 边界条件
        if(node==null||node.next==null){
            return;
        }
        ListNode tmp = node.next;
        node.next = node.next.next;
        tmp.next = node;
        last.next = tmp;
        reverse(node, node.next);
    }

    @Test
    public void testSwapPairs(){
        ListNode head = new ListNode(1);
        ListNode start = head;
        for (int i = 2; i < 10; i++) {
            ListNode node = new ListNode(i);
            head.next = node;
            head = node;
        }

        ListNode newStart = swapPairs(start);
        // 打印处理后的链表
        while(newStart!=null){
            System.out.println(newStart.val);
            newStart = newStart.next;
        }
    }
}
