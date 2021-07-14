import java.util.Iterator;
import java.util.NoSuchElementException;
// TODO: add message in exceptions
/**
 * A double-ended queue or deque (pronounced “deck”) is a generalization
 * of a stack and a queue that supports adding and removing items from either
 * the front or the back of the data structure.
 * @param <T> type of the elements in the Deque
 */
public class Deque<T> implements Iterable<T> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        T element;
        Node prev;
        Node next;

        public Node(T element, Node prev, Node next) {
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

    public void addFirst(T element) {
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

    public void addLast(T element) {
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

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = first.element;
        first = first.next;
        n--;
        return element;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = last.element;
        last = last.prev;
        n--;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinearIterator();
    }

    private class LinearIterator implements Iterator<T> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = current.element;

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
        System.out.println("adding at the beginning");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            deque.addFirst(i);
        }
        System.out.println();
        System.out.println("looping through deque");
        for (int n : deque) {
            System.out.print(n + " ");
        }
    }
}
