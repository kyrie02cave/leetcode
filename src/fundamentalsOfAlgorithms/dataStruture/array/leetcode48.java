package fundamentalsOfAlgorithms.dataStruture.array;

import org.junit.Test;

import java.util.Arrays;

public class leetcode48 {
    /*48. 旋转图像
        给定一个 n × n 的二维矩阵表示一个图像。
        将图像顺时针旋转 90 度。
        说明：
        你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

        示例 1:
        给定 matrix =
        [
          [1,2,3],
          [4,5,6],
          [7,8,9]
        ],
        原地旋转输入矩阵，使其变为:
        [
          [7,4,1],
          [8,5,2],
          [9,6,3]
        ]

        示例 2:
        给定 matrix =
        [
          [ 5, 1, 9,11],
          [ 2, 4, 8,10],
          [13, 3, 6, 7],
          [15,14,12,16]
        ],
        原地旋转输入矩阵，使其变为:
        [
          [15,13, 2, 5],
          [14, 3, 4, 1],
          [12, 6, 8, 9],
          [16, 7,10,11]
        ]*/
    public void rotate(int[][] matrix) {
        // 卫语句
        int length = matrix.length - 1;
        if(length<=0){
            return;
        }

        // 定义一个临时变量用于暂存更新值
        int tmp;

        for (int i = 0; i <= length/2; i++) {
            for (int j = i; j < length - i; j++) {
                // 核心！！！
                tmp = matrix[j][length - i];
                matrix[j][length - i] = matrix[i][j];
                matrix[i][j] = matrix[length - j][i];
                matrix[length - j][i] = matrix[length - i][length - j];
                matrix[length - i][length - j] = tmp;
            }
        }
    }


    public void printMatrix(int[][] matrix){
        for(int i=0;i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
    @Test
    public void testRotate(){
        int[][] matrix = new int[][]{{5, 1, 9,11},
                                     {2, 4, 8,10},
                                     {13, 3, 6, 7},
                                     {15,14,12,16}};
        // 旋转矩阵
        rotate(matrix);
        // 打印矩阵
        printMatrix(matrix);
    }
}
