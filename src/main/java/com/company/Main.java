package com.company;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";

        RandomBinaryTree treeA = RandomBinaryTree.builder().build();
        IntStream.range(0, 20).map(i -> ThreadLocalRandom.current().nextInt(20)).forEach(treeA::insert);
        System.out.println(ANSI_RED + "Дерево A" + ANSI_RESET);
        testMethods(treeA);
        System.out.println();

        RandomBinaryTree treeB = RandomBinaryTree.builder().build();
        IntStream.range(0, 20).map(i -> ThreadLocalRandom.current().nextInt(10)).forEach(treeB::insert);
        System.out.println(ANSI_RED + "Дерево B" + ANSI_RESET);
        testMethods(treeB);
        System.out.println();

        System.out.println(ANSI_RED + "Из дерева A исключить B" + ANSI_RESET);
        treeA.exclude(treeB);
        testMethods(treeA);
        System.out.println();
    }

    public static void testMethods(RandomBinaryTree tree){
        System.out.println(tree);
        System.out.println("Прямой обход");
        tree.preOrderTraversal();
        System.out.println("Симметричный обход");
        tree.inOrderTraversal();
    }
}
