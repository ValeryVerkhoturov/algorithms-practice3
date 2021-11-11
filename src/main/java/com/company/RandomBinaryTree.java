package com.company;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;
import java.util.Optional;

@Data
@Builder
class RandomBinaryTree {

    @Builder.Default
    Node root = null;

    public Node find(int key) {
        if (Objects.isNull(root))
            return null;
        return root.find(key);
    }

    public void insert(int key) {
        if (Objects.isNull(root)) {
            root = Node.builder().key(key).build();
            return;
        }
        root = root.insert(key);
    }

    public void remove(int key){
        Optional.ofNullable(root).ifPresent(r -> root = r.remove(key));
    }

    /** Прямой обход */
    public void preOrderTraversal(){
        if (Objects.isNull(root))
            return;
        System.out.println();
        root.preOrderTraversal();
    }

    /** Симметричный обход */
    public void inOrderTraversal(){
        if (Objects.isNull(root))
            return;
        System.out.println();
        root.inOrderTraversal();
    }
}