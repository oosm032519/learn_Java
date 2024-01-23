package recursion;

import java.util.Scanner;
import java.util.StringJoiner;

public class NQueens {
    private final int[] queens;
    private final int N;

    public NQueens(final int N) {
        queens = new int[N];
        this.N = N;
    }

    // r行目、c列目にクイーンを配置できるかどうかを判断する
    public boolean canPlaceQueen(final int r, final int c) {
        for (var i = 0; i < r; i++) {
            if (queens[i] == c || queens[i] - i == c - r || queens[i] + i == c + r) {
                return false;
            }
        }
        return true;
    }

    // r行目以降にクイーンを配置する
    public void placeQueens(final int r) {
        for (var c = 0; c < N; c++) {
            if (canPlaceQueen(r, c)) {
                queens[r] = c;
                if (r == N - 1) {
                    printQueens();
                } else {
                    placeQueens(r + 1);
                }
            }
        }
    }

    public void printQueens() {
        for (var i = 0; i < N; i++) {
            final var joiner = new StringJoiner(" ");
            for (var j = 0; j < N; j++) {
                if (queens[i] == j) {
                    joiner.add("Q");
                } else {
                    joiner.add("*");
                }
            }
            System.out.println(joiner);
        }
        System.out.println();
    }

    public static void main(final String[] args) {
        try (var scanner = new Scanner(System.in)) {
            System.out.println("Enter the number of Queens: ");
            final var N = scanner.nextInt();
            final var nQueens = new NQueens(N);
            nQueens.placeQueens(0);
        }
    }
}
