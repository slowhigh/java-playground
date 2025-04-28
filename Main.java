import java.io.*;
import java.util.*;

class Main {
    private static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        long wallMap = -1L;
        int wallCount = 0;
        long virusMap = 0L;
        int maxSafeZone = 0;
        List<Integer> virus = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j * 2);
                long bit = 1L << (m * i) + j;
                if (c == '0') {
                    wallMap ^= bit;
                } else if (c == '2') {
                    wallMap ^= bit;
                    virusMap |= bit;
                    virus.add((m * i) + j);
                } else {
                    wallCount++;
                }
            }
        }

        for (int i = 0; i < n * m - 2; i++) {
            if (checkBit(i, wallMap) || checkBit(i, virusMap)) continue;
            for (int j = i + 1; j < n * m - 1; j++) {
                if (checkBit(j, wallMap) || checkBit(j, virusMap)) continue;
                for (int k = j + 1; k < n * m; k++) {
                    if (checkBit(k, wallMap) || checkBit(k, virusMap)) continue;
                    long newVirusMap = virusMap;
                    for (int v : virus) newVirusMap = countSafeZone(v, wallMap | 1L << i | 1L << j | 1L << k, newVirusMap);
                    maxSafeZone = Math.max(maxSafeZone, (n * m) - wallCount - 3 - Long.bitCount(newVirusMap));
                }
            }
        }

        System.out.println(maxSafeZone);
    }

    private static long countSafeZone(int vIdx, long wallMap, long virusMap) {
        if (vIdx >= m && !checkBit(vIdx - m, wallMap) && !checkBit(vIdx - m, virusMap))
            virusMap = countSafeZone(vIdx - m, wallMap, virusMap | 1L << (vIdx - m));
        if (vIdx < m * (n - 1) && !checkBit(vIdx + m, wallMap) && !checkBit(vIdx + m, virusMap))
            virusMap = countSafeZone(vIdx + m, wallMap, virusMap | 1L << (vIdx + m));
        if (vIdx % m > 0 && !checkBit(vIdx - 1, wallMap) && !checkBit(vIdx - 1, virusMap))
            virusMap = countSafeZone(vIdx - 1, wallMap, virusMap | 1L << (vIdx - 1));
        if (vIdx < n * m && (vIdx + 1) % m != 0 && !checkBit(vIdx + 1, wallMap) && !checkBit(vIdx + 1, virusMap))
            virusMap = countSafeZone(vIdx + 1, wallMap, virusMap | 1L << (vIdx + 1));

        return virusMap;
    }

    private static boolean checkBit(int i, long bit) {
        return ((1L << i) & bit) == (1L << i);
    }
}