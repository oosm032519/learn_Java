package search;

public class NaiveSearch {
    public static void main(final String[] args) {
        // コマンドライン引数から検索対象のテキストとパターンを取得
        if (args.length < 2) {
            System.out.println("テキストとパターンを指定してください。");
            return;
        }
        final var text = args[0];
        final var pattern = args[1];

        // パターンがテキスト中に存在するかどうかをチェック
        final var patternIndex = NaiveSearch.search(text, pattern);
        if (patternIndex != -1) {
            System.out.println("パターンが見つかりました: " + patternIndex + "番目の位置");
        } else {
            System.out.println("パターンが見つかりませんでした。");
        }
    }

    // ナイーブ法による文字列探索
    public static int search(final String text, final String pattern) {
        final var m = text.length();
        final var n = pattern.length();

        // テキストをスキャン
        for (var i = 0; i <= m - n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == n) { // パターンが見つかった場合
                return i;
            }
        }
        return -1; // パターンが見つからなかった場合
    }
}
