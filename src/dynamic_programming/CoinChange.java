package dynamic_programming;

import java.util.Scanner;

public class CoinChange {

    public static void main(final String[] args) {
        // コインの種類と金額を定義する
        final int[] coins = {500, 100, 50, 10, 5, 1}; // コインの種類
        int amount; // 金額
        var count = 0; // 引いた回数の合計
        final var counts = new int[coins.length]; // コインの種類ごとの枚数を格納する配列

        try (// ユーザーに支払いたい金額を入力してもらう
             var sc = new Scanner(System.in)) {
            System.out.print("支払いたい金額を入力してください: ");
            amount = sc.nextInt();
        }
        // 支払いたい金額から、コインの種類の中で最も大きいものを引くことを繰り返す
        for (var i = 0; i < coins.length; i++) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
                counts[i]++; // コインの種類ごとの枚数をインクリメントする
            }
        }

        // 引いた回数の合計とコインの種類ごとの枚数を出力する
        System.out.println("最も少ない枚数は" + count + "枚です。");
        for (var i = 0; i < coins.length; i++) {
            System.out.println(coins[i] + "円玉は" + counts[i] + "枚です。");
        }
    }
}
