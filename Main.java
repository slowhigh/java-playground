import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Point S = null, E = null, A = null, a = null, B = null, b = null;

        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j * 2);
                grid[i][j] = c;

                if (c == 'S')
                    S = new Point(i, j);
                else if (c == 'E')
                    E = new Point(i, j);
                else if (c == 'A')
                    A = new Point(i, j);
                else if (c == 'a')
                    a = new Point(i, j);
                else if (c == 'B')
                    B = new Point(i, j);
                else if (c == 'b')
                    b = new Point(i, j);
            }
        }

        System.out.println(aStar(n, grid, S, E, A, a, B, b));
    }

    public static int aStar(int n, char[][] grid, Point S, Point E, Point A, Point a, Point B, Point b) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[S.row][S.col] = 1;

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt((p) -> p.hv));

        return 0;
    }
}

class Point implements Comparable<Point> {
    int row, col, dist, hv;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
        this.dist = 0;
        this.hv = 0;
    }

    public Point(int row, int col, int dist, int hv) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.hv = hv;
    }

    public boolean equals(Point other) {
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int compareTo(Point other) {
        return this.hv - other.hv != 0 ? this.hv - other.hv : this.row - other.row != 0 ? this.row - other.row : 0;
    }
}