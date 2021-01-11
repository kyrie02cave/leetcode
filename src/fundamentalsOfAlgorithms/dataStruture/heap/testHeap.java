package fundamentalsOfAlgorithms.dataStruture.heap;

public class testHeap {
    public static void main(String[] args) {
        Heap heap = new Heap(20);
        System.out.println(heap.getSize());
        heap.push(1);
        heap.push(6);
        heap.push(4);
        heap.push(7);
        heap.push(1);
        System.out.println(heap.getCount());

        heap.pop();
        heap.pop();
        heap.pop();
        System.out.println(heap.getCount());
    }
}
