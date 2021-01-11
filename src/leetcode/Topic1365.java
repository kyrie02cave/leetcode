package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class Topic1365 {
    /*
   * leetcode:1365. 有多少小于当前数字的数字
     给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i
     * 且 nums[j] < nums[i] 。
     以数组形式返回答案
     *
     * 示例 1：
       输入：nums = [8,1,2,2,3]
       输出：[4,0,1,1,3]
       解释：
       对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
       对于 nums[1]=1 不存在比它小的数字。
       对于 nums[2]=2 存在一个比它小的数字：（1）。
       对于 nums[3]=2 存在一个比它小的数字：（1）。
       对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
       */
    public int[] smallerNumbersThanCurrent01(int[] nums) {
        // 暴力法,时间复杂度n^2
            int[] result = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if(i!=j){
                        if(nums[i] > nums[j]){
                            result[i]++;
                        }
                    }
                }
            }
            return result;
    }

    public int[] smallerNumbersThanCurrent02(int[] nums){
        /*
         * 分析：
         *    每个元素最少需要比较一次，所以时间复杂度最优为O(n)
         *    最大(暴力解法)为O(n^2);
         *    优化主要就是去除重复比较
         *    一个比较容易想到的方法就是先排序，排序之后再遍历一遍数组，记录每个元素的个数
         *   最后由这个数组得到结果*/
            int n = nums.length;
            int[][] data = new int[n][2];
            for (int i = 0; i < n; i++) {
                data[i][0] = nums[i];
                data[i][1] = i;

            }
            // 升序排序
            Arrays.sort(data, new Comparator<int[]>(){
                @Override
                public int compare(int[] data1, int[] data2) {
                    return data1[0] - data2[0];
                }
            });

            int[] result = new int[n];
            result[data[0][1]] = 0;
            // 重复计数
            int count = 1;
            for (int i = 1; i <= n - 1; i++) {
                if(data[i][0] == data[i - 1][0]){
                    result[data[i][1]] =  result[data[i - 1][1]];
                    count++;
                }else{
                    result[data[i][1]] =  result[data[i - 1][1]] + count;
                    count = 1;
                }
            }
            return result;
    }

    public int[] smallerNumbersThanCurrent03(int[] nums){
        // 其他解法：计数排序，空间换时间， 0 <= nums[i] <= 100
        int[] countArray = new int[101];
        for (int num:nums) {
            countArray[num]++;
        }
        // 得到计数之后的数组，后项由前项获得
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        // 获得最后的输出
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i]==0 ? 0:countArray[nums[i] - 1];
        }
        return result;
    }



    @Test
    public void smallerNumbersThanCurrent01Test(){
        int[] nums = new int[]{6,5,6,8,0,0};
        int[] result = smallerNumbersThanCurrent03(nums);
        System.out.println(Arrays.toString(result));
    }

}
