package dynamic_programming;

import java.util.Scanner;

public class SubsetSum {

    // 部分和問題を解くメソッド
    public static boolean solve(final int[] set, final int n, final int target) {
        // nが0になったら、targetが0ならtrue、そうでなければfalseを返す
        if (n == 0) {
            return target == 0;
        }
        // set[n-1]を選ばない場合と選ぶ場合の両方を試す
        return SubsetSum.solve(set, n - 1, target) || SubsetSum.solve(set, n - 1, target - set[n - 1]);
    }

    public static void main(final String[] args) {
        try (// 標準入力からデータを読み込むためのScannerオブジェクトを作成
             var sc = new Scanner(System.in)) {
            // 整数の個数と目標値を入力してもらう
            System.out.println("整数の個数を入力してください。");
            final var n = sc.nextInt();
            System.out.println("目標値を入力してください。");
            final var target = sc.nextInt();

            // 整数の集合を入力してもらう
            System.out.println("整数の集合を空白区切りで入力してください。");
            final var set = new int[n];
            for (var i = 0; i < n; i++) {
                set[i] = sc.nextInt();
            }

            // 部分和問題を解いて結果を出力する
            final var result = SubsetSum.solve(set, n, target);
            if (result) {
                System.out.println("部分和問題は解けます。");
            } else {
                System.out.println("部分和問題は解けません。");
            }
        }
    }
}
