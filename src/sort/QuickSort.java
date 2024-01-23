package sort;

// Sortインターフェースを実装するクラス
public class QuickSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の最初と最後のインデックスを渡して、クイックソートを実行する
        quickSort(array, 0, array.length - 1);
    }

    // クイックソートを実行するメソッド
    private void quickSort(int[] array, int left, int right) {
        // 左端のインデックスが右端のインデックスより小さい場合
        if (left < right) {
            // 基準値を決めて、配列を分割する
            int pivot = partition(array, left, right);

            // 左側の部分配列に対して、再帰的にクイックソートを実行する
            quickSort(array, left, pivot - 1);

            // 右側の部分配列に対して、再帰的にクイックソートを実行する
            quickSort(array, pivot + 1, right);
        }
    }

    // 配列を分割するメソッド
    private int partition(int[] array, int left, int right) {
        // 配列の右端の要素を基準値とする
        int pivot = array[right];

        // 基準値より小さい要素のインデックスを記録する変数
        int i = left - 1;

        // 配列の左端から右端の一つ前までループする
        for (int j = left; j < right; j++) {
            // 配列の要素が基準値以下なら
            if (array[j] <= pivot) {
                // iをインクリメントする
                i++;

                // i番目とj番目の要素を入れ替える
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // i+1番目と右端の要素を入れ替える
        int temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;

        // 基準値のインデックスを返す
        return i + 1;
    }
}
