package fundamentalsOfAlgorithms.algorithms.dynamic;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.max;

public class MaxShare02 {
    /*题目描述：
    * 122.买卖股票的最佳时机 II
    * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
      设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
      注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

      示例 1:
      输入: [7,1,5,3,6,4]
      输出: 7
      解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
      示例 2:
      输入: [1,2,3,4,5]
      输出: 4
      解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
         注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
         因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
      示例 3:
      输入: [7,6,4,3,1]
      输出: 0
      解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

      提示：
      1 <= prices.length <= 3 * 10 ^ 4
      0 <= prices[i] <= 10 ^ 4

    */
    public int maxProfit01(int[] prices) {
        /*分析：在每次股票下跌前一天卖出，下跌当天买入即可
        * 可以使用单调栈实现==>错误：不能使用单调栈，每次股票下跌都要清空栈
        * 细节：
        *      使用哨兵，使全部数据出栈*/

        // 添加哨兵
        int[] prices2 = new int[prices.length + 1];
        for (int i = 0; i < prices.length; i++) {
            prices2[i] = prices[i];
        }
        prices2[prices.length] = -1;

        // 初始化一个栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        int maxProfit;
        for (int i = 0; i < prices2.length - 1; i++) {
            // 如果栈空或下一个元素比当前元素大则进栈
            if(prices2[i+1]<prices2[i]){
                stack.push(prices2[i]);
                maxProfit = stack.peekFirst() - stack.peekLast();
                stack.clear();
                ans += maxProfit;
            }else{
                stack.push(prices2[i]);
            }
        }
        return ans;
    }

    public int maxProfit02(int[] prices) {
        /* 实际上使用的是贪心的思想！！！！
           分析：在每次股票下跌前一天卖出，下跌当天买入即可
         * 可以使用单调栈实现==>错误：不能使用单调栈，每次股票下跌都要清空栈
         * 细节：
         *      使用哨兵，使全部数据出栈*/

        // 要保证一定有输出，需添加哨兵
        int[] prices2 = new int[prices.length + 1];
        for (int i = 0; i < prices.length; i++) {
            prices2[i] = prices[i];
        }
        prices2[prices.length] = -1;

        int profit = 0;
        int tmp;
        int profitSum = 0;
        for (int i = 0; i < prices2.length - 1; i++) {
            tmp = prices2[i + 1] - prices2[i];
            if(tmp>0){
                profit += tmp;
            }else{
                profitSum +=  profit;
                profit = 0;
            }
        }
        return profitSum;
    }

    // 使用动态规划解题
    // 参考：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
    // solution/tan-xin-suan-fa-by-liweiwei1419-2/
    public int maxProfit03(int[] prices){
        /* 与背包问题类似，每天都相当于一件物品，可以选择拿或则不拿，当然，与背包不同的是，会受前面状态的影响

        第一步：定义状态
        状态 dp[i][j] 定义如下：
        dp[i][j] 表示到下标为 i 的这一天，持股状态为 j 时，我们手上拥有的最大现金数。
        注意：限定持股状态为 j 是为了方便推导状态转移方程，这样的做法满足 无后效性。
        其中：
        第一维 i 表示下标为 i 的那一天（ 具有前缀性质，即考虑了之前天数的交易 ）；
        第二维 j 表示下标为 i 的那一天是持有股票，还是持有现金。这里 0 表示持有现金（cash），1 表示持有股票（stock）。

        第二步：思考状态转移方程
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);

        第三步：确定初始值
        起始的时候：
        如果什么都不做，dp[0][0] = 0；
        如果持有股票，当前拥有的现金数是当天股价的相反数，即 dp[0][1] = -prices[i]；

        第四步：确定输出值
        上面也分析了，输出 dp[len - 1][0]，因为一定有 dp[len - 1][0] > dp[len - 1][1]
*/
        int len = prices.length;
        // 排除特殊情况
        if(len<2){
            return 0;
        }

        // 声明状态转移方程
        int[][] dp = new int[len][2];

        // 初始化状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 状态转移】
        for (int i = 1; i < len; i++) {
            // 只用到了前面一轮的信息，可以使用滚动数组优化空间
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 输出dp表
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(dp[i][j]);
                System.out.print('\t');
            }
            System.out.println();
        }
        return dp[len-1][0];
    }


    public int maxProfit04(int[] prices){
        /* 动态规划，空间优化版*/
        int len = prices.length;
        // 排除特殊情况
        if(len<2){
            return 0;
        }

        // 声明状态转移方程
        int[] dp = new int[2];

        // 初始化状态
        dp[1] = -prices[0];

        // 定义一个辅助临时变量
        int tmp;
        // 状态转移】
        for (int i = 1; i < len; i++) {
            // 只用到了前面一轮的信息，可以使用滚动数组优化空间
            tmp = dp[0];
            dp[0] = max(dp[0], dp[1] + prices[i]);
            dp[1] = max(dp[1], tmp - prices[i]);
        }
        // 输出dp表
        return dp[0];
    }

    @Test
    public void testMaxProfit(){
        int[] prices = new int[]{7,6,4,3,1};
        int profit = maxProfit04(prices);
        System.out.println("股票最大盈利为：" + profit);
    }
}
