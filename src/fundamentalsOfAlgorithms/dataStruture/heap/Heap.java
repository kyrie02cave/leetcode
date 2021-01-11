package fundamentalsOfAlgorithms.dataStruture.heap;

import org.junit.Test;

public class Heap {
    /*实现堆(优先队列)：堆是一个完全二叉树(数组实现)，其每个节点的值总是大于等于子节点的值。
       O(1) 时间内获得最大值，并且可以在 O(log n) 时间内取出最大值或插入任意值*/

    // 初始化数组(对)
    private int[] heap;
    private int count;
    private int size;

    public Heap(int size) {
        this.heap = new int[size];
        this.count = 0;
        this.size = size;
    }

    // 获得最大值
    public int getMax(){
        return heap[1];
    }

    // 插入任意值：把新的数据放在最后一位，然后上浮
    public void push(int k){
        // 计数加一
        count++;
        heap[count] = k;
        swim(count);
    }

    // 删除最大值：把最后一个数字挪到开头，然后下沉
    public void pop(){
        heap[1] = heap[count];
        heap[count] = 0;
        sink(1);
        // 计数减一
        count--;
    }

    // 上浮===>注意：pos应该是从1开始(数组index+1)
    public  void swim(int pos){
      // 不为根节点且大于父节点===>上浮
      while(pos>1&&heap[pos/2]<heap[pos]){
        swap(heap, pos/2, pos);
        // 更新节点索引值
        pos /= 2;
    }
}
    // 下沉
    public void sink(int pos){
        // 保证不越界
        while(2*pos<=size){
            // 计算子左子树index
            int i = 2 * pos;
            // 判断左右子树大小，并更新i(下沉至子树最大节点)
            if(i<size&&heap[i]<heap[i+1]){
                ++i;
            }
            // 大于则终止,小于则交换
            if(heap[pos]>=heap[i]){
                break;
            }
            // 交换
            swap(heap, pos, i);
            // 更新当前节点索引
            pos = i;

        }
    }


    // 数组元素交换
    public void swap(int[] arr, int swappedIndex, int swapIndex){
        int tmp = arr[swappedIndex];
        arr[swappedIndex] = arr[swapIndex];
        arr[swapIndex] = tmp;
    }
    public int[] getHeap() {
        return heap;
    }

    public int getCount() {
        return count;
    }

    public int getSize() {
        return size;
    }

}
