package lru;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author lkd
 * @date 2020/12/28 星期一 10:32
 */
public class ArrayListLru<T> extends ArrayList<T> {

    int maxSize = 8;

    Comparator<? super T> comparator = (o1, o2) -> 0;

    public ArrayListLru(int maxSize){
        this.maxSize = maxSize;
    }
    public ArrayListLru(){
    }

    public ArrayListLru(Comparator<? super T> comparator){
        this.comparator = comparator;
    }

    @Override
    public boolean add(T t) {
        if (this.size() >= maxSize){
            this.remove(this.stream().max(comparator).get());
        }
        return super.add(t);
    }

    @Override
    public T get(int index) {
        for (T t : this) {
            boolean equals = "1".equals(t);
        }
        return super.get(index);
    }
}
