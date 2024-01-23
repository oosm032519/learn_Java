package sort;

// Sortインターフェースを実装するクラス
public class SelectionSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の長さを取得
        int n = array.length;

        // 配列の要素を順番に選択する
        for (int i = 0; i < n - 1; i++) {
            // 最小の要素のインデックスを記録する変数
            int minIndex = i;

            // 選択した要素より後ろの要素を順番に比較する
            for (int j = i + 1; j < n; j++) {
                // より小さい要素が見つかったら、そのインデックスを更新する
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // 最小の要素と選択した要素を入れ替える
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
