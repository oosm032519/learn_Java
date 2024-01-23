package recursion;

import java.util.Scanner;

public class SudokuSolver {

    // 数独のサイズを定義する定数
    public static final int SIZE = 9;

    // 数独の盤面を表す二次元配列
    public static int[][] board = new int[SudokuSolver.SIZE][SudokuSolver.SIZE];

    // メインメソッド
    public static void main(final String[] args) {
        // ユーザーから数独の盤面を入力するためのスキャナーを作成する
        final var scanner = new Scanner(System.in);

        // ユーザーに数独の盤面を入力するように促す
        System.out.println("数独の盤面を入力してください。空白のセルには0を入力してください。");

        // 二次元配列に数独の盤面を格納する
        for (var i = 0; i < SudokuSolver.SIZE; i++) {
            for (var j = 0; j < SudokuSolver.SIZE; j++) {
                SudokuSolver.board[i][j] = scanner.nextInt();
            }
        }

        // スキャナーを閉じる
        scanner.close();

        // 数独を解く
        if (SudokuSolver.solveSudoku()) {
            // 解けた場合は解答を出力する
            System.out.println("数独の解答は以下の通りです。");
            SudokuSolver.printBoard();
        } else {
            // 解けなかった場合はエラーメッセージを出力する
            System.out.println("この数独は解けません。");
        }
    }

    // 数独を解くメソッド
    public static boolean solveSudoku() {
        // 空白のセルの位置を格納する変数
        var row = -1;
        var col = -1;

        // 空白のセルがあるかどうかを判定する変数
        var isEmpty = true;

        // 二次元配列を走査して空白のセルを探す
        for (var i = 0; i < SudokuSolver.SIZE; i++) {
            for (var j = 0; j < SudokuSolver.SIZE; j++) {
                // 空白のセルが見つかった場合
                if (SudokuSolver.board[i][j] == 0) {
                    // 空白のセルの位置を記録する
                    row = i;
                    col = j;

                    // 空白のセルがあることを示す
                    isEmpty = false;

                    // 走査を終了する
                    break;
                }
            }
            // 走査を終了する
            if (!isEmpty) {
                break;
            }
        }

        // 空白のセルがない場合は数独が解けたことを示す
        if (isEmpty) {
            return true;
        }

        // 空白のセルに入れる候補の数字を1から9まで試す
        for (var num = 1; num <= SudokuSolver.SIZE; num++) {
            // 空白のセルに数字を入れても矛盾がない場合
            if (SudokuSolver.isSafe(row, col, num)) {
                // 空白のセルに数字を入れる
                SudokuSolver.board[row][col] = num;

                // 次の空白のセルに進む
                if (SudokuSolver.solveSudoku()) {
                    // 数独が解けた場合はtrueを返す
                    return true;
                }
                // 数独が解けなかった場合は空白のセルを0に戻す
                SudokuSolver.board[row][col] = 0;
            }
        }

        // 数独が解けなかった場合はfalseを返す
        return false;
    }

    // 数字を入れても矛盾がないかどうかを判定するメソッド
    public static boolean isSafe(final int row, final int col, final int num) {
        // 同じ行に同じ数字があるかどうかをチェックする
        for (var j = 0; j < SudokuSolver.SIZE; j++) {
            if (SudokuSolver.board[row][j] == num) {
                return false;
            }
        }

        // 同じ列に同じ数字があるかどうかをチェックする
        for (var i = 0; i < SudokuSolver.SIZE; i++) {
            if (SudokuSolver.board[i][col] == num) {
                return false;
            }
        }

        // 同じ3x3のブロックに同じ数字があるかどうかをチェックする
        final var startRow = row - row % 3;
        final var startCol = col - col % 3;
        for (var i = startRow; i < startRow + 3; i++) {
            for (var j = startCol; j < startCol + 3; j++) {
                if (SudokuSolver.board[i][j] == num) {
                    return false;
                }
            }
        }

        // すべてのチェックを通過した場合はtrueを返す
        return true;
    }

    // 数独の盤面を出力するメソッド
    public static void printBoard() {
        for (var i = 0; i < SudokuSolver.SIZE; i++) {
            for (var j = 0; j < SudokuSolver.SIZE; j++) {
                System.out.print(SudokuSolver.board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
