import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A randomized queue is similar to a stack or queue, except that the
 * item removed is chosen uniformly at random among items in the data structure.
 * @param <Item> type of elements in the RandomizedQueue
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_CAPACITY = 8;
    private Item[] items;
    private int n;
    private int tail;

    @SuppressWarnings("unchecked casts") // ugly cast
    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_CAPACITY];
        n = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (n == items.length) {
            resize(items.length * 2);
        }
        items[tail++] = element;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        if (n <= (items.length / 4)) {
            resize(items.length / 2);
        }
        int index = StdRandom.uniform(n);
        Item element = items[index];
        items[index] = items[--tail];
        items[tail] = null;
        n--;
        return element;
    }

    @SuppressWarnings("unchecked casts")
    private void resize(int capacity) {
        Item[] cp = (Item[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            cp[i] = items[i];
        }
        items = cp;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return items[StdRandom.uniform(n)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        Item[] cp;
        int current = 0;

        @SuppressWarnings("unchecked")
        public RandomIterator() {
            cp = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                cp[i] = items[i];
            }
            StdRandom.shuffle(cp);
        }

        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return cp[current++];
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
