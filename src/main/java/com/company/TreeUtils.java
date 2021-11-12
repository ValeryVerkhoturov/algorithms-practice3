package com.company;

import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
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
}
