import java.io.*;
import java.util.*;

class Main {
    private static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        long wallMap = 0L;
        long virusMap = 0L;
        int maxSafeZone = 0;
        List<Integer> virus = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j * 2);
                int idx = (m * i) + j;
                if (c == '1') {
                    wallMap |= 1L << idx;
                } else if (c == '2') {
                    virusMap |= 1L << idx;
                    virus.add(idx);
                }
            }
        }
        for (int i = 0; i < n * m; i++) {
            if (checkBit(i, wallMap, virusMap)) continue;
            for (int j = i + 1; j < (n * m) - 1; j++) {
                if (checkBit(j, wallMap, virusMap)) continue;
                for (int k = j + 1; k < (n * m) - 2; k++) {
                    if (checkBit(k, wallMap, virusMap)) continue;


                }
            }
        }
    }

    private static long spread(int i, long wallMap, long virusMap) {
        if (i >= m && !checkBit(i, wallMap, virusMap))
            virusMap = spread(i - m, wallMap, virusMap | (1L << (i - m)))
        if (i )
    }

    private static boolean checkBit(int i, long wallMap, long virusMap) {
        return ((wallMap & (1L << i)) == (1L << i)) || ((virusMap & (1L << i)) == (1L << i));
    }
}