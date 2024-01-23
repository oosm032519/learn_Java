package search;

import java.util.HashMap;

public class HashTableSearch {
    // ハッシュ探索法を実装したメソッド
    public static Integer hashSearch(final HashMap<Integer, Integer> hashMap, final int target) {
        // ハッシュマップから探す値を探す
        return hashMap.get(target);
    }

    public static void main(final String[] args) {
        // コマンドライン引数の数をチェックする
        if (args.length < 2) {
            System.out.println("配列と探す値を指定してください。");
            System.exit(0); // プログラムを正常に終了する
        }

        try {
            // 配列と探す値を取得する
            final var hashMap = HashTableSearch.getHashMap(args); // ハッシュマップを取得するメソッドを呼び出す
            final var target = HashTableSearch.getTarget(args); // 探す値を取得するメソッドを呼び出す

            // ハッシュ探索法で配列から探す値を探す
            final var index = HashTableSearch.hashSearch(hashMap, target); // ハッシュ探索法のメソッドを呼び出す

            // 結果を表示する
            HashTableSearch.showResult(target, index); // 結果を表示するメソッドを呼び出す
        } catch (final NumberFormatException e) {
            // コマンドライン引数が整数に変換できない場合
            System.out.println("配列と探す値は整数で指定してください。");
            System.exit(0); // プログラムを正常に終了する
        } catch (final IllegalArgumentException e) {
            // 配列が空の場合
            System.out.println("配列には少なくとも1つの要素が必要です。");
            System.exit(0); // プログラムを正常に終了する
        }
    }

    // ハッシュマップを取得するメソッド
    public static HashMap<Integer, Integer> getHashMap(final String[] args) throws NumberFormatException {
        final var hashMap = new HashMap<Integer, Integer>(); // 最後の引数は探す値なので除く
        for (var i = 0; i < args.length - 1; i++) {
            hashMap.put(Integer.parseInt(args[i]), i); // 文字列を整数に変換し、その値とインデックスをハッシュマップに追加する
        }
        if (hashMap.isEmpty()) {
            // ハッシュマップが空の場合、例外をスローする
            throw new IllegalArgumentException();
        }
        return hashMap;
    }

    // 探す値を取得するメソッド
    public static int getTarget(final String[] args) throws NumberFormatException {
        return Integer.parseInt(args[args.length - 1]);
    }

    // 結果を表示するメソッド
    public static void showResult(final int target, final Integer index) {
        if (index == null) {
            System.out.println(target + "は配列にありません。");
        } else {
            System.out.println(target + "は配列の" + (index + 1) + "番目にあります。");
        }
    }
}
