package sql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseExample {

    public static void main(final String[] args) {
        // 標準入力からデータベースのURL、ユーザー名、パスワードを読み込む
        final var scanner = new Scanner(System.in);
        System.out.println("データベースのURLを入力してください。");
        final var url = scanner.nextLine();
        System.out.println("データベースのユーザー名を入力してください。");
        final var user = scanner.nextLine();
        System.out.println("データベースのパスワードを入力してください。");
        final var password = scanner.nextLine();

        // データベースに接続する
        try (var conn = DriverManager.getConnection(url, user, password)) {
            // ステートメントを作成する
            final var stmt = conn.createStatement();

            // 標準入力からSQL文を読み込む
            System.out.println("SQL文を入力してください。終了するにはquitと入力してください。");
            while (true) {
                final var sql = scanner.nextLine();
                if ("quit".equalsIgnoreCase(sql)) {
                    break;
                }
                try {
                    // SQL文を実行する
                    final var result = stmt.execute(sql);
                    if (result) {
                        // 結果セットを取得する
                        final var rs = stmt.getResultSet();
                        // 結果セットのメタデータを取得する
                        final var rsmd = rs.getMetaData();
                        // 列数を取得する
                        final var columnCount = rsmd.getColumnCount();
                        // 列名を表示する
                        for (var i = 1; i <= columnCount; i++) {
                            System.out.print(rsmd.getColumnName(i) + "\t");
                        }
                        System.out.println();
                        // レコードを表示する
                        while (rs.next()) {
                            for (var i = 1; i <= columnCount; i++) {
                                System.out.print(rs.getString(i) + "\t");
                            }
                            System.out.println();
                        }
                    } else {
                        // 更新件数を表示する
                        final var updateCount = stmt.getUpdateCount();
                        System.out.println("更新件数: " + updateCount);
                    }
                } catch (final SQLException e) {
                    // エラーメッセージを表示する
                    System.out.println("エラー: " + e.getMessage());
                }
            }
            scanner.close();
        } catch (final SQLException e) {
            // エラーメッセージを表示する
            System.out.println("エラー: " + e.getMessage());
        }
    }
}
