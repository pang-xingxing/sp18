package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int numTrials;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        numTrials = T;
        fractions = new double[T];
        int totalSites = N * N;
        for (int i = 0; i < T; i++) {
            int numOpenSites = 0;
            Percolation pF = pf.make(N);
            while (!pF.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!pF.isOpen(row, col)) {
                    pF.open(row, col);
                    numOpenSites += 1;
                }
            }
            fractions[i] = (double) numOpenSites / totalSites;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return (mu - 1.96 * sigma / Math.sqrt(numTrials));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return (mu + 1.96 * sigma / Math.sqrt(numTrials));
    }
}
