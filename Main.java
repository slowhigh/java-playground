import java.io.*;
import java.util.*;

class Main {
    private static final int[] DR = { -1, 1, 0, 0 }, DC = { 0, 0, -1, 1 };
    private static int _n, _m, _wall;
    private static Set<Long> _visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] l1 = br.readLine().split(" ");

        _n = Integer.parseInt(l1[0]);
        _m = Integer.parseInt(l1[1]);
        _wall = 0;
        int virus = 0;
        int[][] grid = new int[_n][_m];
        _visited = new HashSet<>();
        Queue<Point> q = new LinkedList<>();
        String[] ln;
        for (int i = 0; i < _n; i++) {
            ln = br.readLine().split(" ");
            for (int j = 0; j < _m; j++) {
                grid[i][j] = Integer.parseInt(ln[j]);
                if (grid[i][j] == 1) {
                    _wall++;
                } else if (grid[i][j] == 2) {
                    virus++;
                    q.add(new Point(i, j));
                }
            }
        }
        System.out.println(dfs(q, grid, 0, virus));

        // 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
        // 4 6
        // 0 0 0 0 0 0
        // 1 0 0 0 0 2
        // 1 1 1 0 0 2
        // 0 0 0 0 0 2
        // 9

        // oldWall, ,

        // - 방문 비트 마스킹
        // - if 방문 == 0
        // - if X <= 3
        // - 0 -> X
        // - else
        // - 0 -> 2
        // - if 방문 == X
        // - if X > 3
        // - X -> 2
        // - else
        // - no action

        // for 방문 기준으로 상하좌우
        // - 신규 in rect
        // - 2(바이러스), 1(벽)은 안됨
        // - 방문 유무 확인(이거 조건 생각해봐야함)
        // - 재귀호출

        // - 재귀호출할 때가 없다?
        // max = max vs (all - 1 - X(무조건 놔야 하니까 고정 3) - 2)
    }

    private static int dfs(Queue<Point> q, int[][] grid, int wall, int virus) {
        // visited = visited << (p.row * m + p.col);
        // gVisited.add(visited);

        if (q.size() == 0)
            return (_n * _m) - _wall - 3 - virus;

        Point p = q.poll();
        if (grid[p.row][p.col] == 0) {
            grid[p.row][p.col] = wall <= 3 ? -1 : 2;
        } else { // -1
            if (wall > 3)
                grid[p.row][p.col] = 2;
        }

        int max = 0;

        for (int i = 0; i < 4; i++) {
            int nr = p.row + DR[i], nc = p.col + DC[i];

            if (nr < 0 || nc < 0 || nr >= _n || nc >= _m)
                continue;

            if (grid[nr][nc] == 2 || grid[nr][nc] == 1)
                continue;

            max = Math.max(max, dfs(new LinkedList<>(q), grid, wall, virus));
        }

        return max;
    }
}

class Point {
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}