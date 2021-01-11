package fundamentalsOfAlgorithms.dataStruture.linkList;

import org.junit.Test;

import java.util.Stack;

public class leetcode234 {
    public boolean isPalindrome(ListNode head) {
        /*分析：前后对比即可，先将链表的ListNode压入栈中并记录总个数，然后折半对比即可===>空间复杂度不是O(1)*/
        //卫语句
        if(head==null||head.next==null){
            return true;
        }

        ListNode start = head;
        Stack<ListNode> nodeStack = new Stack<>();
        int count = 0;
        while(head!=null){
            nodeStack.push(head);
            head = head.next;
            count++;
        }

        for (int i = 0; i <= count/2; i++) {
            if(start.val !=nodeStack.pop().val){
                return false;
            }
            start = start.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        /*同样先遍历一遍统计个数，然后反转后面的链表并返回尾节点,对于偶数个节点的情况，在中间添加一个节点*/
        /*写的太复杂了，出了很多bug！！！  ===>  使用栈效率偏低*/
        //卫语句
        if(head==null||head.next==null){
            return true;
        }

        // 遍历一遍数组，统计数组个数
        int count = 0;
        ListNode start = head;
        while(head!=null){
            count++;
            head = head.next;
        }
        head = start;
        for (int i = 0; i < count/2 - 1; i++) {
            head = head.next;
        }

        ListNode halfNode = head.next;
        if(count%2==0){
            // 添加辅助节点,
            ListNode endNode = new ListNode(0);
            ListNode next = head.next;
            head.next = endNode;
            endNode.next = next;
            halfNode = endNode;
        }

        // 获取反转后的链表头节点
        ListNode headNew = reverse(halfNode, halfNode.next);

        // 比较
        while(start.next!=null&&headNew.next!=null){
           if(start.val!=headNew.val){
               return false;
           } else{
               start = start.next;
               headNew = headNew.next;
           }
        }
        return true;
    }

    public ListNode reverse(ListNode lastNode, ListNode node){
        // 卫语句
        if(node.next==null){
            node.next = lastNode;
            // 注意，此处不可省，否则出现循环引用
            lastNode.next = null;
            return node;
        }
        // 哨兵，让中间节点指向null
        ListNode next = node.next;
        node.next = lastNode;
        // 注意，此处不可省，否则出现循环引用
        lastNode.next = null;
        lastNode = node;
        node = next;

        while(node.next!=null){
            next = node.next;
            node.next = lastNode;
            lastNode = node;
            node = next;
        }
        node.next = lastNode;
        return node;
    }
      // 快慢指针！！！
    @Test
    public void testIsPalindrome(){
        ListNode head = new ListNode(0);
        ListNode node = new ListNode(0);
        head.next = node;
        boolean flag = isPalindrome2(head);
        System.out.println(flag);
        System.out.println(head.toString());
    }

}
