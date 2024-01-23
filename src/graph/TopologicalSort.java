package graph;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSort {
    private final int V; // 頂点の数
    private final ArrayList<ArrayList<Integer>> adj; // 隣接リストでグラフを表す

    TopologicalSort(final int v) {
        V = v; // 頂点の数を初期化
        adj = new ArrayList<>(v); // 隣接リストを作成
        for (var i = 0; i < v; ++i) {
            adj.add(new ArrayList<>()); // 各頂点に対応する空のリストを追加
        }
    }

    void addEdge(final int v, final int w) {
        adj.get(v).add(w); // vからwへの辺を追加
    }

    void topologicalSortUtil(final int v, final boolean[] visited, final Stack<Integer> stack) {
        visited[v] = true; // vを訪問済みにする
        for (final Integer neighbor : adj.get(v)) { // vの隣接頂点に対して
            if (!visited[neighbor]) { // まだ訪問していないなら
                topologicalSortUtil(neighbor, visited, stack); // 再帰的に探索
            }
        }

        stack.push(v); // vをスタックに積む
    }

    void topologicalSort() {
        final var stack = new Stack<Integer>(); // トポロジカルソートの結果を格納するスタック

        final boolean[] visited = new boolean[V]; // 訪問済みの頂点を記録する配列
        for (var i = 0; i < V; i++) {
            visited[i] = false; // 最初は全て未訪問にする
        }

        for (var i = 0; i < V; i++) { // 全ての頂点に対して
            if (!visited[i]) { // まだ訪問していないなら
                topologicalSortUtil(i, visited, stack); // 探索を開始
            }
        }

        while (!stack.empty()) { // スタックが空になるまで
            System.out.print(stack.pop() + " "); // スタックの先頭を出力
        }
    }

    public static void main(final String[] args) {
        try (var scanner = new Scanner(System.in)) {
            System.out.println("Enter the number of vertices:"); // 頂点の数を入力
            final var v = scanner.nextInt();
            final var g = new TopologicalSort(v); // トポロジカルソートのオブジェクトを作成

            System.out.println("Enter the number of edges:"); // 辺の数を入力
            final var e = scanner.nextInt();
            System.out.println("Enter the edges (source destination):"); // 辺の情報を入力（始点と終点）
            for (var i = 0; i < e; i++) {
                final var source = scanner.nextInt();
                final var destination = scanner.nextInt();
                g.addEdge(source, destination); // 辺を追加
            }

            System.out.println("Following is a Topological sort of the given graph"); // トポロジカルソートの結果を出力
            g.topologicalSort();
        }
    }
}
