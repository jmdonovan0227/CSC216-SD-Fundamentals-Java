package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class for a list that keeps objects in sorted order as defined by the
 * Comparable interface.
 * @author Jake Donovan
 *
 * @param <E> type for the SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** The size of the sorted list */
	private int size;
	/** A reference to the front of list */
	private ListNode front;
	
	/**
	 * Constructs a new SortedList of elements
	 */
	public SortedList() {
		this.front = null;
		this.size = 0;
	}
	
	/**
	 * Adds the element to the list in sorted order
	 * @param element the element to add
	 * @throws IllegalArgumentException if the element is null or a duplicate element
	 */
	@Override
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if(front == null || element.compareTo(front.data) < 0) {
			front = new ListNode(element, front);
		}
		
		else {
			boolean duplicate = false;
			ListNode checkDuplicates = front;
			
			for(int i = 0; i < size; i++) {
				if(checkDuplicates.data == element) {
					duplicate = true;
					break;
				}
				
				else {
					checkDuplicates = checkDuplicates.next;
				}
			}
			
			if(duplicate) { // If a duplicate is found
				throw new IllegalArgumentException("Cannot add duplicate element.");
			}
			
			else { // Find where the element needs to be inserted
				ListNode current = front;
				while(current.next != null && current.next.data.compareTo(element) < 0) {
					current = current.next;
				}
				current.next = new ListNode(element, current.next);
			}
		}
		
		size++;
	}
	
	/**
	 * Returns the element from the given index. The element is also removed
	 * from the list
	 * @param idx the index to remove the element from
	 * @return E an element at a given index
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E value = null; // reference to removed element
		if(idx == 0) { // Special case at the front of list
			value = front.data; // set value as the value that will be removed
			front = front.next; // modify reference to front and remove current reference to front by moving to next node and remove element with it
		}
		
		else {
			ListNode current = front;
			for(int i = 0; i < idx - 1; i++) { // Stop right before the index we want to remove
				current = current.next;
			}
			value = current.next.data; // set value as the current.next.data which is the element at the index we want to remove
			current.next = current.next.next; // move current.next to next node removing the reference
		}
		size--; // decrement size
		return value; // return removed value
	}
	
	/**
	 * Helps check for valid indexes within SortedList
	 * @param idx a possibly valid index that can be checked for validity in this helper method
	 * @throws IndexOutOfBoundsException if the index is out of bounds aka less than 0 or greater than size - 1
	 */
	private void checkIndex(int idx) {
		// Valid bounds = 0 <= idx < size()
		if(idx < 0 || idx > size() - 1) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Returns true if the element is in the list
	 * @param element the element to search for
	 * @return true if an element is found otherwise return false
	 */
	@Override
	public boolean contains(E element) {
		boolean contains = false;
		
		if(front == null) { // list is empty
			return contains;
		}
		
		else { // check list to see if there is a matching element
			ListNode current = front;
			
			if(current.data.equals(element)) { // element at front of list is the matching element
				contains = true;
				return contains;
			}
			
			else {
				while(current.next != null) {
					current = current.next; // assign current to the next value
					
					if(current.data.equals(element)) { // if new current reference aka the next element in list's data equals the element
						// break the loop at set contains = true because we have found the matching element
						contains = true;
						break;
					}
			    }
			}
		}
		
		return contains;
	}
	
	/**
	 * Return the element at a given index
	 * @param idx the index of the element to retrieve
	 * @return E an element at a given index if it exists otherwise return null
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
		for(int i = 0; i < idx; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	/**
	 * Returns the number of elements in the SortedList
	 * @return size the number of elements in the SortedList
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * This inner class is responsible for creating nodes that can be used to correctly sort our lists with our SortedList implentation
	 * @author Jake Donovan
	 *
	 */
	public class ListNode {
		/** the data value for a listnode aka an element */
		public E data;
		/** A pointer that points to the next node*/
		public ListNode next;
		
		/**
		 * Constructs a ListNode with data value and next node pointer
		 * @param data the data value of the listnode aka the element
		 * @param next the pointer that points to the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.next = next;
			this.data = data;
		}
	}

}
