package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Spliterator;

import static java.lang.Integer.max;

public class MaxShares01 {
    /*题目描述：
    * 121. 买卖股票的最佳时机
           给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
           如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
           注意：你不能在买入股票前卖出股票。
           示例 1:

           输入: [7,1,5,3,6,4]
           输出: 5
           解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
             注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
           示例 2:

           输入: [7,6,4,3,1]
           输出: 0
           解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。*/
    public int maxProfit01(int[] prices) {
     /*有点类似盛水最多的容器问题--->topic11
     * 尝试使用双指针，终止条件是start或end遍历到其中一个*/
     // 去除特殊情况
     if(prices==null||prices.length==0){
         return 0;
     }

     int start = 1;
     int end = prices.length - 2;
     int indexBuy = 0;
     int indexSale = prices.length - 1;
     int buyPrice = prices[indexBuy];
     int salePrice = prices[indexSale];
     // 注意循环终止条件不要写错！！！
     while((start<indexSale)&&(end>indexBuy)){
        if(buyPrice>=prices[start]){
            indexBuy = start;
            buyPrice = prices[indexBuy];
        }
        start++;

         if(salePrice<=prices[end]){
             indexSale = end;
             salePrice = prices[indexSale];
         }
         end--;
     }
     return (salePrice>buyPrice)?(salePrice - buyPrice):0;
    }

    public int maxProfit02(int[] prices){
        /*与盛水容器类似，可以对上述代码优化，
        * 在盛水容器中是比较左右的高度，此处则比较当前天数与下一天(上一天)的差值，
        左指针：左差值 = 后一天 - 当天
        右指针：右差值 = 前一天 - 当天
        if 左差值 > 右差值  ===> 下一状态dp[start+1][end]
        else  ===> 下一状态dp[start][end-1]
        再根据差值正负更新dp即可
        */
        return 0;
    }


    // 执行效率高的一种方法
    public int maxProfit03(int[] prices){
        if(prices==null||prices.length==0){
            return 0;
        }

        int maxSale = 0;
        int minPrice = prices[0];
        for (int price:prices) {
            // 先判断下一天价格是否低于当前最低价，低于则收益肯定小于0，不更新maxSale
            if(price<minPrice){
                minPrice = price;
                continue;
            }

            if(price - minPrice>maxSale){
                maxSale = price - minPrice;
            }
        }
        return maxSale;
    }

    // 单调栈实现
    public int maxProfit04(int[] prices){
        // 参考：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/
        // c-li-yong-shao-bing-wei-hu-yi-ge-dan-diao-zhan-tu-/
        // 复制原数组，在末尾添加一个哨兵，因为计算差值的条件是有栈元素出站，为了最后所有数据出栈，故添加哨兵
        int[] prices2 = new int[prices.length + 1];
        for (int i = 0; i < prices.length; i++) {
            prices2[i] = prices[i];
        }
        prices2[prices.length] = -1;

        // 对ArrayDeque还不熟悉。。。。
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        // 入栈条件：1.当前栈为空；2.小于栈顶元素
        for (int i = 0; i < prices2.length; i++) {
            // 弹出比当前元素大的元素，直到栈顶元素比当前元素小
            while(!stack.isEmpty()&&prices2[i]<stack.peek()){
                int top = stack.pop();
                if(stack.isEmpty()){
                    continue;
                }
                // 元素出栈完毕后，统计原栈差值并与ans比较，更新ans
                // 这里实际上在每次元素弹出时，都计算了差值，并与ans比较
                // 可以优化，但感觉没必要
                ans = max(ans, top - stack.peekLast());
            }
            // 当前元素入栈
            stack.push(prices2[i]);
        }
        return ans;
    }

    @Test
    public void testMaxProfit01(){
        int[] prices = new int[]{7,2,3,4,5,1,2};
        int Profit = maxProfit04(prices);
        System.out.println("股票最大盈利为：" + Profit);
    }
}
