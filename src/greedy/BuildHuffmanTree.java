package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char c, int data, HuffmanNode left, HuffmanNode right) {
        this.c = c;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    void printCode(String s) {
        if (left == null && right == null && Character.isLetter(c)) {
            System.out.println(c + ":" + s);
        } else {
            if (left != null) left.printCode(s + "0");
            if (right != null) right.printCode(s + "1");
        }
    }
}

public class BuildHuffmanTree {
    public static void main(final String[] args) {
        final var test = args[0];
        final var charArray = new int[256];
        for (var i = 0; i < test.length(); i++) {
            charArray[test.charAt(i)]++;
        }

        final var q = new PriorityQueue<HuffmanNode>(256, Comparator.comparingInt(x -> x.data));

        for (var i = 0; i < 256; i++) {
            if (charArray[i] > 0) {
                q.add(new HuffmanNode((char) i, charArray[i], null, null));
            }
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            final var left = q.poll();
            final var right = q.poll();

            if (right != null) {
                root = new HuffmanNode('-', left.data + right.data, left, right);
            }
            q.add(root);
        }

        if (root != null) {
            root.printCode("");
        }
    }
}
