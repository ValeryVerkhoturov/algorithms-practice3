package com.company;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Builder
class RandomBinaryTree {

    @Builder.Default
    Node root = null;

    public Node find(int key) {
        return find(root, key);
    }

    private Node find(Node node, int key) {
        if (Objects.isNull(node))
            return null;
        if (key == node.key)
            return root;

        if (key < node.key)
            return find(node.son, key);
        else
            return find(node.rightBrother, key);
    }

    public Node insert(int key) {
        return insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (Objects.isNull(node))
            return Node.builder().key(key).build();
        if (ThreadLocalRandom.current().nextInt(1, node.size + 1) == 0)
            return insertRoot(node, key);
        if (node.key > key)
            node.son = insert(node.son, key);
        else
            node.rightBrother = insert(node.rightBrother, key);
        node.fixSize();
        return node;
    }

    private Node rotateRight(Node node){
        Node sonNode = node.son;
        if (Objects.isNull(sonNode))
            return node;
        node.rightBrother = sonNode.son;
        sonNode.son = node;
        sonNode.size = node.size;
        return sonNode;
    }

    public Node insertRoot(int key){
        return insertRoot(root, key);
    }

    private Node insertRoot(Node node, int key){
        if (Objects.isNull(node))
            return Node.builder().key(key).build();
        if (node.key > key) {
            node.son = insertRoot(node.son, key);
            return rotateRight(node);
        }
        node.rightBrother = insertRoot(node.rightBrother, key);
        return  rotateRight(node);
    }

    public void remove(int key){
        root = remove(root, key);
    }

    private Node remove(Node node, int key){
        if (Objects.isNull(node))
            return null;
        if (node.key == key)
            return TreeUtils.join(node.son, node.rightBrother);
        if (key < node.size)
            node.son = remove(node.son, key);
        else
            node.rightBrother = remove(node.rightBrother, key);
        return node;
    }
}