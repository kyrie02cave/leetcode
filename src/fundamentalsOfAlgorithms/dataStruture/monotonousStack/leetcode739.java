package fundamentalsOfAlgorithms.dataStruture.monotonousStack;

import java.util.Stack;

public class leetcode739 {
    /*739. 每日温度
      请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
      至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
      例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
      你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
*/
    public int[] dailyTemperatures(int[] T) {
        /*分析：使用单调栈实现，注意问题：1.遇大值则小值出栈；2.必须保证所有元素均出栈
        * 主要问题：部分出栈时，如何标记元素的坐标==>数组？*/
        // 卫语句
        int length = T.length;
        if(length==0){
            return null;
        }
        // 改造数组，绑定位置信息
        int[][] arr = new int[length][2];
        for (int i = 0; i < length; i++) {
            arr[i][0] = i;
            arr[i][1] = T[i];
        }
        // 定义两个栈，一个单调栈一个辅助栈
        Stack<int[]> stack = new Stack<>();
        int[] result = new int[length];
        stack.push(arr[0]);
        for (int i = 1; i < length; i++) {
            while(!stack.isEmpty()&&arr[i][1]>stack.peek()[1]){
                result[stack.peek()[0]] = arr[i][0] - stack.peek()[0];
                stack.pop();
            }
            stack.push(arr[i]);
        }
        // 判断是否有元素未出栈
        while(!stack.isEmpty()){
            result[stack.peek()[0]] = 0;
            stack.pop();
        }
        return result;
    }
}
