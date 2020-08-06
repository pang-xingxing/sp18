package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private boolean[][] site;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom;
    private int numOpenSites = 0;
    private int[][] surroundings = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        site = new boolean[N][N];
        top = 0;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);

    }

    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    private void validate(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            site[row][col] = true;
            numOpenSites += 1;
        }
        if (row == 0) {
            uf.union(xyTo1D(row, col), top);
            ufExcludeBottom.union(xyTo1D(row, col), top);
        }
        if (row == size - 1) {
            uf.union(xyTo1D(row, col), bottom);
        }
        for (int[] surrounding: surroundings) {
            int adjacentRow = row + surrounding[0];
            int adjacentCol = col + surrounding[1];
            if (adjacentRow >= 0 && adjacentRow < size) {
                if (adjacentCol >= 0 && adjacentCol < size) {
                    if (isOpen(adjacentRow, adjacentCol)) {
                        uf.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                        ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(adjacentRow, adjacentCol));
                    }
                }
            }
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return site[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufExcludeBottom.connected(xyTo1D(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
