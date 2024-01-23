package sort;

import java.util.Arrays;
import java.util.Random;

public class Main {

    // ソートする配列のサイズ
    public static final int ARRAY_SIZE = 10000;

    // 配列の要素の範囲
    public static final int MAX_VALUE = 10000;

    // 各ソートアルゴリズムの実行回数
    public static final int RUNS = 10;

    // 乱数生成器
    public static final Random random = new Random();

    public static void main(String[] args) {
        // ソートアルゴリズムの配列
        Sort[] sorts = new Sort[]{
                new BubbleSort(),
                new HeapSort(),
                new InsertionSort(),
                new MergeSort(),
                new QuickSort(),
                new SelectionSort(),
                new ShellSort()
        };

        // 各ソートアルゴリズムの平均時間を格納する配列
        long[] times = new long[sorts.length];

        // 各ソートアルゴリズムについて
        for (int i = 0; i < sorts.length; i++) {
            // ソートアルゴリズムの名前を表示
            System.out.println(sorts[i].getClass().getSimpleName() + ":");

            // 実行回数分繰り返す
            for (int j = 0; j < RUNS; j++) {
                // ランダムな配列を生成
                int[] array = generateRandomArray(ARRAY_SIZE, MAX_VALUE);

                // 配列のコピーを作成
                int[] copy = array.clone();

                // 配列をソートする前の時刻を記録
                long start = System.currentTimeMillis();

                // 配列をソート
                sorts[i].sort(array);

                // 配列をソートした後の時刻を記録
                long end = System.currentTimeMillis();

                // ソートにかかった時間を計算
                long time = end - start;

                // ソートの正しさを検証
                Arrays.sort(copy);
                if (!Arrays.equals(array, copy)) {
                    System.out.println("Error: incorrect sort");
                    return;
                }

                // ソートにかかった時間を表示
                System.out.println("Run " + (j + 1) + ": " + time + " ms");

                // ソートにかかった時間を累積
                times[i] += time;
            }

            // 平均時間を計算
            times[i] /= RUNS;

            // 空行を出力
            System.out.println();
        }

        // 各ソートアルゴリズムの平均時間を表示
        System.out.println("Average times:");
        for (int i = 0; i < sorts.length; i++) {
            System.out.println(sorts[i].getClass().getSimpleName() + ": " + times[i] + " ms");
        }
    }

    // ランダムな配列を生成するメソッド
    public static int[] generateRandomArray(int size, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue);
        }
        return array;
    }
}
