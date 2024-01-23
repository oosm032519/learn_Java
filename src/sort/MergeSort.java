package sort;

// Sortインターフェースを実装するクラス
public class MergeSort implements Sort {

    // ソートするメソッド
    public void sort(int[] array) {
        // 配列の長さを取得
        int n = array.length;

        // 配列の長さが1以下なら、ソートの必要がないので、そのまま返す
        if (n <= 1) {
            return;
        }

        // 配列を半分に分割する
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        System.arraycopy(array, 0, left, 0, mid);
        if (n - mid >= 0) System.arraycopy(array, mid, right, 0, n - mid);

        // 左半分と右半分をそれぞれソートする
        sort(left);
        sort(right);

        // ソート済みの部分配列をマージする
        merge(array, left, right);
    }

    // ソート済みの部分配列をマージするメソッド
    private void merge(int[] array, int[] left, int[] right) {
        // 左半分と右半分の要素数を取得
        int n1 = left.length;
        int n2 = right.length;

        // 左半分と右半分の要素を比較しながら、配列に入れる
        int i = 0; // 左半分のインデックス
        int j = 0; // 右半分のインデックス
        int k = 0; // 配列のインデックス
        while (i < n1 && j < n2) {
            // 左半分の要素が右半分の要素より小さいか等しい場合、配列に入れる
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                // 左半分の要素が右半分の要素より大きい場合、配列に入れる
                array[k] = right[j];
                j++;
            }
            k++;
        }

        // 左半分に余った要素があれば、配列に入れる
        while (i < n1) {
            array[k] = left[i];
            i++;
            k++;
        }

        // 右半分に余った要素があれば、配列に入れる
        while (j < n2) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
}
