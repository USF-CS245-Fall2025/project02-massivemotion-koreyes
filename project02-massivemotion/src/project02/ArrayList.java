package project02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that mimics the built-in ArrayList, maintaining an array that
 * automatically grows when its capacity is full; supporting general
 * list behaviors such as indexed access, insertion, removal, and
 * iteration.
 * 
 * @param <T> the type of elements stored in the list.
 * 
 * @Author: Oliver Reyes
 * @version 1.0
 */
public class ArrayList<T> implements List<T>, Iterable<T> {
	private T[] arr;
    private int size;
    
    /**
     * Constructs an empty ArrayList with an initial capacity of 10.
     */
    public ArrayList() {
        size = 0;
        arr = (T[]) new Object[10];
    }
    
    /**
     * Doubles the capacity of the array when the list is full.
     */
    private void grow() {
        T[] newArr = (T[]) new Object[arr.length * 2];
        
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        
        arr = newArr;
    }
    
    /**
     * Adds the item to a specified position in the list,
     * while shifting subsequent values to the right.
     * 
     * @param pos the index to insert the item.
     * @param item the item to be added.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public void add(int pos, T item) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > size) {
        	throw new IndexOutOfBoundsException();
        }
        
        if (size == arr.length) {
            grow();
        }
        
        for (int i = size; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        
        arr[pos] = item;
        size++;
    }
    
    /**
     * Adds the item at the end of the list.
     * 
     * @return returns true if the item is added successfully.
     */
    public boolean add(T item) {
        if (size == arr.length) {
            grow();
        }
        
        arr[size++] = item;
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
    public T get(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        return arr[pos];
    }
    
    /**
     * Removes and returns the item at the specified position in the
     * list, shifting subsequent elements to the left.
     * 
     * @param pos the index of the element to remove.
     * @return the item removed.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public T remove(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
        	throw new IndexOutOfBoundsException();
        }
        
        T copy = arr[pos];
        for (int i = pos; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        
        size--;
        return copy;
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
            private int index = 0;
            
            /**
             * Returns true if there are more elements to iterate over.
             * 
             * @return true if there are more elements to iterate over.
             */
            @Override
            public boolean hasNext() {
                return index < size;
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
                
                return arr[index++];
            }
        };
    }

}
