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
        if(ThreadLocalRandom.current().nextInt(first.size + second.size) < first.size){
            first.rightBrother = join(first.rightBrother, second);
            first.fixSize();
            return first;
        }
        second.son = join(first, second.son);
        second.fixSize();
        return second;
    }
}
