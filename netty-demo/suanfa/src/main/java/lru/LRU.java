package lru;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author lkd
 * @date 2020/12/28 星期一 9:22
 */
public class LRU {

    private static ArrayList<NameNumber> ints = new ArrayList<>();

    public static void main(String[] args) {

        arrayLruAdd(ints,1);
        arrayLruAdd(ints,2);
        arrayLruAdd(ints,3);
        arrayLruAdd(ints,4);
        arrayLruAdd(ints,5);
        arrayLruGet(ints,1);
        arrayLruGet(ints,3);
        arrayLruGet(ints,4);
        arrayLruGet(ints,0);
        arrayLruAdd(ints,6);
        printArray(ints);
    }

    private static void printArray(ArrayList<NameNumber> ints) {
        for (NameNumber anInt : ints) {
            System.out.println(anInt.toString());
        }
    }

    private static void arrayLruAdd(ArrayList<NameNumber> ints, int i) {
        if (ints.size() >= 5){
            ints.remove(ints.stream().min(Comparator.comparing(NameNumber::getNumber)).get());
        }

        ints.add(new NameNumber(i));
    }

    private static NameNumber arrayLruGet(ArrayList<NameNumber> ints, int i){
        ints.get(i).number++;
        return ints.get(i);
    }


    static class NameNumber{

        private String name;

        private Integer number;

        public NameNumber(int i) {
            name = "initialize number is"+i;
            number = 0;
        }


        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "NameNumber{" +
                    "name='" + name + '\'' +
                    ", number=" + number +
                    '}';
        }
    }

}
