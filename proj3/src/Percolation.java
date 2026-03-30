import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF A;
    private WeightedQuickUnionUF A1;
    private boolean[] B;
    private int size;
    private int virtualtop;
    private int virtualbottom;
    private int opencount;

    public Percolation(int N) {
        if(N<=0){
            throw new java.lang.IllegalArgumentException("illegal N");
        } else {
            A = new WeightedQuickUnionUF(N * N+2);
            A1 = new WeightedQuickUnionUF(N * N +2);
            B = new boolean[N * N];
            size = N;
            virtualtop = N*N;
            virtualbottom = N*N + 1;
            for (int i = 0; i < N; i++){
                A.union(i,virtualtop);
                A1.union(i,virtualtop);
            }
            for (int j = N*(N-1); j < N*N; j++){
                A.union(j,virtualbottom);
            }
            opencount = 0;
        }
    }

    public void open(int row, int col) {
        if (transform(row, col) < 0 || transform(row, col) >= size*size){
           throw new java.lang.IndexOutOfBoundsException();
        }
        if (B[transform(row,col)] == false) {
            B[transform(row,col)] = true;
        }
        if (below(row, col) >= 0 && B[below(row, col)] == true) {
            A.union(transform(row, col), below(row, col));
            A1.union(transform(row, col), below(row, col));
        }
        if (upper(row, col) >= 0 && B[upper(row, col)] == true) {
            A.union(transform(row, col), upper(row, col));
            A1.union(transform(row, col), upper(row, col));
        }
        if (right(row, col) >= 0 && B[right(row, col)] == true) {
            A.union(transform(row, col), right(row, col));
            A1.union(transform(row, col), right(row, col));
        }
        if (left(row, col) >= 0 && B[left(row, col)] == true) {
            A.union(transform(row, col), left(row, col));
            A1.union(transform(row, col), left(row, col));
        }
        opencount ++;
    }

    public boolean isOpen(int row, int col) {
        if (transform(row, col) < 0 || transform(row, col) >= size*size){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return B[transform(row,col)];
    }

    public boolean isFull(int row, int col) {
        if (transform(row, col) < 0 || transform(row, col) >= size*size){
            throw new java.lang.IndexOutOfBoundsException();
        }
        int j = transform(row,col);

        return A1.find(j) == A1.find(virtualtop) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return opencount;
    }

    public boolean percolates() {
       return A.find(virtualbottom) ==  A.find(virtualtop);
    }
    public int transform(int row, int col){
        return row * size + col;
    };
    public int upper(int row, int col) {
        int result = transform(row, col) - size;
        if(result >= 0 && result < size*size) {
            return result;
        } else {
            return -1;
        }
    }

    public int below(int row,int col) {
        int result = transform(row, col) + size;
        if(result >= 0 && result < size*size) {
            return result;
        } else {
            return -1;
        }
    }

    public int left(int row, int col) {
        int result = transform(row, col) - 1;
        if(result >= 0 && col > 0) {
            return result;
        } else {
            return -1;
        }
    }

    public int right(int row, int col) {
        int result = transform(row, col) + 1;
        if(result < size*size && col < size - 1) {
            return result;
        } else {
            return -1;
        }
    }
    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
