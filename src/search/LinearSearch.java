package search;

public class LinearSearch {
    // 線形探索法を実装したメソッド
    public static int linearSearch(int[] array, int target) {
        // 配列の要素を先頭から順に調べる
        for (int i = 0; i < array.length; i++) {
            // 要素が探す値と一致したら、そのインデックスを返す
            if (array[i] == target) {
                return i;
            }
        }
        // 見つからなかった場合、-1を返す
        return -1;
    }

    public static void main(String[] args) {
        // コマンドライン引数の数をチェックする
        if (args.length < 2) {
            System.out.println("配列と探す値を指定してください。");
            System.exit(0); // プログラムを正常に終了する
        }

        try {
            // 配列と探す値を取得する
            int[] array = getArray(args); // 配列を取得するメソッドを呼び出す
            int target = getTarget(args); // 探す値を取得するメソッドを呼び出す

            // 線形探索法で配列から探す値を探す
            int index = linearSearch(array, target); // 線形探索法のメソッドを呼び出す

            // 結果を表示する
            showResult(target, index); // 結果を表示するメソッドを呼び出す
        } catch (NumberFormatException e) {
            // コマンドライン引数が整数に変換できない場合
            System.out.println("配列と探す値は整数で指定してください。");
            System.exit(0); // プログラムを正常に終了する
        } catch (IllegalArgumentException e) {
            // 配列が空の場合
            System.out.println("配列には少なくとも1つの要素が必要です。");
            System.exit(0); // プログラムを正常に終了する
        }
    }

    // 配列を取得するメソッド
    public static int[] getArray(String[] args) throws NumberFormatException {
        int[] array = new int[args.length - 1]; // 最後の引数は探す値なので除く
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(args[i]); // 文字列を整数に変換する
        }
        if (array.length == 0) {
            // 配列が空の場合、例外をスローする
            throw new IllegalArgumentException();
        }
        return array;
    }

    // 探す値を取得するメソッド
    public static int getTarget(String[] args) throws NumberFormatException {
        return Integer.parseInt(args[args.length - 1]);
    }

    // 結果を表示するメソッド
    public static void showResult(int target, int index) {
        if (index == -1) {
            System.out.println(target + "は配列にありません。");
        } else {
            System.out.println(target + "は配列の" + (index + 1) + "番目にあります。");
        }
    }
}
