package linkedlist;

// ノードクラス
class ListNode {
    int data; // データを格納するフィールド
    ListNode next; // 次のノードへの参照を格納するフィールド
    ListNode prev; // 前のノードへの参照を格納するフィールド

    // コンストラクタ
    ListNode(final int data) {
        this.data = data; // データを初期化
        next = null; // 次のノードへの参照をnullに設定
        prev = null; // 前のノードへの参照をnullに設定
    }
}

// 双方向リンクリストクラス
class DoublyLinkedList {
    ListNode head; // リストの先頭を指すフィールド

    // データをリストに追加するメソッド
    public void add(final int data) {
        final var newNode = new ListNode(data); // 新しいノードを作成

        if (head == null) { // リストが空の場合
            head = newNode; // 新しいノードを先頭に設定
        } else { // リストが空でない場合
            var last = head; // 最後のノードを見つけるための一時変数
            while (last.next != null) { // 最後のノードを見つけるまでループ
                last = last.next;
            }
            last.next = newNode; // 最後のノードの次のノードとして新しいノードを設定
            newNode.prev = last; // 新しいノードの前のノードとして最後のノードを設定
        }
    }

    // データをリストから削除するメソッド
    public void delete(final int data) {
        if (head == null) { // リストが空の場合、何もしない
            return;
        }

        if (head.data == data) { // 先頭のノードが削除対象の場合
            head = head.next; // 先頭を次のノードに移動
            if (head != null) { // 新しい先頭が存在する場合
                head.prev = null; // 新しい先頭の前のノードをnullに設定
            }
            return;
        }

        var current = head; // 現在のノードを表す一時変数
        while (current.next != null) { // リストの終わりまでループ
            if (current.next.data == data) { // 次のノードが削除対象の場合
                current.next = current.next.next; // 現在のノードの次のノードを、次のノードの次のノードに設定
                if (current.next != null) { // 新しい次のノードが存在する場合
                    current.next.prev = current; // 新しい次のノードの前のノードを現在のノードに設定
                }
                return;
            }
            current = current.next; // 現在のノードを次のノードに移動
        }
    }

    // リストを表示するメソッド
    public void display() {
        var temp = head; // 先頭からスタート
        while (temp != null) { // リストの終わりまでループ
            System.out.print(temp.data + " "); // 現在のノードのデータを表示
            temp = temp.next; // 次のノードに移動
        }
        System.out.println(); // 改行を出力
    }

    // データをリストから検索するメソッド
    public ListNode search(final int data) {
        var current = head; // 現在のノードを表す一時変数
        while (current != null) { // リストの終わりまでループ
            if (current.data == data) { // 現在のノードのデータが検索対象と一致する場合
                return current; // 現在のノードを返す
            }
            current = current.next; // 現在のノードを次のノードに移動
        }
        return null; // 一致するノードがなければnullを返す
    }

}

//メインクラス
public class DoublyLinkedListOperations {
    public static void main(final String[] args) {
        final var list = new DoublyLinkedList(); // リストを作成

        // 1から10までの要素をリストに追加
        for (var i = 1; i <= 10; i++) {
            list.add(i);
        }

        // リストを表示
        System.out.println("Initial List:");
        list.display();

        // コマンドライン引数に基づいて操作を実行
        for (final String arg : args) {
            final var parts = arg.split("="); // 引数を分割
            final var command = parts[0]; // コマンドを取得
            final var value = Integer.parseInt(parts[1]); // 値を取得

            try {
                switch (command) {
                    case "add" -> {
                        list.add(value); // リストに値を追加

                        System.out.println("After adding " + value + ":");  // "add"コマンドの場合
                    }
                    case "delete" -> {
                        list.delete(value); // リストから値を削除

                        System.out.println("After deleting " + value + ":");  // "delete"コマンドの場合
                    }
                    case "search" -> {
                        final var node = list.search(value); // リストから値を検索

                        if (node == null) { // 一致するノードがない場合
                            System.out.println(value + " is not in the list.");
                        } else { // 一致するノードがある場合
                            System.out.println(value + " is in the list.");
                            if (node.prev != null) { // 前のノードがある場合
                                System.out.println("The previous node is " + node.prev.data + ".");
                            } else { // 前のノードがない場合
                                System.out.println("There is no previous node.");
                            }
                            if (node.next != null) { // 次のノードがある場合
                                System.out.println("The next node is " + node.next.data + ".");
                            } else { // 次のノードがない場合
                                System.out.println("There is no next node.");
                            }
                        }  // "search"コマンドの場合
                    }
                    case null, default ->  // それ以外のコマンドの場合
                            throw new IllegalArgumentException("Invalid command: " + command); // 例外をスロー
                }
                list.display(); // リストを表示
            } catch (final Exception e) {
                System.out.println("Error: " + e.getMessage()); // エラーメッセージを表示
            }
        }
    }
}
