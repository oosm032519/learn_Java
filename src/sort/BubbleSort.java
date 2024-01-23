package sort;

// Sortインターフェースを実装するクラス
public class BubbleSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の長さを取得
        int n = array.length;

        // 配列の要素を順番に比較して入れ替える
        for (int i = 0; i < n - 1; i++) {
            // 配列の後ろから前に向かって比較する
            for (int j = n - 1; j > i; j--) {
                // 隣り合う要素を比較して、左の方が大きければ入れ替える
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
