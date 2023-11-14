import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;

    @Override
    public void insert(E element) {
        this.root = insertRec(root, element);
    }

    private Node<E> insertRec(Node<E> node, E element) {
        if (node == null) {
            return new Node<>(element);
        }
        int compare = element.compareTo(node.getValue());
        if (compare > 0) {
            node.rightChild = insertRec(node.rightChild, element);
        } else if (compare < 0) {
            node.leftChild = insertRec(node.leftChild, element);
        }
        return node;
    }

    @Override
    public boolean contains(E element) {
        return containsRec(root, element);
    }

    private boolean containsRec(Node<E> node, E element) {
        if (node == null) {
            return false;
        }
        int compare = element.compareTo(node.getValue());
        if (compare == 0) {
            return true;
        } else if (compare > 0) {
            return containsRec(node.rightChild, element);
        } else {
            return containsRec(node.leftChild, element);
        }
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        AbstractBinarySearchTree<E> tree = new BinarySearchTree<>();
        Node<E> node = getNode(element);
        if (node == null) {
            return tree;
        }
        List<E> list = searchDFS(node);
        for (E e : list) {
            tree.insert(e);
        }
        return tree;
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    @Override
    public Node<E> getNode(E e) {
        return getNodeRec(root, e);
    }

    private Node<E> getNodeRec(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int compare = element.compareTo(node.getValue());
        if (compare == 0) {
            return node;
        } else if (compare > 0) {
            return getNodeRec(node.rightChild, element);
        } else {
            return getNodeRec(node.leftChild, element);
        }
    }

    @Override
    public AbstractBinarySearchTree<E> getLeft(Node<E> node) {
        AbstractBinarySearchTree<E> tree = new BinarySearchTree<>();
        if (node == null) {
            return tree;
        }
        if (node.getLeftChild() != null) {
            List<E> list = searchDFS(node.getLeftChild());
            for (E e : list) {
                tree.insert(e);
            }
        }
        return tree;

    }

    private List<E> searchDFS(Node<E> node) {
        List<E> result = new ArrayList<>();
        result.add(node.getValue());
        if (node.getLeftChild() != null) result.addAll(searchDFS(node.getLeftChild()));
        if (node.getRightChild() != null) result.addAll(searchDFS(node.getRightChild()));
        return result;
    }

    @Override
    public AbstractBinarySearchTree<E> getRight(Node<E> node) {
        AbstractBinarySearchTree<E> tree = new BinarySearchTree<>();
        if (node == null) {
            return tree;
        }
        if (node.getRightChild() != null) {
            List<E> list = searchDFS(node.getRightChild());
            for (E e : list) {
                tree.insert(e);
            }
        }
        return tree;
    }

    @Override
    public E getValue(Node<E> node) {
        return node.getValue();
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }
}

