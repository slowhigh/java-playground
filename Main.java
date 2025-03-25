import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        int[] info1 = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
        int[][] edges1 = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
                { 4, 6 }, { 8, 9 } };
        int res1 = 5;

        int[] info2 = { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 };
        int[][] edges2 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 },
                { 9, 10 } };
        int res2 = 5;

        int[] info3 = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0 };
        int[][] edges3 = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
                { 4, 6 }, { 8, 9 }, { 11, 12 }, { 12, 13 }, { 5, 14 }, { 14, 15 }, { 3, 16 } };
        int res3 = 9;

        int[] info4 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int[][] edges4 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 3, 8 }, { 4, 9 },
                { 4, 10 }, { 5, 11 }, { 5, 12 }, { 6, 13 }, { 6, 14 }, { 7, 15 }, { 7, 16 } };
        int res4 = 17;

        Solution s = new Solution();
        if (s.solution(info1, edges1) == res1) {
            System.out.println("PASS-1");
        } else {
            System.out.println("FAIL-1");
        }

        if (s.solution(info2, edges2) == res2) {
            System.out.println("PASS-2");
        } else {
            System.out.println("FAIL-2");
        }

        if (s.solution(info3, edges3) == res3) {
            System.out.println("PASS-3");
        } else {
            System.out.println("FAIL-3");
        }

        if (s.solution(info4, edges4) == res4) {
            System.out.println("PASS-4");
        } else {
            System.out.println("FAIL-4");
        }
    }
}

class Solution {
    private int[] gInfo;
    private List<List<Integer>> gNode;
    private int maxSheep;
    private boolean[][][] visited;

    public int solution(int[] info, int[][] edges) {
        gInfo = info;
        gNode = new ArrayList<>();
        maxSheep = 0;
        visited = new boolean[17][18][18];

        for (int i = 0; i < info.length; i++)
            gNode.add(new ArrayList<>());

        for (int[] edge : edges)
            gNode.get(edge[0]).add(edge[1]);

        dfs(0, 0, 0, new ArrayList<>());

        return maxSheep;
    }

    private void dfs(int pos, int sheep, int wolf, List<Integer> node) {
        visited[pos][sheep][wolf] = true;

        if (gInfo[pos] == 0) {
            sheep++;
            maxSheep = Math.max(maxSheep, sheep);
        } else {
            wolf++;
        }

        if (wolf >= sheep)
            return;

        node.addAll(gNode.get(pos));
        for (Integer next : node) {
            List<Integer> newStack = new ArrayList<>(node);
            newStack.remove(next);
            if (!visited[next.intValue()][sheep][wolf])
                dfs(next.intValue(), sheep, wolf, newStack);
        }
    }
}