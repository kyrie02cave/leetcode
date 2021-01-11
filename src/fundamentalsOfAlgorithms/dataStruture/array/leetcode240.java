package fundamentalsOfAlgorithms.dataStruture.array;


import org.junit.Test;

import java.util.Arrays;

public class leetcode240 {
    /*240. 搜索二维矩阵 II
            编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

            每行的元素从左到右升序排列。
            每列的元素从上到下升序排列。*/

    public boolean searchMatrix(int[][] matrix, int target){
        /*最优思路：右上角数据，左边的比它小，右边的比它大，天然的决策点，
        每次与target比较都可以排除掉一侧的数据*/

        /*卫语句*/
        int rows = matrix.length;
        if(rows==0){
            return false;
        }
        int columns = matrix[0].length;
        if(columns==0){
            return false;
        }
        // 右下角数据最大
        if(target>matrix[rows - 1][columns - 1]){
            return false;
        }

        // 初始化搜索坐标
        int rowIndex = 0;
        int columnIndex  = columns - 1;
        while(rowIndex<rows&&columnIndex>=0){
            if(matrix[rowIndex][columnIndex]==target){
                return true;
            }
            if(matrix[rowIndex][columnIndex]>target){
                columnIndex--;
            }else{
                rowIndex++;
            }
        }
        return false;
    }

    @Test
    public void testSearchMatrix(){
        int[][] matrix = new int[][]{{1,4,7,11,15},
                                     {2,5,8,12,19},
                                     {3,6,9,16,22}};
        boolean b = searchMatrix(matrix, 17);
        System.out.println(b);
    }



    // 存在bug的程序...............思路也存在一定的问题
    public boolean searchMatrix01(int[][] matrix, int target){
        // 左上同时搜索
        /*卫语句*/
        int rows = matrix.length;
        if(rows==0){
            return false;
        }
        int columns = matrix[0].length;
        if(columns==0){
            return false;
        }
        if(target>matrix[rows - 1][columns - 1]){
            return false;
        }

        boolean isRow = rows<columns;
        int rowIndex = rows - 1;
        int columnIndex = columns - 1;
        boolean rowFlag;
        boolean columnFlag;
        boolean returnFlag;
        // 非方阵的处理方法
        while (rowIndex>0&&columnIndex>0){
            if(matrix[rowIndex][columnIndex]==target){
                return true;
            }
            // 若为方阵，跳出循环，执行方阵的逻辑
            if(rowIndex==columnIndex){
                break;
            }
            // 分4种情况
            rowFlag = matrix[rowIndex][columnIndex -1]<=target;
            columnFlag = matrix[rowIndex - 1][columnIndex]<=target;

            if(rowFlag&&columnFlag){
                return false;
            }

            if((!rowFlag)&&columnFlag){
                returnFlag = checkRow(matrix, target, rowIndex, columnIndex);
                if(returnFlag){
                    return true;
                }
            }

            if(rowFlag&&(!columnFlag)){
                returnFlag = checkColumn(matrix, target, rowIndex, columnIndex);
                if(returnFlag){
                    return true;
                }
            }

            if((!rowFlag)&&(!columnFlag)){
                // 向左转移
                if(isRow){
                    returnFlag = checkRow(matrix, target, rowIndex, columnIndex);
                    if(returnFlag){
                        return true;
                    }else{
                        columnIndex--;
                    }

                }else{
                    returnFlag = checkColumn(matrix, target, rowIndex, columnIndex);
                    if(returnFlag){
                        return true;
                    }else{
                        rowIndex--;
                    }
                }
            }
        }

        // 方阵搜索
        // 找到目标值可能在的子矩阵边
        int size = 0;
        for (int i = rowIndex; i >= 0; i--) {
            if(target>matrix[i - 1][i - 1]&&target<=matrix[i][i]){
                size = i;
            }
        }

        if(size==0&&matrix[0][0]!=target){
            return false;
        }

        // 找到所属边后，横纵搜索即可
        return checkRow(matrix, target, size, size)||checkColumn(matrix, target, size, size);
    }

    public boolean checkRow(int[][] matrix, int target, int rowIndex, int columnIndex){
        for (int i = columnIndex; i >= 0; i--) {
            if(matrix[rowIndex][i]==target){
                return true;
            }
            if(matrix[rowIndex][i]<target){
                return false;
            }
        }
        return false;
    }

    public boolean checkColumn(int[][] matrix, int target, int rowIndex, int columnIndex){
        for (int i = rowIndex; i >= 0; i--) {
            if(matrix[i][columnIndex]==target){
                return true;
            }
            if(matrix[i][columnIndex]<target){
                return false;
            }
        }
        return false;
    }
}
