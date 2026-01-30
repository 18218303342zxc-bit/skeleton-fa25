public class IntList {
    int first;
    IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. Must use recursion.
     *
     * This method is non-destructive, i.e. it must not modify the original list.
     */
    public static IntList incrRecursiveNondestructive(IntList L, int x) {
        IntList M;
        if(L == null) {
            return null;
        } else {
            M = new IntList(L.first + x, incrRecursiveNondestructive(L.rest , x));
        }
        return M;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrRecursiveDestructive(IntList L, int x) {
        if(L == null){
            return null;
        } else {
            L.first +=  x;
            incrRecursiveDestructive(L.rest, x);
        }
        return L;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. May not modify the original list.
     */
    public static IntList incrIterativeNondestructive(IntList L, int x) {
        IntList M = new IntList(L.first + x ,null);
        IntList tem = L;
        IntList H = M ;
        while (tem.rest != null) {
            tem = tem.rest;
            H.rest = new IntList(tem.first + x ,null);
            H = H.rest;
        }
        return M;
    }

    /**
     * Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed
     * to use recursion. Modifies the original list.
     * You are not allowed to use "new" in this method.
     */
    public static IntList incrIterativeDestructive(IntList L, int x) {
        IntList tem = L;
        while (tem != null){
            tem.first += x;
            tem = tem.rest;
        }
        return L;
    }

    /**
     * Returns an IntList consisting of the elements of L1 followed by the
     * elements of L2.
     */
    public static IntList concatenate(IntList L1, IntList L2) {

        IntList tem = L1;
        if (tem != null) {
            while (tem.rest != null) {
                tem = tem.rest;
            }
            if (L2 != null) {
                tem.rest = new IntList(L2.first, L2.rest);
            } else {
                tem.rest = L2;
            }
        } else {
            if (L2 != null){
                L1 = L2;
            }

        }
        return L1;
    }

    /*
     * =================================================================
     * OPTIONAL METHODS
     * =================================================================
     */

    /**
     * Returns the sum of all elements in the IntList.
     */
    public int sum() {
        // Optional: Fill in this code
        return 0;
    }

    /**
     * Destructively adds x to the end of the list.
     */
    public void addLast(int x) {
        // Optional: Fill in this code
    }

    /**
     * Destructively adds x to the front of this IntList.
     * This is a bit tricky to implement. The standard way to do this would be
     * to return a new IntList, but for practice, this implementation should
     * be destructive.
     */
    public void addFirst(int x) {

        IntList poi = new IntList(this.first, this.rest);
        this.first = x;
        this.rest = poi;

    }
}
