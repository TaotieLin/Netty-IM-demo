package srot;

import java.util.Arrays;
import java.util.List;

/**
 * @author lkd
 * @date 2021/3/9 星期二 13:40
 */
public class SelectSort {





    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 6, 8, 4, 9, 2, 7);
        selectSort(list);
        list.forEach(System.out::println);
    }

    private static void selectSort(List<Integer> list) {
        int min = list.get(0),minIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < list.get(i+1) && i < list.size() -1) {
                min = list.get(i+1);
                minIndex = i +1 ;
            }
        }
    }

}
