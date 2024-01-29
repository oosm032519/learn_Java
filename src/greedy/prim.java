package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class prim {
    public static void main(String[] args) {
        // Scannerを用いてユーザーからの入力を受け取る
        try (Scanner scanner = new Scanner(System.in)) {
            // 頂点の数をユーザーに入力させる
            System.out.println("頂点の数を入力してください:");
            int V = scanner.nextInt();

            // グラフを表す2次元配列を作成
            int[][] graph = new int[V][V];
            // 各エッジの重みをユーザーに入力させる
            System.out.println("各エッジの重みを入力してください (頂点iと頂点jの間にエッジがない場合は0を入力):");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }

            // プリムのアルゴリズムを用いて最小全域木を求める
            primMST(graph);
        } catch (Exception e) {
            // エラーが発生した場合はそのメッセージを表示
            System.out.println("エラーが発生しました: " + e.getMessage());
        }
    }

    // プリムのアルゴリズムを実装したメソッド
    static void primMST(int[][] graph) {
        int V = graph.length;
        int[] key = new int[V];
        Boolean[] mstSet = new Boolean[V];

        // 初期化
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;

        // 最小全域木を構築
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                }
            }
        }

        // 最小全域木の重みの合計を計算し、表示
        int totalWeight = Arrays.stream(key).sum();
        System.out.println("最小全域木の重みの合計: " + totalWeight);
    }

    // 最小キーを持つ頂点のインデックスを返すメソッド
    static int minKey(int[] key, Boolean[] mstSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < key.length; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }
}
