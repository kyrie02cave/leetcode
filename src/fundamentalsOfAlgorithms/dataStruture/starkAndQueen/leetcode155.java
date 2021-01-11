package fundamentalsOfAlgorithms.dataStruture.starkAndQueen;

import java.util.Stack;

public class leetcode155 {
    /*155. 最小栈
    * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
        push(x) —— 将元素 x 推入栈中。
        pop() —— 删除栈顶的元素。
        top() —— 获取栈顶元素。
        getMin() —— 检索栈中的最小元素。

        示例:
        输入：
        ["MinStack","push","push","push","getMin","pop","top","getMin"]
        [[],[-2],[0],[-3],[],[],[],[]]
        输出：
        [null,null,null,null,-3,null,0,-2]

        解释：
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   --> 返回 -3.
        minStack.pop();
        minStack.top();      --> 返回 0.
        minStack.getMin();   --> 返回 -2.
        */

    /* 分析：起初的想法是，每次往栈中插入元素时，将比其小的数据弹入辅助栈，每次pop元素时，将对应的弹入辅助栈的元素
    * 重新入栈===>没编出来，bug太多*/
    private Stack stack;
    private Stack minStack;
    public leetcode155() {
        this.stack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty()||(int)minStack.peek()>=x){
            minStack.push(x);
        }
    }

    public void pop() {
        // (int)一定不要忘！！！！！！！！！！！！！！！！
        if(!minStack.isEmpty()&&(int)minStack.peek()==(int)stack.peek()){
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
       return (int)stack.peek();
    }

    public int getMin() {
        return (int)minStack.peek();
    }
}
