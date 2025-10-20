package project02;

import java.util.Iterator;

/**
 * Interface that defines generic list operations: adding,
 * accessing, removing, retrieving the number of elements in the
 * list.
 * 
 * @param <T> the type of elements stored in the list.
 * 
 * @Author: Oliver Reyes
 * @version: 1.0
 */
public interface List<T> extends Iterable<T>{
	
    /**
     * Adds an item at the specified position in the list.
     * 
     * @param pos the index to insert the item.
     * @item item the item to be added.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public void add (int index, T element) throws IndexOutOfBoundsException;
    
    /**
     * Adds the item at the end of the list.
     * 
     * @return returns true if the item is added successfully.
     */
    public boolean add (T element);
    
    /**
     * Gets the value at the specified position of the list.
     * 
     *  @param pos the index of the value to return.
     *  @return the value at the specified position.
     *  @throws IndexOutofBoundsException if the given position is 
     *  out of range.
     */
    public T get (int index) throws IndexOutOfBoundsException;
    
    /**
     * Removes and returns the element at the specified position.
     * 
     * @param pos the index of the element to remove.
     * @return the item removed.
     * @throws IndexOutOfBoundsException if the given position is 
     * out of range.
     */
    public T remove (int index) throws IndexOutOfBoundsException;
    
    /**
     * Returns the number of items currently stored in the list.
     * 
     * @return size of the list.
     */
    public int size ();
    
    /**
     * Anonymous inner class that returns an iterator over the elements
     * in the list in the proper sequence.
     * 
     * @return an iterator for iterating through the list of elements.
     */
    public Iterator<T> iterator();
}
