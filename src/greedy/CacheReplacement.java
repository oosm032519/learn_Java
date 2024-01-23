package greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class CacheReplacement {
    public static void main(final String[] args) {
        // コマンドライン引数からキャッシュ置換アルゴリズムとデータを取得する
        final var algorithm = args[0];
        final var data = args[1].split(",");

        // アルゴリズムに応じて対応するメソッドを呼び出す
        switch (algorithm) {
            case "FIFO":
                CacheReplacement.printFIFO(data);
                break;
            case "LIFO":
                CacheReplacement.printLIFO(data);
                break;
            case "LFU":
                CacheReplacement.printLFU(data);
                break;
            case "LRU":
                CacheReplacement.printLRU(data);
                break;
            default:
                System.out.println("Invalid algorithm");
                break;
        }
    }

    // FIFO（先入れ先出し）アルゴリズムを用いてキャッシュの状態を表示するメソッド
    private static void printFIFO(final String[] data) {
        // キューを使ってFIFOの順序を保持する
        final Queue<String> fifo = new LinkedList<>(Arrays.asList(data));
        // キューの内容を出力する
        System.out.println("FIFO: " + fifo);
    }

    // LIFO（後入れ先出し）アルゴリズムを用いてキャッシュの状態を表示するメソッド
    private static void printLIFO(final String[] data) {
        // スタックを使ってLIFOの順序を保持する
        final var lifo = new Stack<String>();
        lifo.addAll(Arrays.asList(data));
        // スタックの内容を出力する
        System.out.println("LIFO: " + lifo);
    }

    // LFU（最小使用頻度）アルゴリズムを用いてキャッシュの状態を表示するメソッド
    private static void printLFU(final String[] data) {
        // マップを使って各データの使用回数を記録する
        final Map<String, Integer> lfu = new HashMap<>();
        for (final String item : data) {
            // データが既にマップにあれば使用回数を増やし、なければ1にする
            lfu.put(item, lfu.getOrDefault(item, 0) + 1);
        }
        // マップの内容を出力する
        System.out.println("LFU: " + lfu);
    }

    // LRU（最近使用されたもの）アルゴリズムを用いてキャッシュの状態を表示するメソッド
    private static void printLRU(final String[] data) {
        // リンク付きハッシュマップを使ってLRUの順序と使用回数を保持する
        final LinkedHashMap<String, Integer> lru = new LinkedHashMap<>(data.length, 0.75f, true) {
            // マップのサイズがデータの長さを超えたら、最も古く使用されたエントリーを削除する
            protected boolean removeEldestEntry(final Map.Entry<String, Integer> eldest) {
                return size() > data.length;
            }
        };
        for (final String item : data) {
            // データが既にマップにあれば使用回数を増やし、なければ1にする
            lru.put(item, lru.getOrDefault(item, 0) + 1);
        }
        // マップの内容を出力する
        System.out.println("LRU: " + lru);
    }
}
