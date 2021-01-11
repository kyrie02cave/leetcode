package fundamentalsOfAlgorithms.dataStruture.hashtable;

import org.junit.Test;

import java.util.HashMap;

public class leetcode149 {
    public int maxPoints(int[][] points) {
        /*存在不明bug！！！！！*/




        /*分析：与leetcode128类似，现将坐标以键值对(x,y)的形式存入Map，然后再遍历==>先寻找左端点
        * 最优解：借用八皇后问题判断是否在同一条直线上所采用的技巧*/
        // 卫语句
        if(points.length==0){
            return 0;
        }
        HashMap<Integer, Integer> mapLeftTop = new HashMap<>();
        HashMap<Integer, Integer> mapRightDown = new HashMap<>();
        HashMap<Integer, Integer> mapRow = new HashMap<>();
        HashMap<Integer, Integer> mapColumn = new HashMap<>();
        int max = 1;
        int leftTop;
        int rightDown;
        int row;
        int column;
        // 注意数组的遍历方法
        for (int i = 0; i < points.length; i++) {
            leftTop = points[i][0] - points[i][1];
            if(mapLeftTop.get(leftTop)!=null){
                mapLeftTop.put(leftTop, mapLeftTop.get(leftTop) + 1);
                if(mapLeftTop.get(leftTop)>max){
                    max = mapLeftTop.get(leftTop);
                }
            }else{
                mapLeftTop.put(leftTop, 1);
            }

            rightDown = points[i][0] + points[i][1];
            if(mapRightDown.get(rightDown)!=null){
                mapRightDown.put(rightDown, mapRightDown.get(rightDown) + 1);
                if(mapRightDown.get(rightDown)>max){
                    max = mapRightDown.get(rightDown);
                }
            }else{
                mapRightDown.put(rightDown, 1);
            }

            // 行列也需判断
            row = points[i][0];
            if(mapLeftTop.get(row)!=null){
                mapLeftTop.put(row, mapLeftTop.get(row) + 1);
                if(mapLeftTop.get(row)>max){
                    max = mapLeftTop.get(row);
                }
            }else{
                mapLeftTop.put(row, 1);
            }
            column = points[i][1];
            if(mapLeftTop.get(column)!=null){
                mapLeftTop.put(column, mapLeftTop.get(column) + 1);
                if(mapLeftTop.get(column)>max){
                    max = mapLeftTop.get(column);
                }
            }else{
                mapLeftTop.put(column, 1);
            }
        }
        return max;
    }

    @Test
    public void testMaxPoints(){
        int[][] test = new int[][]{{1, 3, 5, 4, 2, 1},{1, 2, 3, 1, 3, 4}};
        int ans = maxPoints(test);
        System.out.println(ans);
    }
}
