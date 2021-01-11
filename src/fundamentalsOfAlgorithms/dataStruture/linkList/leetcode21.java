package fundamentalsOfAlgorithms.dataStruture.linkList;

import org.junit.Test;

public class leetcode21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        /*分析：比较，每次都将小的那个输出*/
        // 卫语句
        // 如果存在空链表，则直接输出
        if(l1==null||l2==null){
            if(l1==null){
                return l2;
            }else{
                return l1;
            }
        }

        // 新建链表头节点
        ListNode start;
        ListNode head;
        // 哨兵
        if(l1.val<l2.val){
            start = l1;
            head = l1;
            // 更新l1
            l1 = l1.next;
        }else{
            start = l2;
            head = l2;
            // 更新l2
            l2 = l2.next;
        }

        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                head.next = l1;
                head = l1;
                l1 = l1.next;
            }else{
                head.next = l2;
                head = l2;
                l2 = l2.next;
            }
        }
        //将后续未排序的元素追加至末尾
        if(l1==null){
            while(l2!=null){
                head.next = l2;
                head = l2;
                l2 = l2.next;
            }
        }else{
            while(l1!=null){
                head.next = l1;
                head = l1;
                l1 = l1.next;
            }
        }
        return start;
    }

    @Test
    public void testMergeTwoLists(){
        // 创建链表1
        ListNode head1 = new ListNode(1);
        ListNode start1 = head1;
        for (int i = 1; i < 9; i++) {
            ListNode node = new ListNode(2*i + 1);
            head1.next = node;
            head1 = node;
        }
        // 创建链表2
        ListNode head2 = new ListNode(0);
        ListNode start2 = head2;
        for (int i = 1; i < 5; i++) {
            ListNode node = new ListNode(2*i);
            head2.next = node;
            head2 = node;
        }
        // 打印链表1
//        while(start1!=null){
//            System.out.println(start1.val);
//            start1 = start1.next;
//        }
//        // 打印链表2
//        while(start2!=null){
//            System.out.println(start2.val);
//            start2 = start2.next;
//        }

        System.out.println("===================================");
        ListNode start = mergeTwoLists(start1, start2);
        // 打印链表
        while(start!=null){
            System.out.println(start.val);
            start = start.next;
        }
    }


}
