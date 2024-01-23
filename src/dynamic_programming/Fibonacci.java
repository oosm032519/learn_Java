package dynamic_programming;

import java.util.Scanner;

public class Fibonacci {

    // フィボナッチ数列のn番目の項を再帰的に求めるメソッド
    public static int fib(final int n) {
        // nが0または1の場合はnを返す
        if (n == 0 || n == 1) {
            return n;
        }
        // それ以外の場合は、直前の2項の和を返す
        return Fibonacci.fib(n - 1) + Fibonacci.fib(n - 2);
    }

    public static void main(final String[] args) {
        // 標準入力からnを読み込む
        final var sc = new Scanner(System.in);
        System.out.print("n = ");
        final var n = sc.nextInt();
        sc.close();

        // フィボナッチ数列のn番目の項を求める
        final var result = Fibonacci.fib(n);

        // 結果を出力する
        System.out.println("Fibonacci(" + n + ") = " + result);
    }
}
