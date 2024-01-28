package bt;

import java.util.Scanner;

public class CombinationCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("組み合わせの要素数を入力してください: ");
            int n = scanner.nextInt();
            System.out.print("組み合わせの長さを入力してください: ");
            int r = scanner.nextInt();
            int count = calculateCombinationCount(n, r);
            System.out.println("組み合わせの場合の数: " + count);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 組み合わせの場合の数を計算するメソッド
    public static int calculateCombinationCount(int n, int r) {
        if (n < 0 || r < 0) {
            throw new IllegalArgumentException("n and r must be non-negative.");
        }
        if (n < r) {
            throw new IllegalArgumentException("n must be greater than or equal to r.");
        }
        int numerator = 1;
        for (int i = 0; i < r; i++) {
            numerator *= (n - i);
        }
        int denominator = 1;
        for (int i = 1; i <= r; i++) {
            denominator *= i;
        }
        return numerator / denominator;
    }
}
