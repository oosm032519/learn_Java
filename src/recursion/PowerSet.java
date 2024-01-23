package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PowerSet {

    public static void main(final String[] args) {
        // ユーザーに集合の要素数と各要素を入力させる
        final var set = PowerSet.inputSet();

        // パワーセットを生成する
        final var powerSet = PowerSet.generatePowerSet(set);

        // パワーセットを出力する
        PowerSet.outputPowerSet(powerSet);
    }

    // ユーザーに集合の要素数と各要素を入力させるメソッド
    private static List<Integer> inputSet() {
        try (var sc = new Scanner(System.in)) {
            System.out.println("集合の要素数を入力してください。");
            final var n = sc.nextInt();

            System.out.println("集合の各要素を空白区切りで入力してください。");
            final var set = new ArrayList<Integer>();
            for (var i = 0; i < n; i++) {
                set.add(sc.nextInt());
            }
            return set;
        }
    }

    // パワーセットを生成するメソッド
    private static List<List<Integer>> generatePowerSet(final List<Integer> set) {
        final var powerSet = new ArrayList<List<Integer>>();
        final var powerSetSize = 1 << set.size(); // 2のn乗は左シフト演算で表せる
        // 各部分集合に対して
        for (var i = 0; i < powerSetSize; i++) {
            // 空の部分集合を作る
            final var subSet = new ArrayList<Integer>();
            // iのビット列を表す文字列を取得する
            final var bitString = Integer.toBinaryString(i);
            // bitStringの右からj番目の文字が'1'なら、その要素を部分集合に追加する
            IntStream.range(0, bitString.length())
                    .filter(j -> bitString.charAt(bitString.length() - 1 - j) == '1')
                    .mapToObj(set::get)
                    .forEach(subSet::add);
            // 部分集合をパワーセットに追加する
            powerSet.add(subSet);
        }
        return powerSet;
    }

    // パワーセットを出力するメソッド
    private static void outputPowerSet(final List<List<Integer>> powerSet) {
        System.out.println("パワーセットは以下の通りです。");
        // stream APIを使って出力する
        powerSet.stream()
                .map(subSet -> subSet.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"))) // 部分集合を文字列に変換する
                .forEach(System.out::println); // 各部分集合を改行して出力する
    }
}
