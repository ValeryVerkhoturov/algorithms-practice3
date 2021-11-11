package com.company;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        RandomBinaryTree tree = RandomBinaryTree.builder().build();
        IntStream.range(0, 10).map(i -> ThreadLocalRandom.current().nextInt(3)).forEach(tree::insert);
        System.out.println(tree);
        tree.preOrderTraversal();
        tree.inOrderTraversal();
        tree.remove(0);
        tree.inOrderTraversal();
        tree.remove(1);
        tree.inOrderTraversal();
        tree.remove(2);
        tree.inOrderTraversal();

//        // симметричный
//        tree.inOrderTraversal();
//        System.out.println();
//        // прямой
//        tree.preOrderTraversal();
    }
}
