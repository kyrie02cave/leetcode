package fundamentalsOfAlgorithms.dataStruture.hashtable;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class leetcode128 {
    public int longestConsecutive(int[] nums) {
        /*基本实现：遍历一遍找到边界，再new一个区间大小的数组，依次将元素存入，最后遍历得出结果*/
        /*有序的前提下：建立一个hash表，查询上一元素是否存在，存在则将当前元素存入，value加一，不存在则为1
        * 左右双判断可解决===>缺点：太慢了！！！*/

        // 卫语句
        if(nums.length==0){
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            // 如果原先就存在，直接跳过 ==> 去重
            if(map.get(nums[i])!=null){
                continue;
            }

            // 需要参考周围的值
            if(map.get(nums[i] - 1)==null&&map.get(nums[i] + 1)==null){
                map.put(nums[i], 1);
            }
            if(map.get(nums[i] - 1)!=null){
                int tmp = 1;
                int count = map.get(nums[i] - 1) + 1;
                if(count>max){
                    max = count;
                }
                map.put(nums[i], count);
                while(map.get(nums[i] - tmp)!=null){
                    map.put(nums[i] - tmp, count);
                    tmp++;
                }
            }
            if(map.get(nums[i] + 1)!=null){
                int tmp = 1;
                int count;
                if(map.get(nums[i])==null){
                    count = map.get(nums[i] + 1) + 1;
                    if(count>max){
                        max = count;
                    }
                }else{
                    count = map.get(nums[i] + 1) + map.get(nums[i]);
                    if(count>max){
                        max = count;
                    }
                }
                map.put(nums[i], count);
                while(map.get(nums[i] - tmp)!=null){
                    map.put(nums[i] - tmp, count);
                    tmp++;
                }
                tmp = 1;
                while(map.get(nums[i] + tmp)!=null){
                    map.put(nums[i] + tmp, count);
                    tmp++;
                }
            }
        }
        return max;
    }

    public int longestConsecutive2(int[] nums){
        /*将所有数字都存入hash表，然后随机删除值及其连续值并统计*/
        // 卫语句
        if(nums.length==0){
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        int max = 1;
        // 将元素全部放入hash表
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
        }
        int gap;
        int count;
        int left;
        for (int k:nums){
            gap = 0;
            count = 0;
            // 去重
            if(!set.contains(k)){
                continue;
            }
            // 确定左边界
            while(set.contains(k - gap - 1)){
                gap++;
            }
            left = k - gap;
            while(set.contains(left)){
                // 去除当前元素
                set.remove(left);
                left++;
                count++;
            }
            if(count>max){
                max = count;
            }
        }
        return max;
    }

    @Test
    public void testLongestConsecutive(){
        int[] test = new int[]{100, 4, 200, 2, 1, 3};
        int ans = longestConsecutive2(test);
        System.out.println(ans);
    }
}
