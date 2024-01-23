package sort;

// Sortインターフェースを実装するクラス
public class HeapSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の長さを取得
        int n = array.length;

        // 配列をヒープに変換する
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // ヒープから最大値を取り出して配列の後ろに移動する
        for (int i = n - 1; i > 0; i--) {
            // 配列の先頭と末尾を入れ替える
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // 配列の先頭からi-1番目までをヒープに再構築する
            heapify(array, i, 0);
        }
    }

    // 配列の一部分をヒープにする補助メソッド
    private void heapify(int[] array, int n, int i) {
        // 親ノードのインデックス
        int parent = i;
        // 左の子ノードのインデックス
        int left = 2 * i + 1;
        // 右の子ノードのインデックス
        int right = 2 * i + 2;

        // 左の子ノードが存在し、親ノードより大きければ
        if (left < n && array[left] > array[parent]) {
            // 親ノードのインデックスを左の子ノードに更新する
            parent = left;
        }

        // 右の子ノードが存在し、親ノードより大きければ
        if (right < n && array[right] > array[parent]) {
            // 親ノードのインデックスを右の子ノードに更新する
            parent = right;
        }

        // 親ノードのインデックスが変わった場合
        if (parent != i) {
            // 親ノードと子ノードを入れ替える
            int temp = array[i];
            array[i] = array[parent];
            array[parent] = temp;

            // 入れ替えた子ノードを根とする部分ヒープに対して再帰的に処理する
            heapify(array, n, parent);
        }
    }
}
