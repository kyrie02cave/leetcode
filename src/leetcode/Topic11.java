package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Topic11 {
    /*题目描述：
    *11. 盛最多水的容器
         给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
         在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
         找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
         说明：你不能倾斜容器。

         示例 1：
         输入：[1,8,6,2,5,4,8,3,7]
         输出：49
         解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。
         在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
         */
    public int maxArea(int[] height) {
        /*因为是要满足底部长度与最短边的乘积最大，自然想到左右夹击(即双指针实现)
        终止条件：
                左或右指针任意一个遍历到相对边的位置
        更新条件：
                1）Min(左，右)*底部长 > 原面积
        */
        int start = 1;
        int end = height.length - 2;
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        int area = (rightIndex - leftIndex)*min(height[leftIndex], height[rightIndex]);
        int areaTemp;
        while((start<rightIndex)&&(end>leftIndex)){
            areaTemp = (rightIndex - start)*min(height[start], height[rightIndex]);
            if(area<areaTemp){
                leftIndex = start;
                area = areaTemp;
            }
            start++;

            areaTemp = (end - leftIndex)*min(height[leftIndex], height[end]);
            if(area<areaTemp){
                rightIndex = end;
                area = areaTemp;
            }
            end--;
        }

        return area;
    }


    public int maxAreaPlus(int[] height){
        /*上述代码指针移动的代码过于臃肿，重复性高
        * 上述代码是左右移动分开，还需要记录选定边的位置,
        * 可以将二者合并*/

        int start = 0;
        int end = height.length - 1;
        int area = 0;
        // 因为无论左右移动一位，底边长都是-1，所以只需计算长边更新的area与原面积比较即可
        // 短边移动！！！(核心)
        while(start<end) {
            if(height[start] < height[end]) {
                area = max(area, (end - start) * height[start++]);
            }else{
                area = max(area, (end - start) * height[end--]);
            }
        }
        return area;
    }

    public int maxAreaDynamic(int[] height){
        /*这题也可用动态规划求解，不过与上述解法差异不大
        终止条件：
               start>=end
        状态转移方程：
               // 找短边
               if(height[start]<height[end])
                  areaTemp = (end - start - 1) * min(height[start+1], height[end])
                  area[start+1][end] = max(area[start][end], areaTemp)
               else:
                  areaTemp = (end - 1 - start) * min(height[start], height[end - 1])
                  area[start][end - 1] = max(area[start][end], areaTemp)

         注意：此时的子问题为start，end所界定的两边所能得到的最大面积
         */
        int start =0;
        int end = height.length - 1;
        int[][] area = new int[height.length][height.length];
        area[start][end] = (end - start)*min(height[start], height[end]);
        int areaTemp;

        // 动态规划,空间上应该是可以优化的，暂时跳过！！！
        while(start<end){
            if(height[start]<height[end]) {
                areaTemp = (end - start - 1) * min(height[start + 1], height[end]);
                area[start + 1][end] = max(area[start][end], areaTemp);
                start++;
            }
            else{
                areaTemp = (end - 1 - start) * min(height[start], height[end - 1]);
                area[start][end - 1] = max(area[start][end], areaTemp);
                end--;
            }
        }

        System.out.println("start:" + start);
        System.out.println("end:" + end);
        for (int[] num:area) {
            System.out.println(Arrays.toString(num));
        }

        return area[start][end];
    }

    @Test
    public void testMaxArea(){
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        int area = maxArea(height);
        System.out.println("最多盛水：" + area);
    }

    @Test
    public void testMaxAreaPlus(){
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        int area = maxAreaPlus(height);
        System.out.println("最多盛水：" + area);
    }

    @Test
    public void testMaxAreaDynamic(){
        int[] height = new int[]{1,8,3,2,5,4,18,3,7};
        int area = maxAreaDynamic(height);
        System.out.println("最多盛水：" + area);
    }
}
