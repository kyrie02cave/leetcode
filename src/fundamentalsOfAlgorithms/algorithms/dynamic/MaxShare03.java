package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class MaxShare03 {
    /*题目描述：
    123. 买卖股票的最佳时机 III
    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
    注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:
    输入: [3,3,5,0,0,3,1,4]
    输出: 6
    解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
         随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。

    示例 2:
    输入: [1,2,3,4,5]
    输出: 4
    解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
         因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

    示例 3:
    输入: [7,6,4,3,1]
    输出: 0
    解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
    public int maxProfit01(int[] prices) {
        // 结合单调栈实现
        // 将值与下标绑定
        HashMap<Integer, Integer> pricesMap = new HashMap<>();
        for (int i = 0; i < prices.length; i++) {
            pricesMap.put(i, prices[i]);
        }
        // 必须保证所有的元素都出栈
        pricesMap.put(prices.length, -1);

        // 初始化一个二维数组，用于存储股票收益信息
        int[][] profit = new int[2][3];
        int swapIndex;

        // 声明一个单调栈
        ArrayDeque<Map.Entry<Integer, Integer>> stack = new ArrayDeque<>();
        int top;
        int tmpProfit;
        int start=0;
        for (int i = 0; i < prices.length + 1; i++) {
            tmpProfit = 0;
            // 不入栈的条件
            while(!stack.isEmpty()&&pricesMap.get(i)<stack.peek().getValue()){
                top = stack.pop().getValue();
                if(stack.isEmpty()){
                    continue;
                }
                tmpProfit = Math.max(tmpProfit, top - stack.peekLast().getValue());
                start = stack.peekLast().getKey();
            }
            stack.push(Map.entry(i, pricesMap.get(i)));
            if(tmpProfit>profit[0][2]||tmpProfit>profit[1][2]){
                if(start<=profit[0][1]){
                    if(tmpProfit>profit[0][2]){
                        profit[0][0] = start;
                        profit[0][1] = i - 1;
                        profit[0][2] = tmpProfit;
                    }
                    continue;
                }

                if(start<=profit[1][1]&&tmpProfit>profit[1][2]){
                    if(tmpProfit>profit[1][2]){
                        profit[1][0] = start;
                        profit[1][1] = i - 1;
                        profit[1][2] = tmpProfit;
                    }
                    continue;
                }

                // 不存在重叠，则替换小的那个
                swapIndex = profit[0][2]<profit[1][2]?0:1;
                profit[swapIndex][0] = start;
                profit[swapIndex][1] = i - 1;
                profit[swapIndex][2] = tmpProfit;
            }
        }

        // 输出profit表
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(profit[i][j]);
                System.out.print('\t');
            }
            System.out.println();
        }

        return profit[0][2] + profit[1][2];
    }

    // 参考：https://leetcode-cn.com/problems/
    // best-time-to-buy-and-sell-stock-iii/solution/wu-chong-shi-xian-xiang-xi-tu-jie-123mai-mai-gu-pi/
    public int maxProfit02(int[] prices) {
        if(prices==null || prices.length==0) {
            return 0;
        }
        int n = prices.length;
        return dfs(prices,0,false,0);
    }

    private int dfs(int[] prices,int index,boolean state,int k) {
        //递归终止条件，数组执行到头了，或者交易了两次了
        /*
        * 每天都有三种选择：不动、买入、卖出，但是由于买、卖关联的存在，实际上每天只存在两种选择
        * 不动、买入/卖出，故定义变量
        *        state标记持有股票状态，
        *        index表示天数，
        *        同时，定义变量k标记完成交易的次数
                 ===> 递归函数签名：df(prices, index, state, k)
        */
        // 边界条件，天数走完或交易次数达到两次则退出
        if(index==prices.length || k==2) {
            return 0;
        }
        //因为任意一天都可能成为最后的输出。
        // 定义三个变量，分别记录[不动]、[买]、[卖],最后选择的是当天完成三种操作所获收益最大的那个
        int a=0,b=0,c=0;
        //保持不动
        a = dfs(prices,index+1,state,k);
        if(state) {
            //递归处理卖的情况，这里需要将k+1，表示执行了一次交易
            b = dfs(prices,index+1,false,k+1)+prices[index];
        }
        else {
            //递归处理买的情况
            c = dfs(prices,index+1,true,k)-prices[index];
        }

        //最终结果就是三个变量中的最大值
        return Math.max(Math.max(a,b),c);
    }

    public int maxProfit03(int[] prices){
        /*上解实际上是暴力解法，实际上(index,state,k)定义了一种状态，存在重复的情况，而实际上我们只需要最优的结果
        * 故，维护一个Map{(index,state,k):profit},减少不必要的计算*/
        // 卫语句
        if(prices==null||prices.length==0){
            return 0;
        }
        int n = prices.length;
        // 维护一个哈希表，避免重复调用
        HashMap<Key, Integer> map = new HashMap<Key, Integer>();
        return dfs(map, prices, 0, false, (short) 0);
    }

    private int dfs(Map<Key, Integer> map, int[] prices, int index, boolean state, short k){
        Key key = new Key(index, state, k);
        // 
        if(map.containsKey(key)){
            return map.get(key);
        }
        // 边界条件
        if(index==prices.length||k==2){
            return 0;
        }
        
        int a=0,b=0,c=0;
        a = dfs(map, prices, index + 1, state, k);
        if(state) {
            //递归处理卖的情况，这里需要将k+1，表示执行了一次交易
            b = dfs(prices,index+1,false,k+1)+prices[index];
        }
        else {
            //递归处理买的情况
            c = dfs(prices,index+1,true,k)-prices[index];
        }
        map.put(key, Math.max(Math.max(a,b),c));
        return map.get(key);
    }


    public int maxProfit04(int[] prices){
        /*由上可知，*/


        return 0;
    }

    // 转化为背包问题：https://leetcode-cn.com/problems/
    // best-time-to-buy-and-sell-stock-iii/solution/zhe-qi-shi-shi-yi-ge-bei-bao-wen-ti-by-liu-yulong/
    @Test
    public void testMaxProfit(){
        // 还是错的。。。。
        int[] prices = new int[]{6,1,3,2,4,11,1,3,2,4,7,1,3,2,4,9,1,3,2,4,7,1,3,2,6,7,1,3,2,4,7};
        int profit = maxProfit02(prices);
        System.out.println("股票最大收益为：" + profit);
    }
}

class Key{
    final int index;
    final boolean state;
    final short k;

    // 构造方法
    Key(int index, boolean state, short k){
        this.index = index;
        this.state = state;
        this.k = k;
    }

    // 自定义哈希算法，判断是否为同一Key,需要保证不存在哈希冲突
    public int hashCode(){
        return this.state?(this.index * 10^(this.k)):(-1 * this.index * 10^(this.k));
    }
}