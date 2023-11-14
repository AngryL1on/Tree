public class BinaryTreeMain{
    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree<>(5);
        root.setLeft(new BinaryTree<>(3));
        root.setRight(new BinaryTree<>(8));
        root.getLeft().setLeft(new BinaryTree<>(2));
        root.getLeft().setRight(new BinaryTree<>(4));
        root.getRight().setLeft(new BinaryTree<>(7));
        root.getRight().setRight(new BinaryTree<>(9));
        root.getLeft().getLeft().setLeft(new BinaryTree<>(1));
        root.getLeft().getLeft().setRight(new BinaryTree<>(6));
        root.printTree();

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(8);
        tree.insert(2);
        tree.insert(4);
        tree.insert(7);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);
        tree.printTree();

        System.out.println("DFS");
        root.searchDFS().forEach(System.out::println);
        System.out.println("BFS");
        root.searchBFS().forEach(System.out::println);

        BinaryTree<Character> pers = new BinaryTree<>('A');
        pers.setLeft(new BinaryTree<>('B'));
        pers.setRight(new BinaryTree<>('C'));
        pers.getLeft().setLeft(new BinaryTree<>('D'));
        pers.getLeft().setRight(new BinaryTree<>('E'));
        pers.getRight().setLeft(new BinaryTree<>('F'));
        pers.getRight().setRight(new BinaryTree<>('G'));
        pers.printTree();

        System.out.println("Displaying a tree by level:");
        pers.printTreeByLevels();
    }
}
