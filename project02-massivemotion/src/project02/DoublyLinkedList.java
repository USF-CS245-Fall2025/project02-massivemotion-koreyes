package project02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implements a doubly linked list, a variation of
 * a regular linked list, with each node storing reference to both
 * its previous and next neighbor.
 * 
 * @param <T> the type of elements stored in the list.
 * 
 * @Author: Oliver Reyes
 * @version 1.0
 */
public class DoublyLinkedList<T> implements List<T>, Iterable<T> {
	
    /**
     * A private static inner class representing a node in the linked list.
     */
    private static class Node<T> {
        T data;
        Node<T> next, prev;
        
        Node(T value) { 
        	data = value; 
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    /**
     * Adds an item at the specified position in the list.
     * 
     * @param pos the index to insert the item.
     * @item item the item to be added.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public void add(int pos, T item) {
        if (pos < 0 || pos > size) {
        	throw new IndexOutOfBoundsException();
        }
        
        if (pos == size) {
            add(item);
            return;
        }

        Node<T> curr = head;
        for (int i = 0; i < pos; i++) {
            curr = curr.next;
        }
        
        Node<T> node = new Node<>(item);
        node.next = curr;
        node.prev = curr.prev;

        if (curr.prev != null) {
            curr.prev.next = node;
        } else {
            head = node;
        }
        
        curr.prev = node;
        size++;
    }
    
    /**
     * Adds the item at the end of the list or at head and tail if
     * list is empty.
     * 
     * @return returns true if the item is added successfully.
     */
    public boolean add(T item) {
        Node<T> node = new Node<>(item);

        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
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
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<T> curr = head;
        for (int i = 0; i < pos; i++) {
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
    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> curr = head;
        for (int i = 0; i < pos; i++) {
            curr = curr.next;
        }

        if (curr.prev != null) {
            curr.prev.next = curr.next;
        } else {
            head = curr.next;
        }
        
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        } else {
            tail = curr.prev;
        }
        
        size--;
        return curr.data;
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
     * Anonymous inner class that returns an iterator over the elements
     * in the list in the proper sequence.
     * 
     * @return an iterator for iterating through the list of elements.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            
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
