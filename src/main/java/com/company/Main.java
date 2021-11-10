package com.company;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        RandomBinaryTree tree = RandomBinaryTree.builder().build();
        IntStream.range(0, 10).map(i -> ThreadLocalRandom.current().nextInt(50)).forEach(tree::insert);
        System.out.println(tree);
        tree.inOrderTraversal();
        System.out.println();
        tree.preOrderTraversal();
    }
}
