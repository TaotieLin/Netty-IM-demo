package taotie.controller;

import java.util.HashSet;

/**
 * @author lkd
 * @date 2020/7/29 17:02
 */
public class AnonymousDemo1 {


    public static void main(String args[]) {
        new AnonymousDemo1().play();
    }

    private void play() {
//        Dog dog = new Dog();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                while (dog.getAge() < 100) {
//                    // 过生日，年龄加一
//                    dog.happyBirthday();
//                    // 打印年龄
//                    System.out.println(dog.getAge());
//                }
//            }
//        };
//        new Thread(runnable).start();

        // do other thing below when dog's age is increasing
        // ....


        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        System.out.println(set);

    }
}
