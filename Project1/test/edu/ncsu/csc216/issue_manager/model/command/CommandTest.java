/**
 *
 */
package edu.ncsu.csc216.issue_manager.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * Tests the Command class
 * @author Jake Donovan
 *
 */
class CommandTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.command.Command#Command(edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue, java.lang.String, edu.ncsu.csc216.issue_manager.model.command.Command.Resolution, java.lang.String)}.
	 */
	@Test
	void testCommand() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> new Command(null, "", Resolution.DUPLICATE, "a note"));
		assertEquals("Invalid information.", e.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, "", Resolution.DUPLICATE, "A note"));
		assertEquals("Invalid information.", e2.getMessage());
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.RESOLVE, "", null, "A note"));
		assertEquals("Invalid information.", e3.getMessage());
        Exception e4 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, null, Resolution.DUPLICATE, "A note"));
        assertEquals("Invalid information.", e4.getMessage());
        Exception e5 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, "owner", Resolution.DUPLICATE, null));
        assertEquals("Invalid information.", e5.getMessage());
        Exception e6 = assertThrows(IllegalArgumentException.class, () -> new Command(CommandValue.ASSIGN, "owner", Resolution.DUPLICATE, ""));
        assertEquals("Invalid information.", e6.getMessage());
        Command c = new Command(CommandValue.ASSIGN, "owner", Resolution.DUPLICATE, "A note");
        assertEquals(CommandValue.ASSIGN, c.getCommand());
        assertEquals("owner", c.getOwnerId());
        assertEquals(Resolution.DUPLICATE, c.getResolution());
        assertEquals("A note", c.getNote());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.command.Command#getCommand()}.
	 */
	@Test
	void testGetCommand() {
		Command c = new Command(CommandValue.ASSIGN, "owner", Resolution.DUPLICATE, "A note");
		assertEquals(CommandValue.ASSIGN, c.getCommand());
		Command c2 = new Command(CommandValue.CONFIRM, "owner", Resolution.DUPLICATE, "A note");
		assertEquals(CommandValue.CONFIRM, c2.getCommand());
		Command c3 = new Command(CommandValue.REOPEN, "owner", Resolution.DUPLICATE, "A note");
		assertEquals(CommandValue.REOPEN, c3.getCommand());
		Command c4 = new Command(CommandValue.RESOLVE, "owner", Resolution.DUPLICATE, "A note");
		assertEquals(CommandValue.RESOLVE, c4.getCommand());
		Command c5 = new Command(CommandValue.VERIFY, "owner", Resolution.DUPLICATE, "A note");
		assertEquals(CommandValue.VERIFY, c5.getCommand());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.command.Command#getOwnerId()}.
	 */
	@Test
	void testGetOwnerId() {
		Command c = new Command(CommandValue.ASSIGN, "owner", Resolution.DUPLICATE, "A note");
		assertEquals("owner", c.getOwnerId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.command.Command#getResolution()}.
	 */
	@Test
	void testGetResolution() {
		Command c = new Command(CommandValue.ASSIGN, "owner", Resolution.FIXED, "A note");
		assertEquals(Resolution.FIXED, c.getResolution());
		Command c2 = new Command(CommandValue.ASSIGN, "owner", Resolution.WONTFIX, "A note");
		assertEquals(Resolution.WONTFIX, c2.getResolution());
		Command c3 = new Command(CommandValue.ASSIGN, "owner", Resolution.WORKSFORME, "A note");
		assertEquals(Resolution.WORKSFORME, c3.getResolution());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.issue_manager.model.command.Command#getNote()}.
	 */
	@Test
	void testGetNote() {
		Command c = new Command(CommandValue.ASSIGN, "owner", Resolution.DUPLICATE, "A note");
		assertEquals("A note", c.getNote());
	}
}
