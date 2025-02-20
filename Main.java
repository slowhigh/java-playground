import java.io.*;
import java.util.*;

// 5 6  // V E
// 1    // K
// 5 1 1
// 1 2 2
// 1 3 3
// 2 3 4
// 2 4 5
// 3 4 6

// 5 6
// 1
// 5 1 1
// 1 2 2
// 1 3 3
// 2 3 4
// 2 4 5
// 3 4 6

// 0
// 2
// 3
// 7
// INF

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ve = br.readLine().split(" ");
        int V = Integer.parseInt(ve[0]);
        int E = Integer.parseInt(ve[1]);
        int K = Integer.parseInt(br.readLine());

        Map<Integer, List<Edge>> edges = new HashMap<>();
        for (int i = 0; i < E; i++) {
            String[] e = br.readLine().split(" ");
            int u = Integer.parseInt(e[0]), v = Integer.parseInt(e[1]), w = Integer.parseInt(e[2]);

            if (!edges.containsKey(u))
                edges.put(u, new ArrayList<>());

            edges.get(u).add(new Edge(v, w));
        }

        int[] result = solution(V, K, edges);
        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r == Integer.MAX_VALUE ? "INF\n" : r + "\n");
        }

        System.out.println(sb.toString());
    }

    public static int[] solution(int V, int K, Map<Integer, List<Edge>> edges) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.dist - y.dist);
        pq.add(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.dist > dist[cur.to] || !edges.containsKey(cur.to))
                continue;

            for (Edge next : edges.get(cur.to)) {
                int newDist = cur.dist + next.dist;

                if (newDist >= dist[next.to])
                    continue;

                dist[next.to] = newDist;
                pq.add(new Edge(next.to, newDist));
            }
        }

        return Arrays.copyOfRange(dist, 1, V + 1);
    }
}

class Edge {
    int to, dist;

    public Edge(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}