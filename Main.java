import java.io.*;
import java.util.*;

// 2 4
// CAAB
// ADCB

// 3

class Main {
    private static final int[] DR = { -1, 1, 0, 0 }, DC = { 0, 0, -1, 1 };
    private static int R, C, max;
    private static int[][] board, visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rc = br.readLine().split(" ");
        R = Integer.parseInt(rc[0]);
        C = Integer.parseInt(rc[1]);
        max = 0;

        board = new int[R][C];
        visited = new int[R][C];

        for (int i = 0; i < R; i++) {
            String l = br.readLine();

            for (int j = 0; j < C; j++) {
                board[i][j] = 1 << (l.charAt(j) - 'A');
            }
        }

        dfs(0, 0, board[0][0], 1);
        System.out.println(max);
    }

    private static void dfs(int r, int c, int bit, int count) {
        max = Math.max(max, count);
        if (max == 26)
            return;

        visited[r][c] = bit;

        for (int i = 0; i < 4; i++) {
            int nr = r + DR[i], nc = c + DC[i];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                continue;

            if ((bit & board[nr][nc]) == 0 && (bit | board[nr][nc]) != visited[nr][nc]) {
                dfs(nr, nc, bit | board[nr][nc], count + 1);
            }
        }
    }
}