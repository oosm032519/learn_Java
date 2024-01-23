package sort;

// Sortインターフェースを実装するクラス
public class ShellSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の長さを取得
        int n = array.length;

        // 間隔を初期化
        int gap = n / 2;

        // 間隔が1になるまで繰り返す
        while (gap > 0) {
            // 間隔分だけずらして比較する
            for (int i = gap; i < n; i++) {
                // 比較する要素を取得
                int temp = array[i];
                // 挿入する位置を探す
                int j = i - gap;
                // 配列の前方に向かって比較していく
                while (j >= 0 && array[j] > temp) {
                    // 大きい要素を後ろにずらす
                    array[j + gap] = array[j];
                    // さらに前の要素を比較する
                    j = j - gap;
                }
                // 挿入する位置に要素を入れる
                array[j + gap] = temp;
            }
            // 間隔を半分にする
            gap = gap / 2;
        }
    }
}
