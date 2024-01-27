package bt;

import java.util.Scanner;

public class PermutationCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("順列の要素数を入力してください: ");
            int n = scanner.nextInt();
            System.out.print("順列の長さを入力してください: ");
            int r = scanner.nextInt();
            int count = calculatePermutationCount(n, r);
            System.out.println("順列の場合の数: " + count);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 順列の場合の数を計算するメソッド
    public static int calculatePermutationCount(int n, int r) {
        if (n < 0 || r < 0) {
            throw new IllegalArgumentException("n and r must be non-negative.");
        }
        if (n < r) {
            throw new IllegalArgumentException("n must be greater than or equal to r.");
        }
        int count = 1;
        for (int i = 0; i < r; i++) {
            count *= (n - i);
        }
        return count;
    }
}
