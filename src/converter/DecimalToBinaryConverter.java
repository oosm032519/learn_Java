package converter;

public class DecimalToBinaryConverter {
    public static class Main {
        public static void main(final String[] args) {
            // コマンドライン引数が空でないかチェックする
            if (args.length == 0) {
                System.out.println("コマンドライン引数がありません。");
                return;
            }
            // コマンドライン引数の10進数をdouble型に変換する
            double decimal;
            try {
                decimal = Double.parseDouble(args[0]);
            } catch (final Exception e) {
                // 数値に変換できない場合はエラーメッセージを出力する
                System.out.println("コマンドライン引数が数値ではありません。");
                return;
            }
            // 10進数を2進数に変換する
            final var binary = DecimalToBinaryConverter.convert(decimal);
            // 結果を出力する
            System.out.println("10進数 " + decimal + " の2進数は " + binary + " です。");
        }
    }

    // 変換する基数
    private static final int BASE = 2;

    // 小数部分の最大桁数
    private static final int MAX_DIGITS = 10;

    // 10進数を2進数に変換するメソッド
    public static String convert(final double decimal) {
        // 整数部分と小数部分に分離する
        final var integer = (int) decimal;
        final var fraction = decimal - integer;
        // 整数部分を2進数に変換する
        final var binaryInteger = DecimalToBinaryConverter.convertInteger(integer);
        // 小数部分を2進数に変換する
        final var binaryFraction = DecimalToBinaryConverter.convertFraction(fraction);
        // 2進数の整数部分と小数部分を結合する
        return binaryInteger + "." + binaryFraction;
    }

    // 小数部分を2進数に変換するメソッド
    private static String convertFraction(double fraction) {
        // 2進数の小数部分を格納する変数
        final var binaryFraction = new StringBuilder();
        // 小数部分の桁数をカウントする変数
        var digits = 0;
        // 小数が0になるか、桁数が最大になるまで繰り返す
        while (fraction > 0 && digits < DecimalToBinaryConverter.MAX_DIGITS) {
            // 小数を2倍する
            fraction = fraction * DecimalToBinaryConverter.BASE;
            // 整数部分を2進数の桁とする
            final var bit = (int) fraction;
            // 2進数の桁を文字列に追加する
            binaryFraction.append(bit);
            // 小数部分を更新する
            fraction = fraction - bit;
            // 桁数を増やす
            digits++;
        }
        // 2進数の小数部分を返す
        return binaryFraction.toString();
    }

    // 整数部分を2進数に変換するメソッド
    private static String convertInteger(int integer) {
        // 0の場合はそのまま返す
        if (integer == 0) {
            return "0";
        }
        // 2進数の整数部分を格納する変数
        StringBuilder binaryInteger = new StringBuilder();
        // 整数が0になるまで繰り返す
        while (integer > 0) {
            // 2で割った余りを2進数の桁とする
            final var bit = integer % DecimalToBinaryConverter.BASE;
            // 2進数の桁を文字列に追加する
            binaryInteger.insert(0, bit);
            // 整数を2で割る
            integer = integer / DecimalToBinaryConverter.BASE;
        }
        // 2進数の整数部分を返す
        return binaryInteger.toString();
    }
}
