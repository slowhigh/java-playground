import java.io.*;
import java.util.*;

class Main {
    private static final int S = 9;
    private static final int BOX_SIZE = 3;
    private static final int BLANK = 0;
    private static int[][] grid;
    private static boolean[][] rowCheck, colCheck, boxCheck;
    private static List<int[]> blanks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        grid = new int[S][S];
        rowCheck = new boolean[S][S + 1];
        colCheck = new boolean[S][S + 1];
        boxCheck = new boolean[S][S + 1];
        blanks = new ArrayList<>();

        for (int i = 0; i < S; i++) {
            String s = br.readLine();
            for (int j = 0; j < S; j++) {
                int num = Character.getNumericValue(s.charAt(j * 2));
                grid[i][j] = num;
                if (num == BLANK) {
                    blanks.add(new int[] { i, j });
                } else {
                    rowCheck[i][num] = colCheck[j][num] = boxCheck[getBoxIdx(i, j)][num] = true;
                }
            }
        }

        check(0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S; i++)
            for (int j = 0; j < S; j++)
                sb.append(grid[i][j]).append((j < S - 1) ? " " : "\n");

        System.out.print(sb.toString());
    }

    private static boolean check(int idx) {
        if (idx == blanks.size())
            return true;

        int[] blank = blanks.get(idx);
        int row = blank[0], col = blank[1];
        int box = getBoxIdx(row, col);

        for (int num = 1; num <= S; num++) {
            if (rowCheck[row][num] || colCheck[col][num] || boxCheck[box][num])
                continue;

            grid[row][col] = num;
            rowCheck[row][num] = colCheck[col][num] = boxCheck[box][num] = true;

            if (check(idx + 1))
                return true;

            grid[row][col] = BLANK;
            rowCheck[row][num] = colCheck[col][num] = boxCheck[box][num] = false;
        }

        return false;
    }

    private static int getBoxIdx(int row, int col) {
        return ((row / BOX_SIZE) * BOX_SIZE) + (col / BOX_SIZE);
    }
}