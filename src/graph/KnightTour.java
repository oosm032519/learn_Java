package graph;

import java.util.Scanner;

public class KnightTour {

    // 盤のサイズ
    static int N;

    // ナイトの移動方向を表す配列
    static int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

    // ナイトの巡回の経路を表示するメソッド
    static void printSolution(final int[][] sol) {
        for (var x = 0; x < KnightTour.N; x++) {
            for (var y = 0; y < KnightTour.N; y++) {
                System.out.print(sol[x][y] + "\t");
            }
            System.out.println();
        }
    }

    // ナイトの巡回が可能かどうかを判定するメソッド
    static void solveKT() {
        // 盤を表す二次元配列を作成し、-1で初期化する
        final var sol = new int[KnightTour.N][KnightTour.N];
        for (var x = 0; x < KnightTour.N; x++) {
            for (var y = 0; y < KnightTour.N; y++) {
                sol[x][y] = -1;
            }
        }

        // ユーザーにナイトの初期位置を入力してもらう
        final var sc = new Scanner(System.in);
        System.out.println("ナイトの初期位置を入力してください。");
        System.out.print("x座標（0から" + (KnightTour.N - 1) + "までの整数）：");
        final var x = sc.nextInt();
        System.out.print("y座標（0から" + (KnightTour.N - 1) + "までの整数）：");
        final var y = sc.nextInt();
        sc.close();

        // ナイトの初期位置を1とする
        sol[x][y] = 1;

        // バックトラッキングを用いて、ナイトの巡回が可能かどうかを調べる
        if (!KnightTour.solveKTUtil(x, y, 2, sol)) {
            System.out.println("No solution");
            return;
        }
        KnightTour.printSolution(sol);

    }

    // バックトラッキングを用いて、ナイトの巡回が可能かどうかを再帰的に調べるメソッド
    static boolean solveKTUtil(final int x, final int y, final int movei, final int[][] sol) {
        // ナイトが全てのマスを通った場合はtrueを返す
        if (movei == KnightTour.N * KnightTour.N + 1) {
            return true;
        }

        // ナイトが移動できる8つの方向について調べる
        for (var k = 0; k < 8; k++) {
            // 次の移動先の座標を計算する
            final var next_x = x + KnightTour.xMove[k];
            final var next_y = y + KnightTour.yMove[k];

            // 移動先が盤の範囲内で、まだ通っていない場合
            if (KnightTour.isSafe(next_x, next_y, sol)) {
                // 移動先にナイトの移動回数を記録する
                sol[next_x][next_y] = movei;
                // 次の移動先から再帰的にナイトの巡回が可能かどうかを調べる
                if (KnightTour.solveKTUtil(next_x, next_y, movei + 1, sol)) {
                    return true;
                }
                // ナイトの巡回が不可能な場合は、移動先を-1に戻す（バックトラック）
                sol[next_x][next_y] = -1;
            }
        }

        // ナイトの巡回が不可能な場合はfalseを返す
        return false;
    }

    // 移動先が盤の範囲内で、まだ通っていないかどうかを判定するメソッド
    static boolean isSafe(final int x, final int y, final int[][] sol) {
        return x >= 0 && x < KnightTour.N && y >= 0 && y < KnightTour.N && sol[x][y] == -1;
    }

    // メインメソッド
    public static void main(final String[] args) {
        // ユーザーに盤のサイズを入力してもらう
        final var sc = new Scanner(System.in);
        System.out.println("盤のサイズを入力してください。");
        System.out.print("N（正の整数）：");
        KnightTour.N = sc.nextInt();
        sc.close();

        // ナイトの巡回を解く
        KnightTour.solveKT();
    }
}
