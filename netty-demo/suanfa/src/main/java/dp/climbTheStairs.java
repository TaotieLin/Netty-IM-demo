package dp;

/**
 * @author lkd
 * @date 2021/3/10 星期三 16:13
 */
public class climbTheStairs {

    static int[] f = new int[999];;

    public static void main(String[] args) {
        System.out.println(climbStairs(1));
        System.out.println(climbStairs(2));
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
        System.out.println(climbStairs(6));
        System.out.println(climbStairs(7));
        System.out.println(climbStairs(8));
    }

        public static int climbStairs(int n) {

            if(n == 1){
                if(f[n] == 0){
                    f[n] = 1;
                }
            }
            if(n == 2){
                if(f[n] == 0){
                    f[n] = 2;
                }
            }
            if(n>2){
                if(f[n] == 0){
                    f[n] = climbStairs(n-1) + climbStairs(n-2);
                }
            }

            return f[n];
        }
}
