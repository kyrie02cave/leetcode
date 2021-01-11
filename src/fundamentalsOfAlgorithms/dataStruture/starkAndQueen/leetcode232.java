package fundamentalsOfAlgorithms.dataStruture.starkAndQueen;

import java.util.Stack;

public class leetcode232 {

    // 定义全局栈对象
    private Stack stackMain;
    private Stack stackAuxiliary;
    private int size;

    /** Initialize your data structure here. */
    public leetcode232() {
        this.stackMain = new Stack<Integer>();
        this.stackAuxiliary = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stackMain.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 如果dequeAuxiliary为空
        if(stackAuxiliary.isEmpty()){
            // 此时，顺序存储(在dequeMain中)
            size = stackMain.size();
            for (int i = 0; i < size; i++) {
                stackAuxiliary.push(stackMain.pop());
            }
        }
        return (int)stackAuxiliary.pop();
    }

    /** Get the front element. */
    public int peek() {
        // 如果dequeAuxiliary为空
        if(stackAuxiliary.isEmpty()){
            // 此时，顺序存储(在dequeMain中)
            size = stackMain.size();
            /*慎用：不要在循环中直接使用求length、size等的函数，一方面是性能，一方面是易出错(size改变时)*/
            for (int i = 0; i < size; i++) {
                stackAuxiliary.push(stackMain.pop());
            }
        }
        return (int)stackAuxiliary.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return (stackMain.isEmpty()&&stackAuxiliary.isEmpty());
    }
}
