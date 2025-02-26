import java.io.*;
import java.util.*;

// 2 4
// CAAB
// ADCB

// 3

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = br.readLine().split(" ");
        int R = Integer.parseInt(rc[0]), C = Integer.parseInt(rc[1]);

        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String l = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = l.charAt(j);
            }
        }

        System.out.println(solution(R, C, board));
    }

    public static final int[] DR = { -1, 1, 0, 0 }, DC = { 0, 0, -1, 1 };

    public static int solution(int R, int C, char[][] board) {
        int[][] dist = new int[R][C];
        dist[0][0] = 1;
        int max = 1;

        Stack<Point> s = new Stack<>();
        Point start = new Point(0, 0);
        start.alphabet += (board[0][0]);
        s.push(start);

        while (!s.isEmpty()) {
            Point cur = s.pop();

            for (int i = 0; i < 4; i++) {
                int r = cur.r + DR[i], c = cur.c + DC[i];

                if (r < 0 || c < 0 || r >= R || c >= C)
                    continue;

                String nextStr = Character.toString(board[r][c]);

                if (cur.alphabet.contains(nextStr))
                    continue;

                Point next = new Point(r, c);
                next.alphabet += cur.alphabet + nextStr;
                dist[r][c] = Math.max(dist[r][c], next.alphabet.length());
                s.push(next);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                max = Math.max(max, dist[i][j]);
            }
        }

        return max;
    }
}

class Point {
    int r, c;
    String alphabet;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
        alphabet = "";
    }
}