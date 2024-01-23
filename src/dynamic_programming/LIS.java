package dynamic_programming;

import java.util.Scanner;

public class LIS {
    // 数列のLISの長さを返す関数
    public static int lis(final int[] arr) {
        // 数列の長さ
        final var n = arr.length;
        // LISの長さを格納する配列
        final var lis = new int[n];
        // 最初の要素のLISの長さは1
        lis[0] = 1;
        // 最大のLISの長さを記録する変数
        var max = 1;
        // 2番目の要素から順にLISの長さを求める
        for (var i = 1; i < n; i++) {
            // 自分より前の要素の中で最大のLISの長さを記録する変数
            var maxLis = 0;
            // 自分より前の要素を走査する
            for (var j = 0; j < i; j++) {
                // 自分より小さい要素があれば、そのLISの長さを比較する
                if (arr[j] < arr[i]) {
                    maxLis = Math.max(maxLis, lis[j]);
                }
            }
            // 自分のLISの長さは、自分より小さい要素のLISの長さに1を足したもの
            lis[i] = maxLis + 1;
            // 最大のLISの長さを更新する
            max = Math.max(max, lis[i]);
        }
        // 最大のLISの長さを返す
        return max;
    }

    public static void main(final String[] args) {
        // 標準入力から数列を読み込む
        final var sc = new Scanner(System.in);
        System.out.println("数列の長さを入力してください。");
        final var n = sc.nextInt();
        final var arr = new int[n];
        System.out.println("数列の要素を空白区切りで入力してください。");
        for (var i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();
        // LISの長さを求める
        final var result = LIS.lis(arr);
        // 結果を出力する
        System.out.println("数列の最長増加部分列の長さは " + result + " です。");
    }
}
