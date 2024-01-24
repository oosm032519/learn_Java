package recursion;

import java.util.Scanner;

public class Euclid {

    // ユークリッドの互除法を再帰的に実行するメソッド
    public static int gcd(int a, int b) {
        // bが0ならaが最大公約数
        if (b == 0) {
            return a;
        }
        // aをbで割った余りとbの最大公約数を求める
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        // 標準入力から2つの自然数を読み込む
        Scanner sc = new Scanner(System.in);
        System.out.println("2つの自然数を入力してください");
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        // ユークリッドの互除法を呼び出して最大公約数を求める
        int result = gcd(a, b);

        // 結果を出力する
        System.out.println(a + "と" + b + "の最大公約数は" + result + "です");
    }
}
