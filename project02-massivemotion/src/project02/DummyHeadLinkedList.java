package project02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly linked list implementation using a dummy head node.
 * This simplifies insertion and removal at the beginning of the list.
 *
 * @param <T> the type of elements stored in the list
 *
 * @author Oliver Reyes
 * @version 1.2
 */
public class DummyHeadLinkedList<T> implements List<T>, Iterable<T> {

    /**
     * Private static inner class representing a node in the linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.data = value;
            this.next = next;
        }
    }

    private Node<T> head;  // Dummy head node (never holds actual data)
    private int size;

    /**
     * Constructs an empty DummyHeadLinkedList with a dummy head node.
     */
    public DummyHeadLinkedList() {
        head = new Node<>(null, null);
        size = 0;
    }

    /**
     * Adds an item at the specified position in the list.
     *
     * @param pos  the index to insert the item
     * @param item the item to be added
     * @throws IndexOutOfBoundsException if the given position is out of range
     */
    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> prev = head;
        for (int i = 0; i < pos; i++) {
            prev = prev.next;
        }

        prev.next = new Node<>(item, prev.next);
        size++;
    }

    /**
     * Adds the item at the end of the list.
     *
     * @return true if the item was added successfully
     */
    @Override
    public boolean add(T item) {
        Node<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(item, null);
        size++;
        return true;
    }

    /**
     * Gets the value at the specified position of the list.
     *
     * @param pos the index of the value to return
     * @return the value at the specified position
     * @throws IndexOutOfBoundsException if the given position is out of range
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> curr = head.next;
        for (int i = 0; i < pos; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    /**
     * Removes and returns the element at the specified position.
     *
     * @param pos the index of the element to remove
     * @return the item removed
     * @throws IndexOutOfBoundsException if the given position is out of range
     */
    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> prev = head;
        for (int i = 0; i < pos; i++) {
            prev = prev.next;
        }

        T removed = prev.next.data;
        prev.next = prev.next.next;
        size--;
        return removed;
    }

    /**
     * Returns the number of items currently stored in the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * This iterator does NOT support the remove() operation.
     *
     * @return an iterator for the list
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head.next;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
