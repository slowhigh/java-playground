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

        long newWallMap, newVirusMap;
        for (int i = 0; i < n * m - 2; i++) {
            if (checkMap(i, wallMap, virusMap))
                continue;

            for (int j = i + 1; j < (n * m) - 1; j++) {
                if (checkMap(j, wallMap, virusMap))
                    continue;

                for (int k = j + 1; k < (n * m); k++) {
                    if (checkMap(k, wallMap, virusMap))
                        continue;

                    newWallMap = wallMap | 1L << i | 1L << j | 1L << k;
                    newVirusMap = virusMap;

                    for (int v : virus)
                        newVirusMap = spread(v, newWallMap, newVirusMap);

                    maxSafeZone = Math.max(maxSafeZone,
                            (m * n) - Long.bitCount(newWallMap) - Long.bitCount(newVirusMap));
                }
            }
        }

        System.out.println(maxSafeZone);
    }

    private static long spread(int i, long wallMap, long virusMap) {
        if (i - m >= 0 && !checkMap(i - m, wallMap, virusMap))
            virusMap = spread(i - m, wallMap, virusMap | (1L << (i - m)));
        if (i + m < m * n && !checkMap(i + m, wallMap, virusMap))
            virusMap = spread(i + m, wallMap, virusMap | (1L << (i + m)));
        if (i % m > 0 && !checkMap(i - 1, wallMap, virusMap))
            virusMap = spread(i - 1, wallMap, virusMap | (1L << (i - 1)));
        if ((i + 1) % m != 0 && !checkMap(i + 1, wallMap, virusMap))
            virusMap = spread(i + 1, wallMap, virusMap | (1L << (i + 1)));

        return virusMap;
    }

    private static boolean checkMap(int i, long wallMap, long virusMap) {
        return ((wallMap & (1L << i)) == (1L << i)) || ((virusMap & (1L << i)) == (1L << i));
    }
}