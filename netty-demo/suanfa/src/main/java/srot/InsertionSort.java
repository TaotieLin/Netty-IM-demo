package srot;

import java.util.Arrays;
import java.util.List;

/**
 * @author lkd
 * @date 2021/3/9 星期二 11:27
 */
public class InsertionSort {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 6, 8, 4, 9, 2, 7);
        insertionSort(list);
        list.forEach(System.out::println);
    }

    private static void insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int tmp = list.get(i);
            insertionList(list,i,tmp);
        }
    }

    private static void insertionList(List<Integer> list, int i, int tmp) {
        //插入排序区间操作
        //对比第一个元素 如果小于  则插入到当前元素 排序区间元素全部后移一位
        //如果大于 则到后面一位开始比较
        for (int i1 = 0; i1 <= i; i1++) {
            if (tmp < list.get(i1)){
                for (int i2 = i ; i2 >= i1; i2--) {
                    list.set(i2 ,list.get(i2-1));
                }
                list.set(i1,tmp);
                return;
            }
        }
    }

}
