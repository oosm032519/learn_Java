package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    // グラフの隣接行列を表す二次元配列
    static int[][] graph = {
            {0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 1},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0}
    };

    // ノードの数
    static int n = BFS.graph.length;

    // BFSを実行するメソッド
    static void bfs(final int start, final int end) {
        // 訪問済みのノードを記録する配列
        final var visited = new boolean[BFS.n];
        // 探索するノードを格納するキュー
        final Queue<Integer> queue = new LinkedList<>();
        // 前のノードを記録する配列
        final var prev = new int[BFS.n];
        // 配列を初期化
        Arrays.fill(visited, false);
        Arrays.fill(prev, -1);
        // 始点をキューに追加
        queue.add(start);
        // 始点を訪問済みにする
        visited[start] = true;
        // キューが空になるまで繰り返す
        while (!queue.isEmpty()) {
            // キューからノードを取り出す
            final int node = queue.poll();
            // 終点に到達したら終了
            if (node == end) {
                break;
            }
            // 隣接するノードを探索する
            for (var i = 0; i < BFS.n; i++) {
                // 未訪問かつ辺がある場合
                if (!visited[i] && BFS.graph[node][i] == 1) {
                    // キューに追加
                    queue.add(i);
                    // 訪問済みにする
                    visited[i] = true;
                    // 前のノードを記録
                    prev[i] = node;
                }
            }
        }
        // 経路を復元する
        final List<Integer> path = new ArrayList<>();
        // 終点から始点まで遡る
        var node = end;
        while (node != -1) {
            // 経路に追加
            path.add(node);
            // 前のノードに移動
            node = prev[node];
        }
        // 経路が存在するか判定
        if (path.getLast() == start) {
            // 経路を逆順にして表示
            Collections.reverse(path);
            System.out.println("Path: " + path);
        } else {
            // 経路が存在しない場合
            System.out.println("No path found");
        }
    }

    public static void main(final String[] args) {
        // コマンドライン引数を整数に変換
        final var start = Integer.parseInt(args[0]);
        final var end = Integer.parseInt(args[1]);
        // BFSを実行
        BFS.bfs(start, end);
    }
}
