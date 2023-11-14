import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private AbstractBinaryTree<E> left;
    private AbstractBinaryTree<E> right;

    public BinaryTree(E key) {
        this.key = key;
    }

    @Override
    public E getKey() {
        return key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return left;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return right;
    }

    @Override
    public void setLeft(AbstractBinaryTree<E> left) {
        this.left = left;

    }

    @Override
    public void setRight(AbstractBinaryTree<E> right) {
        this.right = right;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append("  ".repeat(Math.max(0, indent)));
        builder.append(key.toString()).append("\n");
        if (left != null) builder.append(left.asIndentedPreOrder(indent + 1));
        if (right != null) builder.append(right.asIndentedPreOrder(indent + 1));
        return builder.toString();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (left != null) result.addAll(left.preOrder());
        if (right != null) result.addAll(right.preOrder());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) result.addAll(left.inOrder());
        result.add(this);
        if (right != null) result.addAll(right.inOrder());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        if (left != null) result.addAll(left.postOrder());
        if (right != null) result.addAll(right.postOrder());
        result.add(this);
        return result;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        if (left != null) left.forEachInOrder(consumer);
        consumer.accept(key);
        if (right != null) right.forEachInOrder(consumer);
    }

    @Override
    public List<AbstractBinaryTree<E>> searchDFS() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        result.add(this);
        if (left != null) result.addAll(left.searchDFS());
        if (right != null) result.addAll(right.searchDFS());
        return result;
    }

    @Override
    public List<AbstractBinaryTree<E>> searchBFS() {
        List<AbstractBinaryTree<E>> result = new ArrayList<>();
        Queue<AbstractBinaryTree<E>> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            AbstractBinaryTree<E> element = queue.poll();
            result.add(element);
            if (element.getLeft() != null) queue.offer(element.getLeft());
            if (element.getRight() != null) queue.offer(element.getRight());
        }
        return result;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "key=" + key + "}";
    }
    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(this);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (!globalStack.isEmpty()) {
                AbstractBinaryTree temp = (AbstractBinaryTree) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getKey());
                    localStack.push(temp.getLeft());
                    localStack.push(temp.getRight());
                    if (temp.getLeft() != null ||
                            temp.getRight() != null)
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
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }

    public void printTreeByLevels() {
        if (this == null) {
            return;
        }

        Queue<AbstractBinaryTree<E>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            AbstractBinaryTree<E> node = queue.poll();
            System.out.print(node.getKey() + " ");

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }
}
