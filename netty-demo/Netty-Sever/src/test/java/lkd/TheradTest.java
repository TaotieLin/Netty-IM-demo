package lkd;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lkd
 * @date 2020/11/12 星期四 16:14
 */
@Slf4j
public class TheradTest {

    public static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 打印出现异常的线程和异常名称
            System.out.println("捕获到异常 : 线程名[" + t.getName() + "], 异常名[" + e + "]");

            // 异常栈的信息
            e.printStackTrace();
            Boolean aTrue = Boolean.TRUE;
            // TODO ... 如果对异常还需要做特殊处理,可以在此处继续实现处理方法
        }

    }



    public static class ExceptionThread implements Runnable {

        @Override
        public void run() {
            // 线程信息
            Thread t = Thread.currentThread();
            System.out.println("执行线程：" + t);
            System.out.println("异常被谁处理：" + t.getUncaughtExceptionHandler());

            // 抛出异常
            throw new RuntimeException();
        }

    }


    public static void main(String[] args) {
        // 设置线程的异常处理器
//        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        // 执行会抛出异常的线程，看看是否会被捕获
//        try {
//            ExecutorService exec = Executors.newCachedThreadPool();
//            exec.execute(new ExceptionThread());
//        } catch (Exception e){
//            e.printStackTrace();
//            log.error("myErro",e.getStackTrace());
//        }
        System.out.println(statusTest(675).toString());
        System.out.println(statusTest(225).toString());
        System.out.println(statusTest(5).toString());

    }

    private static Thread.State statusTest(int var0){
        if ((var0 & 4) != 0) {
            return Thread.State.RUNNABLE;
        } else if ((var0 & 1024) != 0) {
            return Thread.State.BLOCKED;
        } else if ((var0 & 16) != 0) {
            return Thread.State.WAITING;
        } else if ((var0 & 32) != 0) {
            return Thread.State.TIMED_WAITING;
        } else if ((var0 & 2) != 0) {
            return Thread.State.TERMINATED;
        } else {
            return (var0 & 1) == 0 ? Thread.State.NEW : Thread.State.RUNNABLE;
        }
    }
}
