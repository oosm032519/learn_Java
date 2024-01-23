package graph;

import java.util.Arrays;
import java.util.Scanner;

public class MST {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(final String[] args) {
        try (var scanner = new Scanner(System.in)) {
            System.out.print("エッジの数を入力してください: ");
            final var edges = scanner.nextInt();
            final var graph = new int[edges][edges];

            // グラフの初期化
            for (final int[] row : graph) {
                Arrays.fill(row, MST.INF);
            }

            System.out.println("各エッジの2つのノードとその重みを入力してください:");
            for (var i = 0; i < edges; i++) {
                final var u = scanner.nextInt();
                final var v = scanner.nextInt();
                final var w = scanner.nextInt();
                graph[u][v] = graph[v][u] = w;
            }

            MST.primMST(graph);
        }
    }

    private static void primMST(final int[][] graph) {
        final var V = graph.length;
        final var parent = new int[V];
        final var key = new int[V];
        final var mstSet = new boolean[V];

        for (var i = 0; i < V; i++) {
            key[i] = MST.INF;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (var count = 0; count < V - 1; count++) {
            final var u = MST.minKey(key, mstSet);
            mstSet[u] = true;

            for (var v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        MST.printMST(parent, graph);
    }

    private static int minKey(final int[] key, final boolean[] mstSet) {
        int min = MST.INF, minIndex = -1;

        for (var v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static void printMST(final int[] parent, final int[][] graph) {
        System.out.println("エッジ \t重み");
        for (var i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }
}
