package greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// ポイントを表すクラス
class Point {
    double x; // x座標
    double y; // y座標

    // コンストラクタ
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    // 二つのポイントの距離を計算するメソッド
    public double distance(final Point p) {
        return Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
    }
}

// クローゼストペアを求めるクラス
class ClosestPair {
    // 二つのポイントのペアを表すクラス
    static class Pair {
        Point p1; // 一つ目のポイント
        Point p2; // 二つ目のポイント
        double d; // 二つのポイントの距離

        // コンストラクタ
        public Pair(final Point p1, final Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            d = p1.distance(p2);
        }
    }

    // x座標でソートされたポイントの配列
    Point[] pointsByX;

    // y座標でソートされたポイントの配列
    Point[] pointsByY;

    // コンストラクタ
    public ClosestPair(final Point[] points) {
        // ポイントの数
        final var n = points.length;

        // x座標でソートされた配列を作成
        pointsByX = new Point[n];
        System.arraycopy(points, 0, pointsByX, 0, n);
        Arrays.sort(pointsByX, Comparator.comparing(p1 -> p1.x));

        // y座標でソートされた配列を作成
        pointsByY = new Point[n];
        System.arraycopy(points, 0, pointsByY, 0, n);
        Arrays.sort(pointsByY, Comparator.comparing(p1 -> p1.y));
    }

    // クローゼストペアを求めるメソッド
    public Pair findClosestPair() {
        // 全てのポイントを含む区間でクローゼストペアを求める
        return findClosestPair(0, pointsByX.length - 1);
    }

    // 指定された区間でクローゼストペアを求めるメソッド
    private Pair findClosestPair(final int low, final int high) {
        // 区間の長さ
        final var n = high - low + 1;

        // 区間の長さが2ならそのまま返す
        if (n == 2) {
            return new Pair(pointsByX[low], pointsByX[high]);
        }

        // 区間の長さが3なら全ての組み合わせを比較して最小のものを返す
        if (n == 3) {
            final var p1 = new Pair(pointsByX[low], pointsByX[low + 1]);
            final var p2 = new Pair(pointsByX[low], pointsByX[high]);
            final var p3 = new Pair(pointsByX[low + 1], pointsByX[high]);
            if (p1.d <= p2.d && p1.d <= p3.d) {
                return p1;
            }
            if (p2.d <= p1.d && p2.d <= p3.d) {
                return p2;
            }
            return p3;
        }

        // 区間を二つに分割する
        final var mid = (low + high) / 2;
        final var midPoint = pointsByX[mid];

        // 左側の区間でクローゼストペアを求める
        final var leftPair = findClosestPair(low, mid);

        // 右側の区間でクローゼストペアを求める
        final var rightPair = findClosestPair(mid + 1, high);

        // 左側と右側のうち小さい方を暫定的なクローゼストペアとする
        var closestPair = leftPair.d <= rightPair.d ? leftPair : rightPair;

        // 中央の線から距離が暫定的なクローゼストペアの距離以下のポイントを集める
        final var strip = new Point[n];
        var size = 0;
        for (var i = 0; i < n; i++) {
            if (Math.abs(pointsByY[i].x - midPoint.x) < closestPair.d) {
                strip[size] = pointsByY[i];
                size++;
            }
        }

        // 集めたポイントについて、y座標が近いもの同士を比較してクローゼストペアを更新する
        for (var i = 0; i < size; i++) {
            for (var j = i + 1; j < size && strip[j].y - strip[i].y < closestPair.d; j++) {
                final var pair = new Pair(strip[i], strip[j]);
                if (pair.d < closestPair.d) {
                    closestPair = pair;
                }
            }
        }

        // クローゼストペアを返す
        return closestPair;
    }
}

// メインクラス
class Main {
    public static void main(final String[] args) {
        // ユーザーに親切な標準入力を使用する
        final var sc = new Scanner(System.in);
        System.out.println("ポイントの数を入力してください。");
        final var n = sc.nextInt(); // ポイントの数
        final var points = new Point[n]; // ポイントの配列
        System.out.println("ポイントの座標を入力してください。");
        for (var i = 0; i < n; i++) {
            final var x = sc.nextDouble(); // x座標
            final var y = sc.nextDouble(); // y座標
            points[i] = new Point(x, y); // ポイントを作成して配列に追加
        }
        sc.close();

        // クローゼストペアを求める
        final var cp = new ClosestPair(points);
        final var pair = cp.findClosestPair();

        // 結果を出力する
        System.out.println("クローゼストペアは以下の二つのポイントです。");
        System.out.println("p1: (" + pair.p1.x + ", " + pair.p1.y + ")");
        System.out.println("p2: (" + pair.p2.x + ", " + pair.p2.y + ")");
        System.out.println("二つのポイントの距離は " + pair.d + " です。");
    }
}
