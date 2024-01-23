package sort;

// Sortインターフェースを実装するクラス
public class InsertionSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の長さを取得
        int n = array.length;

        // 配列の要素を順番に整列済みの部分に挿入する
        for (int i = 1; i < n; i++) {
            // 挿入する要素を取得
            int key = array[i];
            // 整列済みの部分の最後のインデックスを取得
            int j = i - 1;
            // 整列済みの部分を後ろから前に向かって走査する
            while (j >= 0 && array[j] > key) {
                // 挿入する要素より大きい要素は右にずらす
                array[j + 1] = array[j];
                j--;
            }
            // 挿入する要素を適切な位置に挿入する
            array[j + 1] = key;
        }
    }
}
