package dp;

/**
 * @author lkd
 * @date 2021/3/2 星期二 16:47
 */
public class Money {

    private static int[] f;

    public static void main(String[] args) {
        f = new int[50];

        System.out.println(mixBySum(15));
        System.out.println(mixBySum(10));
        System.out.println(mixBySum(35));

    }

    private static int mixBySum(int i) {
        int r ;
        if (i == 1){
            f[i] = 1;
            return 1;
        }
        if (5 > i && i > 1){
            return mixBySum(i -1) +1;
        }
        if (i == 5){
            return 1;
        }
        if (11 > i && i >5){
            int j =  mixBySum(i -1);
            int k =  mixBySum(i - 5);
            r = Math.min(j, k);
            return r + 1;
        }
        if (i == 11){
            return 1;
        }
        int j =  mixBySum(i -1);
        int k =  mixBySum(i - 5);
        int l = mixBySum(i - 5);
        r = Math.min(j, k);
        r = Math.min(r,l);
        return r + 1;
    }




}
