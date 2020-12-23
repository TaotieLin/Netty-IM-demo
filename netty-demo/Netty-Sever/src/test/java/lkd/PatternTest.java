package lkd;

import java.util.regex.Pattern;

/**
 * @author lkd
 * @date 2020/11/11 星期三 16:41
 */
public class PatternTest {

    public static void main(String[] args) {
        test1("862316464321");
        test1("2132316464321");
        test1("45686868686868");
        test1("6868686");
        test1("86121");

    }

    private static void test1(String phone){

        if (Pattern.matches("86.*", phone)) {
            System.out.println(phone.substring(2));
        } else {
            System.out.println(phone);
        }


    }

}
