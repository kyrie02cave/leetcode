package fundamentalsOfAlgorithms.dataStruture.hashtable;

import java.util.HashMap;

public class leetcode1 {

    public int[] twoSum(int[] nums, int target){
        // 将遍历过的值存入hash表，之后遍历查询target与当前值得差值是否存在于hash表中
        HashMap<Integer, Integer> map = new HashMap<>();
        int res;
        for (int i = 0; i < nums.length; i++) {
            // 获取差值
            res = target - nums[i];
            if(map.get(res)!=null){
                return new int[]{i, map.get(res)};
            }
            // 不满足条件，存入hashMap
            map.put(nums[i], i);
        }
        return null;
    }
}
