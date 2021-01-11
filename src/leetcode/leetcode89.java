package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode89 {
    /*89. 格雷编码
            格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
            给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
            格雷编码序列必须以 0 开头。

            示例 1:
            输入: 2
            输出: [0,1,3,2]
            解释:
            00 - 0
            01 - 1
            11 - 3
            10 - 2
            对于给定的 n，其格雷编码序列并不唯一。
            例如，[0,2,3,1] 也是一个有效的格雷编码序列。
            00 - 0
            10 - 2
            11 - 3
            01 - 1
                **/
    public List<Integer> grayCode(int n) {
        /*分析：格雷码每次自动变动一位，通过手写格雷码找出变动(取反)位数的顺序规律=====>笨办法*/
        // 卫语句
        List<Integer> result = new ArrayList<>();
        result.add(0);
        if(n==0){
            return result;
        }
        // 获取取反顺序数组
        int length = (int)(Math.pow(2, n) - 1);
        int[] reverse = new int[length];

        getReverseArray(reverse, 0, length - 1, n-1);

        // 初始化格雷码数组
        int[] gray = new int[n];
        for (int i = 0; i < length; i++) {
            gray[reverse[i]] = ~gray[reverse[i]];
            // 计算数字
            result.add(getNum(gray, n));
        }
        System.out.println(result);
        return result;
    }

    public int getNum(int[] arr, int n){
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i]!=0)
            sum = sum + (int)Math.pow(2, n - 1 -i);
        }
        return sum;
    }

    public void getReverseArray(int[] reverse, int start, int end, int n){
        // 边界条件
        if(end - start <= 0){
            return;
        }
        // 计算中间值
        int middle = (end + start)/2;
        reverse[middle] = n;
        // 递归调用
        getReverseArray(reverse, start, middle - 1, n - 1);
        getReverseArray(reverse, middle + 1, end, n - 1);

    }
    @Test
    public void testGrayCode(){
        List<Integer> ans = grayCode(2);
    }
}
