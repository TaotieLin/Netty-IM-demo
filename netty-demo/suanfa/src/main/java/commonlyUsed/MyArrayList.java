package commonlyUsed;

/**
 * @author lkd
 * @date 2021/3/6 星期六 13:57
 *
 * 动态数组获取元素某个元素的复杂度为O(1),添加、删除某个元素的复杂度都是O(n)
 */
public class MyArrayList<T>{


    private Object[] BASE;

    private int SIZE;
    /**
     * 模数 列表在进行操作的时候 add remove 增加模数的值
     * 这里模数看作是操作次数  用于再多线程下保证 快速失败的机制
     */
    private int MODULUS = 0;
    /**
     * 数组头部有元数据 ，元数据的最大大小在Hopspot 中不能超过8字节
     * Monitoring：Sentinel
     */
    private final int MAX_SIZE = Integer.MAX_VALUE - 8;

    public MyArrayList(int size){
        checkSize(size);
        BASE = new Object[size];
        SIZE = size;
    }

    public T get(int index){
        checkSize(index);
        return (T) BASE[index];
    }

    public T remove(int index){
        checkSize(index);

        T o = (T)BASE[index];

        if (index + 1 >= SIZE){
            System.arraycopy(BASE,index,BASE,0,SIZE -1);
        }

        return o;
    }

    private void checkSize(int index) {
        if (index > SIZE || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    public void checkModulus(){

    }




}
