package fundamentalsOfAlgorithms.dataStruture.linkList;

public class ListNode2<AnyType> {
    private AnyType val;
    private ListNode2<AnyType> next;

    public ListNode2(AnyType val) {
        this.val = val;
    }

    public AnyType getVal() {
        return val;
    }

    public void setVal(AnyType val) {
        this.val = val;
    }

    public ListNode2<AnyType> getNext() {
        return next;
    }

    public void setNext(ListNode2<AnyType> next) {
        this.next = next;
    }
}
