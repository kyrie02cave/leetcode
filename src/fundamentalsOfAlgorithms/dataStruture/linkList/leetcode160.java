package fundamentalsOfAlgorithms.dataStruture.linkList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

public class leetcode160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*分析:后面的节点是重复的，所以从后往前遍历即可（错误点：链表不可以从后往前遍历）==>先反转链表
        * 错误点2：不能反转，反转之后链表B的结构也变了==>解决：不反转原链表，新建一个链表(不可行)
        * 新建两个栈，存储每个节点，然后逐一比较*/
        // 卫语句
        if(headA==null||headB==null){
            return null;
        }

        // 新建两个栈，用于存储节点元素
        Stack<ListNode> nodeStackA = new Stack<>();
        while(headA!=null){
            nodeStackA.push(headA);
            headA = headA.next;
        }
        Stack<ListNode> nodeStackB = new Stack<>();
        while(headB!=null){
            nodeStackB.push(headB);
            headB = headB.next;
        }

        // 链表无交点，返回
        if(nodeStackA.peek()!=nodeStackB.peek()){
            return null;
        }

        // 从后往前遍历，下一节点不相等则输出当前节点
        ListNode result = null;
        while(!nodeStackA.isEmpty()&&!nodeStackB.isEmpty()){
            // 栈为空时，不能调用peek方法
            if(nodeStackA.peek()==nodeStackB.peek()){
                result = nodeStackA.pop();
                nodeStackB.pop();
            }else {
                break;
            }
        }
        return result;

    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB){
        /*巧妙解法*/
        // 卫语句
        if(headA==null||headB==null){
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    @Test
    public void testGetIntersectionNode(){
        // 新建两个节点
        int connNodeA = 2;
        ListNode headA = new ListNode(0);
        ListNode startA = headA;
        for (int i = 0; i < connNodeA; i++) {
            ListNode node = new ListNode( 2 * (i + 1));
            headA.next = node;
            headA = node;
        }

        int connNodeB = 3;
        ListNode headB = new ListNode(1);
        ListNode startB = headB;
        for (int i = 0; i < connNodeB; i++) {
            ListNode node = new ListNode( 2 * i + 3);
            headB.next = node;
            headB = node;
        }

        int connNodeC = 13;
        ListNode headC = new ListNode(1);
        ListNode startC = headC;
        for (int i = 10; i < connNodeC; i++) {
            ListNode node = new ListNode( 2 * i + 3);
            headC.next = node;
            headC = node;
        }

        // 将链表A，B的下一节点均指向链表C的头节点
        headA.next = startC;
        headB.next = startC;

//        ListNode common = new ListNode(1);
//        ListNode startA = common;
//        ListNode startB = common;

        ListNode result = getIntersectionNode(startA, startB);
        System.out.println(result.val);
    }


}
