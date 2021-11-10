package com.company;

import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class TreeUtils {

    public Node join(Node first, Node second){
        if(Objects.isNull(first))
            return second;
        if(Objects.isNull(second))
            return first;
        if(ThreadLocalRandom.current().nextInt(first.size + second.size + 1) < first.size){
            first.rightBrother = join(first.rightBrother, second);
            first.fixSize();
            return first;
        }
        second.son = join(first, second.son);
        second.fixSize();
        return second;
    }
}
