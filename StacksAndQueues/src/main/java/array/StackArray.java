package array;

import java.util.Iterator;

/**
 * Stack implementation with arrays
 * @param <T> type of the items in the stack
 */
public class StackArray<T> implements Iterable<T> {
    private int size;
    private T[] items;
    private static final int INITIAL_CAPACITY = 8;

    @SuppressWarnings("unchecked casting")
    public StackArray() {
        items = (T[]) new Object[INITIAL_CAPACITY];
    }

    @SuppressWarnings("unchecked casting")
    public StackArray(int capacity) {
        items = (T[]) new Object[capacity];
    }

    public void enqueue(T item) {

    }

    public T pop() {
        return null;
    }

    // helper method to resize array when stack underflow or waste space
    private void resize(int capacity) {

    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIteratorArray();
    }

    private class ReverseIteratorArray implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }
}
