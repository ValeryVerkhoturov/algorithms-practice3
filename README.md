# Алгоритмы и структуры данных 3 лабораторная

Тип дерева - рандомизированное дерево двоичного дерева
Реализация дерева - левый сын, правый брат (указатели)


Операция - из дерева A исключаются узлы, отсутствующие в дереве B == в дерево С поместить ключи из A, которые присутствуют в B
Вывод деревьев на экран:
    A - прямой preorderTraversal
    B - симметричный inorderTraversal



Дерево A
RandomBinaryTree(
    root=Node(key=3, size=10,
        son=Node(key=1, size=6,
            son=Node(key=0, size=2,
                son=null,
                rightBrother=Node(key=1, size=1,
                    son=null,
                    rightBrother=null)),
            rightBrother=Node(key=3, size=3,
                son=Node(key=3, size=1,
                    son=null,
                    rightBrother=null),
                rightBrother=Node(key=3, size=1,
                    son=null,
                    rightBrother=null))),
        rightBrother=Node(key=4, size=3,
            son=Node(key=4, size=2,
                son=Node(key=4, size=1,
                    son=null,
                    rightBrother=null),
                rightBrother=null),
            rightBrother=null)))
Прямой обход
3 1 0 1 3 3 3 4 4 4
Симметричный обход
0 1 1 3 3 3 3 4 4 4

Дерево B
RandomBinaryTree(
    root=Node(key=15, size=10,
        son=Node(key=4, size=9,
            son=Node(key=4, size=3,
                son=Node(key=2, size=2,
                    son=Node(key=1, size=1,
                        son=null,
                        rightBrother=null),
                    rightBrother=null),
                rightBrother=null),
            rightBrother=Node(key=12, size=5,
                son=Node(key=12, size=1,
                    son=null,
                    rightBrother=null),
                rightBrother=Node(key=14, size=3,
                    son=Node(key=14, size=1,
                        son=null,
                        rightBrother=null),
                    rightBrother=Node(key=14, size=1,
                        son=null,
                        rightBrother=null)))),
        rightBrother=null))
Прямой обход
15 4 4 2 1 12 12 14 14 14
Симметричный обход
1 2 4 4 12 12 14 14 14 15

Из дерева A исключить B
RandomBinaryTree(
    root=Node(key=4, size=3,
        son=Node(key=1, size=2,
            son=null,
            rightBrother=Node(key=4, size=1,
                son=null,
                rightBrother=null)),
        rightBrother=null))
Прямой обход
4 1 4
Симметричный обход
1 4 4