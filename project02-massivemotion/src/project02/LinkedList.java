package project02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that mimics the basic built-in singly linked list, storing
 * elements sequentially. Each node references its next node.
 * 
 * @param <T> the type of elements stored in the list.
 * 
 * @Author: Oliver Reyes
 * @version 1.0
 */
public class LinkedList<T> implements List<T>, Iterable<T> {
    private Node<T> head;
    private int size = 0;
    
    /**
     * A private static inner class representing a node in the linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T d) {
            data = d;
            next = null;
        }
    }
    
    /**
     * Adds an item at the specified position in the list.
     * 
     * @param pos the index to insert the item.
     * @item item the item to be added.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = new Node<>(element);

        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }
    
    /**
     * Adds the item at the end of the list.
     * 
     * @return returns true if the item is added successfully.
     */
    public boolean add(T element) {
        if (size == 0) {
            head = new Node<>(element);
        } else {
            Node<T> node = head;
            
            while (node.next != null) {
                node = node.next;
            }
            
            node.next = new Node<>(element);
        }
        size++;
        return true;
    }
    
    /**
     * Gets the value at the specified position of the list.
     * 
     *  @param pos the index of the value to return.
     *  @return the value at the specified position.
     *  @throws IndexOutofBoundsException if the given position is 
     *  out of range.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        
        return curr.data;
    }
    
    /**
     * Removes and returns the element at the specified position.
     * 
     * @param pos the index of the element to remove.
     * @return the item removed.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        T removed;

        if (index == 0) {
            removed = head.data;
            head = head.next;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            
            removed = prev.next.data;
            prev.next = prev.next.next;
        }

        size--;
        return removed;
    }

    /**
     * Returns the number of items currently stored in the list.
     * 
     * @return size of the list.
     */
    public int size() {
        return size;
    }
    
    /**
     * Inner class that returns an iterator over the elements
     * in the list in the proper sequence.
     * 
     * @return an iterator for iterating through the list of elements.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            
            /**
             * Returns true if there are more elements to iterate over.
             * 
             * @return true if there are more elements to iterate over.
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            /**
             * Returns the next element in the iteration.
             * 
             * @return the next element in the list.
             * @throws NoSuchElementException if no more elements remain.
             */
            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}

