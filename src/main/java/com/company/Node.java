package com.company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

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

    public Node find(int key) {
        if (key == this.key)
            return this;
        if (key < this.key)
            return Objects.nonNull(son) ? son.find(key) : null;
        return Objects.nonNull(rightBrother) ? rightBrother.find(key) : null;
    }

    public Node insert(int key) {
        if (ThreadLocalRandom.current().nextBoolean())
            return insertRoot(key);
        if (this.key > key)
            Optional.ofNullable(son).ifPresentOrElse(s -> son = s.insert(key), () -> son = Node.builder().key(key).build());
        else
            Optional.ofNullable(rightBrother).ifPresentOrElse(b -> rightBrother = b.insert(key), () -> rightBrother = Node.builder().key(key).build());
        fixSize();
        return this;
    }

    private Node rotateRight(){
        Node tempSon = son;
        son = tempSon.rightBrother;
        tempSon.rightBrother = this;
        fixSize();
        tempSon.fixSize();
        return tempSon;
    }

    private Node rotateLeft(){
        Node brother = rightBrother;
        rightBrother = brother.son;
        brother.son = this;
        fixSize();
        brother.fixSize();
        return brother;
    }

    private Node insertRoot(int key){
        if (key < this.key) {
            Optional.ofNullable(son).ifPresentOrElse((s) -> son = s.insertRoot(key), () -> son = Node.builder().key(key).build());
            return rotateRight();
        }
        else {
            Optional.ofNullable(rightBrother).ifPresentOrElse((b) -> rightBrother = b.insertRoot(key), () -> rightBrother = Node.builder().key(key).build());
            return rotateLeft();
        }
    }

    public Node remove(int key){
        if (this.key == key)
            return TreeUtils.join(son, rightBrother);
        if (key < this.key)
            Optional.ofNullable(son).ifPresent(n -> son = n.remove(key));
        else
            Optional.ofNullable(rightBrother).ifPresent(n -> rightBrother = n.remove(key));
        return this;
    }

    public void preOrderTraversal(){
        System.out.print(this.key + " ");
        Stream.of(son, rightBrother).filter(Objects::nonNull).forEach(Node::preOrderTraversal);
    }

    public void inOrderTraversal(){
        Optional.ofNullable(son).ifPresent(Node::inOrderTraversal);
        System.out.print(key + " ");
        Optional.ofNullable(rightBrother).ifPresent(Node::inOrderTraversal);
    }
}