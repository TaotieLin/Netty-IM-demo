package main.java;

import java.util.HashMap;

/**
 * @author lkd
 * @date 2020/10/26 星期一 19:55
 */
public class number03 {


    public static void main(String[] args) {
//        number1();
//        int[][] m = new int[5][5];
//        for (int i = 0; i < 5; i++) {
//            int[] i1 = new int[5];
//            for (int j = 0; j < 5; j++) {
//                i1[j] = i+j*i;
//                if (i1[j] == 0){
//                    i1[j] = j /2;
//                }
//            }
//            m[i] = i1;
//        }
        int[][] m = new int[1][2];
        m[0] = new int[]{5,6};
        System.out.println(findNumberIn2DArray(m,1));

        System.out.println(findNumberIn2DArray(m,6));

        System.out.println(findNumberIn2DArray(m,100));



        show(m);
    }

    private static void show(int[][] m) {
        for (int[] ints1 : m) {
            for (int i : ints1) {
                System.out.print(i+" ");
            }
            System.out.println("\r\n");
        }
    }

    private static void number1() {
        int[] ints = new int[5];
        ints[0] = 1;
        ints[1] = 2;
        ints[2] = 3;
        ints[3] = 4;
        ints[4] = 3;
        int repeatNumber = findRepeatNumber(ints);
        System.out.println(repeatNumber);
    }


    //找出数组中重复的数字。
    //
    //
    //在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    //
    //示例 1：
    //
    //输入：
    //[2, 3, 1, 0, 2, 5, 3]
    //输出：2 或 3
    // 
    //
    //限制：
    //
    //2 <= n <= 100000
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public static int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if (hashMap.get(num) != null) {
                return hashMap.get(num);
            }
            hashMap.put(num,num);
        }
        return 0;
    }

    //在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    //
    // 
    //
    //示例:
    //
    //现有矩阵 matrix 如下：
    //
    //[
    //  [1,   4,  7, 11, 15],
    //  [2,   5,  8, 12, 19],
    //  [3,   6,  9, 16, 22],
    //  [10, 13, 14, 17, 24],
    //  [18, 21, 23, 26, 30]
    //]
    //给定 target = 5，返回 true。
    //
    //给定 target = 20，返回 false。
    //
    // 
    //
    //限制：
    //
    //0 <= n <= 1000
    //
    //0 <= m <= 1000
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        //在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
        if (matrix == null || matrix.length <= 0){
            return false;
        }
        int i = 0;
        while (true) {
            int[] x = matrix[i];
            for (int i1 = 0; i1 < x.length; i1++) {
                if (i1 == 0 && x[i1] > target) {
                    if (i == 0){
                        return false;
                    }
                    i--;
                    continue;
                } else if (i1 == x.length-1 && x[i1] < target){
                    if (i == matrix.length -1){
                        return false;
                    }
                    i++;
                    continue;
                }
                if (x[i1] == target){
                    return true;
                }
            }
            i++;
            if (i == matrix.length){
                return false;
            }
            if (i < 0){
                return false;
            }
        }
    }

}
