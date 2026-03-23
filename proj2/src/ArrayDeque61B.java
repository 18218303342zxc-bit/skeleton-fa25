import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.StringBuilder;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private int cap = 8;
    private int nextfirst;
    private T[] item;
    private int nextlast;
    public int size;
    private int sizeup = 2;
    private double sizedown = 0.5;
    public ArrayDeque61B(){
     size = 0;
     nextfirst = 0;
     nextlast = 1;
     item = (T[]) new Object[cap];

    }
    public Iterator<T> iterator() {
        return new classiterator();
    }

    public class classiterator implements Iterator<T>{
        private int wizpos;
        public classiterator(){
            wizpos = 0;
        }
        public boolean hasNext(){
            return wizpos < size;
        }
        public T next(){
            T x = get(wizpos);
            wizpos += 1;
            return x;

        }

    }

    public ArrayDeque61B(int cap){
        size = 0;
        nextfirst = 0;
        nextlast = 1;
        item = (T[]) new Object[cap];

    }
    private void resizeup( ){
       T[] up = (T[]) new Object[cap*sizeup];
       for (int i = 0; i < size;i++) {
           up[i] = get(i);

       }
       nextfirst = cap * sizeup - 1;
       nextlast = size;
       cap = cap * sizeup;
       item = up;
    }

    private void resizedown( ){
        T[] down = (T[]) new Object[(int)(cap*sizedown)];
        for (int i = 0; i < size;i++) {
            down[i] = get(i);

        }
        nextfirst = down.length - 1;
        nextlast = size;
        cap = (int)(cap * sizedown);
        item = down;

    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (size < cap) {
        item[nextfirst] = x;
        nextfirst = (nextfirst - 1 + cap) % cap;
        size += 1;
        } else {
            resizeup();
            item[nextfirst] = x;
            nextfirst = (nextfirst - 1 + cap) % cap;
            size += 1;
        }

    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        if (size < cap) {
            item[nextlast] = x;
            nextlast = (nextlast + 1) % cap;
            size += 1;
        }  else {
            resizeup();
            item[nextlast] = x;
            nextlast = (nextlast + 1) % cap;
            size += 1;
        }

    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if ((double)(size/ cap) <= 0.25) {
            nextfirst = (nextfirst + 1) % cap;
            T result = item[nextfirst];
            item[nextfirst] = null;
            return result;
        }  else {
            if (size > 0) {
                nextfirst = (nextfirst + 1) % cap;
                T result = item[nextfirst];
                item[nextfirst] = null;
                return result;
            } else {
                return null;
            }

        }


    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if ((double)(size/ cap) <= 0.25) {
            resizedown();
            nextlast = ((nextlast - 1 ) % cap);
            T result = item[nextlast];
            item[nextlast] = null;
            return result;
        } else {
            if (size > 0) {
                nextlast = ((nextlast - 1) % cap);
                T result = item[nextlast];
                item[nextlast] = null;
                return result;
            } else {
                return null;
            }

        }
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index > cap) {
            return null;
        } else if (index < 0) {
            return null;
        } else {
            int realpos = (((nextfirst + 1) % cap) + index) % cap;
            return item[realpos];
        }
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if(other instanceof Deque61B<?> w) {
            if (w.size() != this.size()) {
                return false;
            } else {
                for(T x : this) {
                    if (x == w.removeFirst()){
                        break;
                    } return false;

                }
                return true;
            }

        }
        return false;
    }
    @Override
    public String toString() {
        StringBuilder retstr = new StringBuilder("{");
        for(int i = 0; i < size - 1; i++) {
            retstr.append(item[i].toString());
            retstr.append(",");
        }
        retstr.append(item[size - 1].toString());
        retstr.append("}");
        return retstr.toString();


    }
}

