import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer>[] graph;
    private static int[] colors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            graph = new List[V + 1];
            for (int i = 1; i <= V; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            colors = new int[V + 1]; // 0: 미방문, 1 or -1: 그룹 색상
            boolean result = true;

            for (int i = 1; i <= V; i++) {
                if (colors[i] != 0)
                    continue;

                if (!dfsCheck(i, 1)) {
                    result = false;
                    break;
                }
            }

            sb.append(result ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }

    private static boolean dfsCheck(int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == 0) {
                if (!dfsCheck(neighbor, -color)) {
                    return false;
                }
            } else if (colors[neighbor] == colors[node]) {
                return false;
            }
        }

        return true;
    }
}
