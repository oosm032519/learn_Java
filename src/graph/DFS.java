package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

//深さ優先探索のクラスを定義する
public class DFS {

    // グラフの頂点数
    private final int V;

    // グラフの隣接リスト
    private final ArrayList<LinkedList<Integer>> adj;

    // コンストラクタでグラフを初期化する
    public DFS(final int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (var i = 0; i < v; i++) {
            adj.add(new LinkedList<>());
        }
    }

    // グラフに辺を追加するメソッド
    public void addEdge(final int v, final int w) {
        adj.get(v).add(w);
    }

    // 指定された頂点から深さ優先探索を行うメソッド
    public void dfs(final int s) {
        // 訪問済みの頂点を記録する配列
        final var visited = new boolean[V];

        // 再帰的に探索する補助メソッドを呼び出す
        dfsUtil(s, visited);
    }

    // 再帰的に探索する補助メソッド
    public void dfsUtil(final int v, final boolean[] visited) {
        // 現在の頂点を訪問済みとしてマークする
        visited[v] = true;

        // 現在の頂点を出力する
        System.out.print(v + " ");

        // 現在の頂点に隣接する頂点について
        for (final int w : adj.get(v)) {
            // まだ訪問していない場合は再帰的に探索する
            if (!visited[w]) {
                dfsUtil(w, visited);
            }
        }
    }

    // メインメソッド
    public static void main(final String[] args) {
        // Scannerオブジェクトを作成する
        final var sc = new Scanner(System.in);

        // グラフの頂点数を入力する
        System.out.println("グラフの頂点数を入力してください。");
        final var V = sc.nextInt();

        // DFSオブジェクトを作成する
        final var dfs = new DFS(V);

        // グラフの辺数を入力する
        System.out.println("グラフの辺数を入力してください。");
        final var E = sc.nextInt();

        // グラフの各辺の両端の頂点を入力する
        System.out.println("グラフの各辺の両端の頂点を入力してください。");
        for (var i = 0; i < E; i++) {
            final var v = sc.nextInt();
            final var w = sc.nextInt();
            dfs.addEdge(v, w);
        }

        // 深さ優先探索の始点を入力する
        System.out.println("深さ優先探索の始点を入力してください。");
        final var s = sc.nextInt();

        // 深さ優先探索を実行する
        System.out.println("深さ優先探索の結果は以下の通りです。");
        dfs.dfs(s);

        // Scannerオブジェクトを閉じる
        sc.close();
    }
}
