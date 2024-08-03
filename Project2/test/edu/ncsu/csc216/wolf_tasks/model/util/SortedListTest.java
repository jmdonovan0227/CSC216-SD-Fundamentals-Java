/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the SortedList class to make sure everything functions as required
 * @author Jake Donovan
 *
 */
class SortedListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SortedList#SortedList()}.
	 */
	@Test
	void testSortedList() {
		SortedList<String> sortedList = new SortedList<String>();
		assertEquals(0, sortedList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SortedList#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		SortedList<String> sortedList = new SortedList<String>();
		assertEquals(0, sortedList.size());
		sortedList.add("A");
		assertEquals(1, sortedList.size());
		sortedList.add("B");
		assertEquals(2, sortedList.size());
		sortedList.add("D");
		assertEquals(3, sortedList.size());
		sortedList.add("C");
		assertEquals(4, sortedList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SortedList#remove(int)}.
	 */
	@Test
	void testRemove() {
		SortedList<String> sortedList = new SortedList<String>();
		assertEquals(0, sortedList.size());
		sortedList.add("A");
		assertEquals(1, sortedList.size());
		sortedList.add("B");
		assertEquals(2, sortedList.size());
		sortedList.add("D");
		assertEquals(3, sortedList.size());
		sortedList.add("C");
		assertEquals(4, sortedList.size());
		sortedList.remove(0);
		assertEquals(3, sortedList.size());
		assertEquals("B", sortedList.get(0));
		sortedList.remove(2);
		assertEquals(2, sortedList.size());
		assertEquals("C", sortedList.get(1));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SortedList#contains(java.lang.Comparable)}.
	 */
	@Test
	void testContains() {
		SortedList<String> sortedList = new SortedList<String>();
		assertEquals(0, sortedList.size());
		sortedList.add("A");
		assertEquals(1, sortedList.size());
		sortedList.add("B");
		assertEquals(2, sortedList.size());
		assertTrue(sortedList.contains("A"));
		assertTrue(sortedList.contains("B"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SortedList#get(int)}.
	 */
	@Test
	void testGet() {
		SortedList<String> sortedList = new SortedList<String>();
		assertEquals(0, sortedList.size());
		sortedList.add("A");
		assertEquals(1, sortedList.size());
		sortedList.add("B");
		assertEquals(2, sortedList.size());
		sortedList.add("D");
		assertEquals(3, sortedList.size());
		sortedList.add("C");
		assertEquals(4, sortedList.size());
		assertEquals("A", sortedList.get(0));
		assertEquals("B", sortedList.get(1));
		assertEquals("C", sortedList.get(2));
		assertEquals("D", sortedList.get(3));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SortedList#size()}.
	 */
	@Test
	void testSize() {
		SortedList<String> sortedList = new SortedList<String>();
		assertEquals(0, sortedList.size());
		sortedList.add("A");
		assertEquals(1, sortedList.size());
		sortedList.add("B");
		assertEquals(2, sortedList.size());
		sortedList.add("D");
		assertEquals(3, sortedList.size());
		sortedList.add("C");
		assertEquals(4, sortedList.size());
		sortedList.add("E");
		assertEquals(5, sortedList.size());
		sortedList.add("F");
		assertEquals(6, sortedList.size());
	}

}
