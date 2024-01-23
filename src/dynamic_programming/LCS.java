package dynamic_programming;

import java.util.Scanner;

public class LCS {

    public static void main(final String[] args) {
        // 標準入力から2つの文字列を受け取る
        final var sc = new Scanner(System.in);
        System.out.println("最長共通部分列(LCS)を求めるプログラムです。");
        System.out.println("1つ目の文字列を入力してください。");
        final var s1 = sc.nextLine();
        System.out.println("2つ目の文字列を入力してください。");
        final var s2 = sc.nextLine();
        sc.close();

        // LCSを求める
        final var m = s1.length(); // 1つ目の文字列の長さ
        final var n = s2.length(); // 2つ目の文字列の長さ
        final var dp = new int[m + 1][n + 1]; // LCSの長さを記録する二次元配列
        // dp[i][j]は、s1の先頭i文字とs2の先頭j文字のLCSの長さを表す

        // dpを埋める
        for (var i = 0; i <= m; i++) {
            for (var j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    // どちらかの文字列が空の場合、LCSの長さは0
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // 両方の文字列の末尾の文字が同じ場合、LCSの長さは1増える
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 両方の文字列の末尾の文字が異なる場合、LCSの長さは、
                    // 1つ目の文字列を1文字減らした場合と、
                    // 2つ目の文字列を1文字減らした場合の、
                    // いずれか大きい方になる
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // LCSの長さを出力する
        System.out.println("LCSの長さは" + dp[m][n] + "です。");

        // LCSの内容を求める
        final var sb = new StringBuilder(); // LCSを格納する文字列ビルダー
        var i = m; // 1つ目の文字列のインデックス
        var j = n; // 2つ目の文字列のインデックス
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // 両方の文字列の末尾の文字が同じ場合、LCSに含まれる
                sb.append(s1.charAt(i - 1)); // LCSに文字を追加する
                i--; // 1つ目の文字列のインデックスを減らす
                j--; // 2つ目の文字列のインデックスを減らす
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // 1つ目の文字列を1文字減らした場合の方がLCSの長さが大きい場合、
                // 1つ目の文字列のインデックスを減らす
                i--;
            } else {
                // 2つ目の文字列を1文字減らした場合の方がLCSの長さが大きい場合、
                // 2つ目の文字列のインデックスを減らす
                j--;
            }
        }

        // LCSの内容を出力する
        System.out.println("LCSの内容は" + sb.reverse() + "です。");
    }
}
