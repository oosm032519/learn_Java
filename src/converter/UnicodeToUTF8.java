package converter;

import java.util.Scanner;

public class UnicodeToUTF8 {

    public static void main(String[] args) {
        // 標準入力からUnicodeの符号位置を読み込む
        Scanner sc = new Scanner(System.in);
        System.out.println("Unicodeの符号位置を入力してください。");
        int codePoint = sc.nextInt();
        sc.close();

        // Unicodeの符号位置を2進数に変換する
        String binary = Integer.toBinaryString(codePoint);

        // 2進数の桁数に応じて、UTF-8の符号の形式を決める
        int length = binary.length();
        String utf8;
        if (length <= 7) {
            // 1バイトの形式
            // 0を追加して8ビットにする
            utf8 = String.format("%08d", Integer.parseInt(binary));
        } else if (length <= 11) {
            // 2バイトの形式
            // 110と10を追加して16ビットにする
            utf8 = "110" + binary.substring(0, length - 6) + "10" + binary.substring(length - 6);
        } else if (length <= 16) {
            // 3バイトの形式
            // 1110と10と10を追加して24ビットにする
            utf8 = "1110" + binary.substring(0, length - 12) + "10" + binary.substring(length - 12, length - 6) + "10" + binary.substring(length - 6);
        } else if (length <= 21) {
            // 4バイトの形式
            // 11110と10と10と10を追加して32ビットにする
            utf8 = "11110" + binary.substring(0, length - 18) + "10" + binary.substring(length - 18, length - 12) + "10" + binary.substring(length - 12, length - 6) + "10" + binary.substring(length - 6);
        } else {
            // エラーを表示する
            System.out.println("Unicodeの符号位置が範囲外です。");
            return;
        }

        // UTF-8の符号を16進数に変換する
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < utf8.length(); i += 8) {
            // 8ビットずつ切り出して、16進数に変換する
            String sub = utf8.substring(i, i + 8);
            int decimal = Integer.parseInt(sub, 2);
            hex.append(String.format("%02x", decimal));
        }

        // UTF-8の符号を出力する
        System.out.println("UTF-8の符号は " + hex + " です。");
    }
}
