package search;

public class KMP {
    public static void main(final String[] args) {
        // コマンドライン引数から検索対象のテキストとパターンを取得
        if (args.length < 2) {
            System.out.println("テキストとパターンを指定してください。");
            return;
        }
        final var text = args[0];
        final var pattern = args[1];

        // パターンがテキスト中に存在するかどうかをチェック
        final var patternIndex = KMP.search(text, pattern);
        if (patternIndex != -1) {
            System.out.println("パターンが見つかりました: " + patternIndex + "番目の位置");
        } else {
            System.out.println("パターンが見つかりませんでした。");
        }
    }

    // 最長接頭辞テーブルを計算
    public static int[] computeLSPTable(final String pattern) {
        final var lsp = new int[pattern.length()];
        lsp[0] = 0; // 常に0
        for (var i = 1; i < pattern.length(); i++) {
            // 最長接頭辞の終わりを追跡
            var j = lsp[i - 1];
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lsp[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            lsp[i] = j;
        }
        return lsp;
    }

    // クヌース–モリス–プラット法による文字列探索
    public static int search(final String text, final String pattern) {
        final var lsp = KMP.computeLSPTable(pattern);

        var j = 0; // パターンのインデックス
        for (var i = 0; i < text.length(); i++) { // テキストのインデックス
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                // ミスマッチが発生した場合、LSPテーブルを使用して次のインデックスを決定
                j = lsp[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                // 文字が一致した場合、パターンの次の文字をチェック
                j++;
                if (j == pattern.length()) {
                    // パターン全体が一致した場合、一致を報告
                    return i - (j - 1);
                }
            }
        }
        return -1; // パターンが見つからなかった場合
    }
}
