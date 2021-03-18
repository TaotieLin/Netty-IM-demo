package srot;

import java.util.Arrays;
import java.util.List;

/**
 * @author lkd
 * @date 2021/3/9 星期二 10:56
 */
public class mp {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 5, 6, 8, 4, 9, 2, 7);
        mpSort(list);
        list.forEach(System.out::println);
    }


    private static void mpSort(List<Integer> list){

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i; j++) {
                if (j != list.size() -1 && list.get(j) > list.get(j+1)){
                    int tmp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,tmp);
                }
            }
        }

    }

}
