package dp;

/**
 * @author lkd
 * @date 2021/3/2 星期二 16:47
 */
public class Money {

    private static int[] f;

    public static void main(String[] args) {
        f = new int[50];

        System.out.println(minBySum2(27));

    }

    //你有三种硬币，2元、5元、7元，每种硬币足够多，买一本书需要27元，用最少的硬币组合
    private static int minBySum2(int i) {
        if (i < 2){
            return 999;
        }
        if (i == 2){
            if (f[i] == 0) {
                return 1;
            }
        }
        if (i < 5){
            if (f[i] == 0) {
                f[i] = minBySum2(i - 2) + 1;
            }
        }
        if (i == 5){
            if (f[i] == 0) {
                f[i] = 1;
            }
        }
        if (i < 7){
            if (f[i] == 0) {
                int i1 = minBySum2(i - 2) + 1;
                int i2 = minBySum2(i - 5) + 1;
                f[i] = Math.min(i1, i2);
            }
        }
        if (i == 7){
            if (f[i] == 0) {
                f[i] = 1;
            }
        }
        if (f[i] == 0) {
            int i1 = minBySum2(i - 2) + 1;
            int i2 = minBySum2(i - 5) + 1;
            int i3 = minBySum2(i - 7) + 1;
            f[i] = Math.min(Math.min(i1, i2), i3);
        }
        return f[i];
    }

    private static int minBySum(int i) {
        int r ;
        if (i == 1){
            f[i] = 1;
            return 1;
        }
        if (5 > i && i > 1){
            return minBySum(i -1) +1;
        }
        if (i == 5){
            return 1;
        }
        if (11 > i && i >5){
            int j =  minBySum(i -1);
            int k =  minBySum(i - 5);
            r = Math.min(j, k);
            return r + 1;
        }
        if (i == 11){
            return 1;
        }
        int j =  minBySum(i -1);
        int k =  minBySum(i - 5);
        int l = minBySum(i - 5);
        r = Math.min(j, k);
        r = Math.min(r,l);
        return r + 1;
    }




}
