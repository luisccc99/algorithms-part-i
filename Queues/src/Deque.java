import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * A double-ended queue or deque (pronounced “deck”) is a generalization
 * of a stack and a queue that supports adding and removing items from either
 * the front or the back of the data structure.
 * @param <Item> type of the elements in the Deque
 */
public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        Item element;
        Node prev;
        Node next;

        public Node(Item element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            first = new Node(element, null, null);
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(element, null, oldFirst);
            oldFirst.prev = first;
        }
        n++;
    }

    public void addLast(Item element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            last = new Node(element, null, null);
            first = last;
        } else {
            Node oldLast = last;
            last = new Node(element, oldLast, null);
            oldLast.next = last;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return remove(first);
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return remove(last);
    }

    private Item remove(Node node) {
        Item item = node.element;
        if (last == first){
            last = null;
            first = null;
        } else if (node.prev == null) { // first
            first = first.next;
            first.prev = null;
        } else if (node.next == null) { // last
            last = last.prev;
            last.next = null;
        }
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinearIterator();
    }

    private class LinearIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item element = current.element;

            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(2);
        deque.addLast(4);
        deque.addLast(5);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
    }
}
