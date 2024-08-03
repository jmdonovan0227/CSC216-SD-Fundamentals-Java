/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class for a list that changes the position of elements through swap operations
 * @author Jake Donovan
 *
 * @param <E> type for SwapList
 */
public class SwapList<E> implements ISwapList<E> {
	/** The initial capacity of list, which will grow as needed */
	private static final int INITIAL_CAPACITY = 10;
	/** A list of type E which holds elements that can be moved within list through swap operations */
	private E[] list;
	/** The number of elements within list */
	private int size;
	
	
	/**
	 * Constructs a new SwapList
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		this.list = (E[]) new Object[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	/**
	 * Adds an element to the end of the list
	 * @param element the element to add
	 * @throws IllegalArgumentException if the element is null
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		checkCapacity(size + 1); // Check capacity to make sure that element can be added at back of list aka at size
		list[size++] = element; // Add element at size and increment size value
	}
	
	/**
	 * Checks capacity of list, and increases the capacity of the list as more elements are added
	 * @param capacity the current capacity of the list
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int capacity) {
		if(capacity >= list.length) {
			E[] resizedArray = (E[]) new Object[capacity * 2];
			
			for(int i = 0; i < list.length; i++) {
				resizedArray[i] = list[i];
			}
			
			list = resizedArray;
		}
	}
	
	/**
	 * Returns the element from the given index. The element is also removed from the list
	 * @param idx the index of the element that will be removed (if a valid index)
	 * @return E the element at the given index
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E v = list[idx];
		
		// Left shift elements
		for(int i = idx; i < size() - 1; i++) {
			list[i] = list[i + 1];
		}
		
		list[size - 1] = null;
		size--;
		return v;
	}
	
	/**
	 * Helper method that checks indexes passed from public methods to determine whether an index is valid,
	 * so that valid operations can be performed within the SwapList
	 * @param idx the index the will be checked for validity
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	private void checkIndex(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Moves the element at the given index to index-1. If the element is already at the front of the list,
	 * the list is not changed
	 * @param idx the index of the element to move up
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx); // Makes sure idx is valid (not less than 0 or greater than size of list)
		
		if(idx == 0) { // Trying to move the element at the front of the list, just return aka do nothing
			return;
		}
		
		else { // Not at front of list, so move up within list
			E element = list[idx]; // Save value at index as a temp variable
			list[idx] = list[idx - 1]; // Swap values at index the value at idx is being moved to (For example if we have a list,
			// that has A, B, C and we want to move B up to the front it is at index 1 so we want list to turn into B, A, C so first swap A and B = A, A, C
			list[idx - 1] = element; // Assign the position where want to move the element as the temp variable aka the value we wanted to move, so the list would 
			// turn into B, A, C if we had a list A, B, C that we then called this method on = B, A, C if we wanted to move the element at idx 1 aka B
		}
	}
	
	/**
	 * Moves the element at the given index to index+1. If the element is already at the end of the list,
	 * the list is not changed.
	 * @param idx the index of the element to move down
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		
		if(idx == size() - 1) { // Trying to move element at the end of the list just return in this case aka do nothing
			return;
		}
		
		else { // Otherwise move the element down within the list
			E element = list[idx]; // save element to be moved as temp
			list[idx] = list[idx + 1]; // swap the element at idx val and element below values the current idx val is lost
			list[idx + 1] = element; // Assign the the element at idx + 1 with the temp value aka the value we wanted to move
			// If we had a list A, B, C and we called this method on the list at the idx 1 (B), the list would become A, C, B
		}
	}
	
	/**
	 * Moves the element at an index to index 0. If the element is already at front of the list,
	 * the list is not changed.
	 * @param idx the index of the element to move to the front
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		
		if(idx == 0) { // element that is trying to be moved is already at the front of the list, just return aka do nothing
			return;
		}
		
		else { // Otherwise move the element to the front of the list
			for(int i = idx; (i - 1) >= 0; i--) {
				E element = list[i];
				list[i] = list[i - 1];
				list[i - 1] = element;
			}
		}
	}
	
	/**
	 * Moves an element at the given index to size-1. If the element is already at the end of the list,
	 * the list is not changed.
	 * @param idx the index of the element to move to the end of the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		
		if(idx == size() - 1) { // index of element that is trying to be moved is already at the back of the list, just return aka do nothing
			return;
		}
		
		else { // Otherwise move the element to the back of the list
			for(int i = idx; (i + 1) <= (size() - 1); i++) {
				E element = list[i];
				list[i] = list[i + 1];
				list[i + 1] = element;
			}
		}
	}
	
	/**
	 * Returns the element at a given index
	 * @param idx the index of the element to be retrieved from the list
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}
	
	/**
	 * Returns the number of elements in the list
	 * @return size the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

}
