import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A randomized queue is similar to a stack or queue, except that the
 * item removed is chosen uniformly at random among items in the data structure.
 * @param <E> type of elements in the RandomizedQueue
 */
public class RandomizedQueue<E> implements Iterable<E> {
    private static final int INITIAL_CAPACITY = 8;
    private E[] items;
    private int n;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked casts")
    public RandomizedQueue() {
        items = (E[]) new Object[INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        n++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        n--;
        return null;
    }

    private void resize(int capacity) {
        items = Arrays.copyOf(items, capacity);
    }

    public E sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }
}
