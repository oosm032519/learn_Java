package search;

public class BinarySearch {
    // 二分探索法を実装したメソッド
    public static int binarySearch(int[] array, int target) {
        // 探索範囲の左端と右端のインデックスを初期化する
        int left = 0;
        int right = array.length - 1;

        // 探索範囲がなくなるまで繰り返す
        while (left <= right) {
            // 探索範囲の中央のインデックスを計算する
            int mid = (left + right) / 2;
            // 中央の要素と探す値を比較する
            if (array[mid] == target) {
                // 一致したら、そのインデックスを返す
                return mid;
            } else if (array[mid] < target) {
                // 中央の要素より探す値が大きい場合、左端を中央の右隣に移動する
                left = mid + 1;
            } else {
                // 中央の要素より探す値が小さい場合、右端を中央の左隣に移動する
                right = mid - 1;
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

            // 二分探索法で配列から探す値を探す
            int index = binarySearch(array, target); // 二分探索法のメソッドを呼び出す

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
