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

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (Objects.isNull(node))
            return Node.builder().key(key).build();
        if (ThreadLocalRandom.current().nextInt(0, node.size) == 0)
            return insertRoot(node, key);
        if (node.key > key)
            node.son = insert(node.son, key);
        else
            node.rightBrother = insert(node.rightBrother, key);
        node.fixSize();
        return node;
    }

    /*private Node rotateRight(Node node){
        Node son = node.son;
        if (Objects.isNull(son))
            return node;
        node.rightBrother = son.son;
        son.son = node;
        son.size = node.size;
        node.fixSize();
        return son;
    }*/
    private Node rotateRight(Node node){
        Node son = node.son;
        node.son = son.rightBrother;
        son.rightBrother = node;
        node.fixSize();
        son.fixSize();
        return son;
    }

    /*private Node rotateLeft(Node node){
        Node brother = node.rightBrother;
        if (Objects.isNull(brother))
            return node;
        node.rightBrother = brother.rightBrother;
        brother.son = node;
        brother.size = node.size;
        node.fixSize();
        return brother;
    }*/
    private Node rotateLeft(Node node){
        Node brother = node.rightBrother;
        node.rightBrother = brother.son;
        brother.son = node;
        node.fixSize();
        brother.fixSize();
        return brother;
    }

    public Node insertRoot(int key){
        return insertRoot(root, key);
    }

    private Node insertRoot(Node node, int key){
        if (Objects.isNull(node))
            return Node.builder().key(key).build();
        if (key < node.key) {
            node.son = insertRoot(node.son, key);
            return rotateRight(node);
        }
        else {
            node.rightBrother = insertRoot(node.rightBrother, key);
            return rotateLeft(node);
        }
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

    public void preOrderTraversal(){
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node node){
        if (Objects.isNull(node))
            return;
        System.out.print(node.key + " ");
        preOrderTraversal(node.son);
        preOrderTraversal(node.rightBrother);
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node){
        if (Objects.isNull(node))
            return;
        inOrderTraversal(node.son);
        System.out.print(node.key + " ");
        inOrderTraversal(node.rightBrother);
    }
}