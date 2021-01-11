package fundamentalsOfAlgorithms.dataStruture.heap;

public class leetcode23 {
    /*23. 合并K个升序链表
        给你一个链表数组，每个链表都已经按升序排列。
        请你将所有链表合并到一个升序链表中，返回合并后的链表。

        示例 1：
        输入：lists = [[1,4,5],[1,3,4],[2,6]]
        输出：[1,1,2,3,4,4,5,6]
        解释：链表数组如下：
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        将它们合并到一个有序链表中得到。
        1->1->2->3->4->4->5->6
        */
    public ListNode mergeKLists(ListNode[] lists) {
        /*分析： 联想==>归并排序子数组的合并==>三指针？？  ==> 复杂度：
        *       另：维护一个最小堆，每次都pop出最小元素    ==> 复杂度： */


        return null;
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }