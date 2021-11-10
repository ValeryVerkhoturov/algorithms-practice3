package com.company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Objects;

@ToString
@FieldDefaults(level = AccessLevel.PUBLIC)
@Builder
public class Node {

    int key;

    @Builder.Default
    int size = 1;

    @Builder.Default
    Node son = null;

    @Builder.Default
    Node rightBrother = null;

    public void fixSize(){
        size = Arrays.stream(new Node[]{son, rightBrother}).filter(Objects::nonNull).mapToInt(n -> n.size).sum() + 1;
    }
}