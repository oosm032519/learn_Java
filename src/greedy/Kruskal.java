package greedy;

import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class subset {
    int parent, rank;
}

public class Kruskal {
    int V, E;
    Edge[] edge;

    Kruskal(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("頂点の数を入力してください:");
            int V = scanner.nextInt();
            System.out.println("エッジの数を入力してください:");
            int E = scanner.nextInt();

            Kruskal graph = new Kruskal(V, E);

            System.out.println("各エッジの重みを入力してください (頂点iと頂点jの間にエッジがない場合は0を入力):");
            for (int i = 0; i < E; i++) {
                graph.edge[i].src = scanner.nextInt();
                graph.edge[i].dest = scanner.nextInt();
                graph.edge[i].weight = scanner.nextInt();
            }

            graph.KruskalMST();
        } catch (Exception e) {
            System.out.println("エラーが発生しました: " + e.getMessage());
        }
    }

    int find(subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    void Union(subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void KruskalMST() {
        Edge[] result = new Edge[V];
        int e = 0;
        int i;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge);

        subset[] subsets = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge;
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }

        int totalWeight = 0;
        for (i = 0; i < e; ++i) {
            totalWeight += result[i].weight;
        }
        System.out.println("最小全域木の重みの合計: " + totalWeight);
    }
}
