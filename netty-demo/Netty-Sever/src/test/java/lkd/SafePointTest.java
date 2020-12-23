package lkd;

import java.util.HashMap;

/**
 * 安全点测试类
 * @author lkd
 * @date 2020/11/12 星期四 9:55
 */
public class SafePointTest {

    public static void main(String[] args) {
        MyThread t=new MyThread();
        PrintThread p=new PrintThread();
        t.start();
        p.start();
    }


    public static class MyThread extends Thread{
        HashMap map=new HashMap();
        public void run(){
            try{

                while(true){
                    if(map.size()*512/1024/1024>=300){

                    }
                    byte[] b1;

                    //map占用 字节数：
                    //https://blog.csdn.net/GV7lZB0y87u7C/article/details/90735506
                    for(int i=0;i<100;i++){
                        b1=new byte[512];    //byte字节 0.5K

                        map.put(System.nanoTime(),b1); //System.nanoTime() -long 占据8字节

                    }
                    Thread.sleep(1);

                }



            }catch(Exception e){
                e.printStackTrace();
            }


        }

    }

    public static class PrintThread extends Thread{

        public static final long starttime=System.currentTimeMillis();
        public void run(){
            try{
                while(true){
                    long t=System.currentTimeMillis()-starttime;
                    System.out.println(t/1000+"."+t%1000);
                    Thread.sleep(100);

                }
            }catch(Exception e ){
                e.printStackTrace();
            }
        }

    }


}
