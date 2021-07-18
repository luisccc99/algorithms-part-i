package linked_lists;

import java.util.Iterator;

/**
 * Stack implementation with a linked list
 * @param <T> type of the items in the stack
 */
public class Stack<T> implements Iterable<T> {
    private Node last;
    private int size;

    // initialize stack
    public Stack() {

    }

    private class Node {
        T item;
        Node prev;

        public Node(T item, Node prev) {
            this.item = item;
            this.prev = prev;
        }
    }

    public void push(T item) {
        Node oldLast = last;
        last = new Node(item, oldLast);
        size++;
    }

    public T pop() {
        T item = last.item;
        last = last.prev;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }

    // client
    public static void main(String[] args) {

    }
}
