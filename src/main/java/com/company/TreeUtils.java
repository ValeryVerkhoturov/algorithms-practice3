package com.company;

import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Stream;

@UtilityClass
public class TreeUtils {

    public Node join(Node first, Node second){
        if (Stream.of(first, second).anyMatch(Objects::isNull))
            return Stream.of(first, second).filter(Objects::nonNull).findAny().orElse(null);
        if(ThreadLocalRandom.current().nextInt(first.getSize() + second.getSize()) < first.getSize()){
            first.setRightBrother(join(first.getRightBrother(), second));
            first.fixSize();
            return first;
        }
        second.setSon(join(first, second.getSon()));
        second.fixSize();
        return second;
    }

    public RandomBinaryTree exclude(RandomBinaryTree minuend, RandomBinaryTree subtrahend){
        RandomBinaryTree difference = RandomBinaryTree.builder().build();
        List<Integer> subtrahendKeys = subtrahend.getAllKeys();
        minuend.getAllKeys().stream().filter(subtrahendKeys::contains).forEach(key -> {
            subtrahendKeys.remove(key);
            difference.insert(key);
        });
        return difference;
    }
}