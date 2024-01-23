package search;

import java.util.Arrays;

public class BoyerMoore {
    // 文字列の長さ
    private static final int STRING_SIZE = 65536;

    public static void main(final String[] args) {
        // コマンドライン引数から検索対象のテキストとパターンを取得
        if (args.length < 2) {
            System.out.println("テキストとパターンを指定してください。");
            return;
        }
        final var text = args[0];
        final var pattern = args[1];

        // パターンがテキスト中に存在するかどうかをチェック
        final var patternIndex = BoyerMoore.search(text, pattern);
        if (patternIndex != -1) {
            System.out.println("パターンが見つかりました: " + patternIndex + "番目の位置");
        } else {
            System.out.println("パターンが見つかりませんでした。");
        }
    }

    // ボイヤー・ムーア法による文字列探索
    public static int search(final String text, final String pattern) {
        final var shiftTable = new int[BoyerMoore.STRING_SIZE];
        Arrays.fill(shiftTable, -1);

        // パターン中の各文字の最後の出現位置を記録
        for (var i = 0; i < pattern.length(); i++) {
            shiftTable[pattern.charAt(i)] = i;
        }

        // テキストをスキャン
        final var m = pattern.length();
        final var n = text.length();
        int skip;
        for (var i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (var j = m - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - shiftTable[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return -1; // パターンが見つからなかった場合
    }
}
