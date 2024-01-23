package dynamic_programming;

public class Knapsack {
    public static void main(final String[] args) {
        // コマンドライン引数から入力を受け取る
        final var n = Integer.parseInt(args[0]); // 品物の数
        final var W = Integer.parseInt(args[1]); // ナップサックの容量
        final var w = new int[n]; // 品物の重さ
        final var v = new int[n]; // 品物の価値
        for (var i = 0; i < n; i++) {
            w[i] = Integer.parseInt(args[2 + i * 2]);
            v[i] = Integer.parseInt(args[3 + i * 2]);
        }

        // 動的計画法でナップサック問題を解く
        final var dp = new int[n + 1][W + 1]; // dp[i][j]は、i番目までの品物から重さの総和がj以下になるように選んだときの価値の総和の最大値
        for (var i = 0; i < n; i++) {
            for (var j = 0; j <= W; j++) {
                if (j < w[i]) {
                    // i番目の品物を入れられない場合
                    dp[i + 1][j] = dp[i][j];
                } else {
                    // i番目の品物を入れるか入れないかで価値の総和が大きくなる方を選ぶ
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
                }
            }
        }

        // 最終的な答えを出力する
        System.out.println("The maximum value is " + dp[n][W]);
    }
}
