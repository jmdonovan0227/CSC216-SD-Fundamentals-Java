/**
 * 
 */
package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionality of the SwapList class to make sure everything is functioning as required
 * @author Jake Donovan
 *
 */
class SwapListTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#SwapList()}.
	 */
	@Test
	void testSwapList() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#add(java.lang.Comparable)}.
	 */
	@Test
	void testAdd() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#remove(int)}.
	 */
	@Test
	void testRemove() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		swapList.remove(0);
		assertEquals(2, swapList.size());
		swapList.remove(1);
		assertEquals(1, swapList.size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#moveUp(int)}.
	 */
	@Test
	void testMoveUp() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		swapList.moveUp(1);
		assertEquals("B", swapList.get(0));
		assertEquals("A", swapList.get(1));
		assertEquals("C", swapList.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#moveDown(int)}.
	 */
	@Test
	void testMoveDown() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		swapList.moveDown(1);
		assertEquals("A", swapList.get(0));
		assertEquals("C", swapList.get(1));
		assertEquals("B", swapList.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#moveToFront(int)}.
	 */
	@Test
	void testMoveToFront() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		swapList.moveToFront(2);
		assertEquals("C", swapList.get(0));
		assertEquals("A", swapList.get(1));
		assertEquals("B", swapList.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#moveToBack(int)}.
	 */
	@Test
	void testMoveToBack() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		swapList.moveToBack(0);
		assertEquals("B", swapList.get(0));
		assertEquals("C", swapList.get(1));
		assertEquals("A", swapList.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#get(int)}.
	 */
	@Test
	void testGet() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		assertEquals("A", swapList.get(0));
		assertEquals("B", swapList.get(1));
		assertEquals("C", swapList.get(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_tasks.model.util.SwapList#size()}.
	 */
	@Test
	void testSize() {
		ISwapList<String> swapList = new SwapList<String>();
		assertEquals(0, swapList.size());
		swapList.add("A");
		assertEquals(1, swapList.size());
		swapList.add("B");
		assertEquals(2, swapList.size());
		swapList.add("C");
		assertEquals(3, swapList.size());
		swapList.add("D");
		assertEquals(4, swapList.size());
		swapList.add("E");
		assertEquals(5, swapList.size());
		swapList.add("F");
		assertEquals(6, swapList.size());
	}

}
