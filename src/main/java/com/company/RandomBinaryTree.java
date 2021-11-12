package com.company;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
class RandomBinaryTree {

    @Builder.Default
    Node root = null;

    public int size(){
        return Optional.ofNullable(root).map(Node::getSize).orElse(0);
    }

    public Node find(int key) {
        if (Objects.isNull(root))
            return null;
        return root.find(key);
    }

    public void insert(int key) {
        Optional.ofNullable(root).ifPresentOrElse(r -> root = r.insert(key), () -> root = Node.builder().key(key).build());
    }

    public void remove(int key){
        Optional.ofNullable(root).ifPresent(r -> root = r.remove(key));
    }

    /** Прямой обход */
    public void preOrderTraversal(){
        if (Objects.isNull(root))
            return;
        root.preOrderTraversal();
        System.out.println();
    }

    /** Симметричный обход */
    public void inOrderTraversal(){
        if (Objects.isNull(root))
            return;
        root.inOrderTraversal();
        System.out.println();
    }

    public List<Integer> getAllKeys(){
        return root.getAllKeys();
    }

    public void exclude(RandomBinaryTree subtraction){
        Set<Integer> subtractionKeys = new HashSet<>(subtraction.getAllKeys());
        getAllKeys().stream().filter(subtractionKeys::contains).forEach(this::remove);
    }
}