import java.io.*;
import java.util.*;

public class Main {
    private static int[] root;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(A, B, C);
        }

        Arrays.sort(edge);

        root = new int[V + 1];
        for (int i = 1; i <= V; i++)
            root[i] = i;

        int sum = 0, count = 0;
        for (Edge edge : edges) {
            if (findRoot(edge.u) == findRoot(edge.v))
                continue;

            unionRoot(edge.u, edge.v);
            sum += edge.weight;
            count++;
            if (count == V - 1)
                break;
        }

        System.out.print(sum);
    }

    private static int findRoot(int node) {
        if (root[node] != node)
            root[node] = findRoot(root[node]);

        return root[node];
    }

    private static void unionRoot(int node1, int node2) {
        int root1 = findRoot(node1);
        int root2 = findRoot(node2);
        if (root1 != root2)
            root[root1] = root2;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
