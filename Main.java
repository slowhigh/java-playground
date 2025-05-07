import java.io.*;
import java.util.*;

public class Main {
    private static int[] colors;
    private static List<Integer>[] graph;

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

            boolean result = true;
            colors = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0 && !dfs(i, 1)) {
                    result = false;
                    break;
                }
            }
            sb.append(result ? "YES\n" : "NO\n");
        }

        System.out.print(sb.toString());
    }

    private static boolean dfs(int node, int color) {
        colors[node] = -color;
        for (int next : graph[node]) {
            if (colors[next] != 0 && colors[next] == colors[node])
                return false;
            if (colors[next] == 0 && !dfs(next, colors[node]))
                return false;
        }
        return true;
    }
}
