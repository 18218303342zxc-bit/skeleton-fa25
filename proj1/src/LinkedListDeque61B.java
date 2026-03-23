import java.sql.Array;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B <T> implements Deque61B<T> {
    @Override
    public void addFirst(T x) {
        Intnode<T> a = new Intnode<T>(x,sentinel,sentinel.next);
        sentinel.next.prev = a;
        sentinel.next = a;
        size += 1;

    }

    @Override
    public void addLast(T x) {
        Intnode<T> a = new Intnode<T>(x,sentinel.prev,sentinel);
        sentinel.prev.next = a;
        sentinel.prev = a;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Intnode<T> a = sentinel.next;
        while (a != sentinel){
            returnList.add(a.item);
            a = a.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {

        Intnode<T> a = sentinel.next;
        if (a != sentinel) {
            T x = sentinel.next.item;
            a.next.prev = sentinel;
            sentinel.next = a.next;
            a = null;
            return x;
        } else {
            return null;
        }
    }

    @Override
    public T removeLast() {
        Intnode<T> a = sentinel.prev;

        if (a != sentinel) {
            T x = sentinel.prev.item;
            sentinel.prev = a.prev;
            a.prev.next = sentinel;
            a = null;

            return x;
        } else {
            return null;
        }

    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            Intnode<T> a = sentinel;
            while (index >= 0) {
                a = a.next;
                index--;
            }
            return a.item;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        class Recursive<T> {
            public T helper(int index, Intnode<T> a) {
                if (index == 0) {
                    return a.item;
                } else {
                    return helper(index - 1, a.next);
                }
            }
        }
        if (index < size && index >= 0) {
           Recursive<T> a = new Recursive<>();
           return a.helper(index,sentinel.next);
        } else {
            return null;
        }

    }



    private Intnode<T> sentinel;
    private int size;

    public static class Intnode<T> {
        public T  item;
        public Intnode<T> next;
        public Intnode<T> prev;
        public Intnode (T x,Intnode<T> y,Intnode<T> z) {
            item = x;
            prev = y;
            next = z;
        }
    }

    public LinkedListDeque61B(){
        size = 0;
        sentinel = new Intnode<T>(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);
        lld.addLast(1);
        lld.addFirst(-1);
    }
    }

