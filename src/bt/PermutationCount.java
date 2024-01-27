package bt;

import java.util.Scanner;

public class PermutationCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("順列の要素数を入力してください: ");
        int n = scanner.nextInt();
        System.out.print("順列の長さを入力してください: ");
        int r = scanner.nextInt();
        int count = calculatePermutationCount(n, r);
        System.out.println("順列の場合の数: " + count);
    }

    // 順列の場合の数を計算するメソッド
    public static int calculatePermutationCount(int n, int r) {
        if (n < r) {
            return 0;
        }
        int count = 1;
        for (int i = 0; i < r; i++) {
            count *= (n - i);
        }
        return count;
    }
}
