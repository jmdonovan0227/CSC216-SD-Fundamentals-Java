package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * This class is responsible for creating valid issues and calling Command class to be able to correctly
 * transition each Issue to the correct state with each passed Command values
 * @author Jake Donovan
 *
 */
public class Issue {
		/** The String representation of IssueType enhancement */
		public static final String I_ENHANCEMENT = "Enhancement";
		/** The String representation of the IssueType enhancement as a String */
		public static final String I_BUG = "Bug";
		/** An issue's id */
		private int issueId;
		/** An issue's summary */
		private String summary;
		/** An issue's owner */
		private String owner;
		/** An issue's confirmed status */
		private boolean confirmed;
		/** An issue's note list */
		private ArrayList<String> notes;
		/** An issue's resolution code */
		private Resolution resolution;
		/** An issue's type*/
		private IssueType issueType;
		/** The String representation of NewState */
		public static final String NEW_NAME = "New";
		/** The String representation of WorkingState */
		public static final String WORKING_NAME = "Working";
		/** The String representation of ConfirmedState */
		public static final String CONFIRMED_NAME = "Confirmed";
		/** The String representation of VerifyingState */
		public static final String VERIFYING_NAME = "Verifying";
		/** The String representation of ClosedState */
		public static final String CLOSED_NAME = "Closed";
		/** An issue's current state */
		private IssueState state;
		/** A constructed NewState object */
		private final NewState newState = new NewState();
		/** A constructed WorkingState object */
		private final WorkingState workingState = new WorkingState();
		/** A constructed ConfirmedState object */
		private final ConfirmedState confirmedState = new ConfirmedState();
		/** A constructed VerifyingState object */
		private final VerifyingState verifyingState = new VerifyingState();
		/** A constructed ClosedState object */
		private final ClosedState closedState = new ClosedState();


		/**
		 * Issue class constructor one which used passed parameters for id, issueType, summary, and a note
		 * @param id the issue's id
		 * @param issueType the issue's type
		 * @param summary the issue's summary
		 * @param note the issue's note
		 * @throws IllegalArgumentException if any of the parameters are null or empty Strings
		 */
		public Issue(int id, IssueType issueType, String summary, String note) {
			if(issueType == null || note == null || "".equals(note) || summary == null || "".equals(summary) || id <= 0) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
			
			else {
				this.issueId = id;
			    this.summary = summary;
			    this.issueType = issueType;
			    this.state = newState;
			    this.notes = new ArrayList<String>();
			    addNote(note);
			    this.resolution = null;
			    this.owner = null;
			    this.confirmed = false;
			}
		}

		/**
		 * Issue class constructor 2 which uses parameter for id, state, issueType, summary, owner, confirmed status, resolution code,
		 * and a list of notes
		 * @param id the issue's id
		 * @param state the issue's state
		 * @param issueType the issue's type
		 * @param summary the issue's summary
		 * @param owner the issue's owner
		 * @param confirmed the issue's confirmed status
		 * @param resolution the issue's resolution code
		 * @param notes the issue's list of notes
		 */
		public Issue(int id, String state, String issueType, String summary, String owner, boolean confirmed, String resolution, ArrayList<String> notes) {
			setIssueId(id);
			setState(state);
			setIssueType(issueType);
			setSummary(summary);
			setOwner(owner);
			setConfirmed(confirmed);
			setResolution(resolution);
			setNotes(notes);
			setConfirmed(confirmed);
			setNotes(notes);
		}

		/**
		 * Sets issue's id
		 * @param id the issue's id
		 * @throws IllegalArgumentException if the id is less than or equal to 0
		 */
		private void setIssueId(int id) {
			if(id <= 0) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}

			this.issueId = id;
		}

		/**
		 * Sets issue's state
		 * @param stateString the issue's state as a String
		 * @throws IllegalArgumentException if state string is null or an empty string, and if a valid state name is not found
		 */
		private void setState(String stateString) {
			if(stateString == null || "".equals(stateString)) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}

			else if(NEW_NAME.equals(stateString)) {
				this.state = newState;
			}

			else if(WORKING_NAME.equals(stateString)) {
				this.state = workingState;
			}

			else if(CONFIRMED_NAME.equals(stateString)) {
				this.state = confirmedState;
			}

			else if(VERIFYING_NAME.equals(stateString)) {
				this.state = verifyingState;
			}

			else if(CLOSED_NAME.equals(stateString)) {
				this.state = closedState;
			}
			
			else {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
		}

		/**
		 * Sets the issue's type
		 * @param issueType the issue's type
		 * @throws IllegalArgumentException if issueType is null or an empty string, or if a valid issue type is not found
		 */
		private void setIssueType(String issueType) {
			if(issueType == null || "".equals(issueType)) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}

			else if(I_BUG.equals(issueType)) {
				this.issueType = IssueType.BUG;
			}

			else if(I_ENHANCEMENT.equals(issueType)) {
				this.issueType = IssueType.ENHANCEMENT;
			}
			
			else {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
		}

		/**
		 * Sets the issue's summary
		 * @param summary the issue's summary
		 * @throws IllegalArgumentException if the summary is null or an empty string
		 */
		private void setSummary(String summary) {
			if(summary == null || "".equals(summary)) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}

			this.summary = summary;
		}

		/**
		 * Sets the issue's owner
		 * @param owner the issue's owner
		 * @throws IllegalArgumentException if owner is null or an empty string in the working or verifying state,
		 * owner is null and the state is not verifying or working state
		 */
		private void setOwner(String owner) {
			if(("".equals(owner) || owner == null) && (state == workingState || state == verifyingState)) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
			
			
			else if(owner == null && (state != workingState || state != verifyingState)) {
				throw new IllegalArgumentException("Issue cannot be created.");
		    }
			
			else if("".equals(owner) && (state != workingState || state != verifyingState)) {
				this.owner = null;
				return;
			}
			
			else {
				this.owner = owner;
			}
		}

		/**
		 * Sets the issue's confirmed status
		 * @param confirmed the issue's confirmed status
		 * @throws IllegalArgumentException if the issue type is bug and is in the working state and confirmed is false,
		 * if the issue type is enhancement and confirmed is true
		 */
		private void setConfirmed(boolean confirmed) {
			if(issueType == IssueType.BUG && !confirmed && state == workingState) {
				// a bug that is in the working state has to be confirmed
				throw new IllegalArgumentException("Issue cannot be created.");
			}
		
			else if(issueType == IssueType.ENHANCEMENT && confirmed) {
				// Enhancements cannot be confirmed!
				throw new IllegalArgumentException("Issue cannot be created.");
			}
			
			else {
				this.confirmed = confirmed;
			}
		}

		/**
		 * Sets the issue's resolution code
		 * @param resolutionString the issue's resolution code as a String
		 * @throws IllegalArgumentException if the the resolution string is empty while in the closed state,
		 * if the a command other than fixed is received in verifying state, and if a valid conditional is not found aka everything else was false
		 */
		private void setResolution(String resolutionString) {
			if(Command.R_DUPLICATE.equals(resolutionString)) {
				this.resolution = Resolution.DUPLICATE;
				return;
			}

			else if(Command.R_FIXED.equals(resolutionString)) {
				this.resolution = Resolution.FIXED;
				return;
			}

			else if(Command.R_WONTFIX.equals(resolutionString)) {
				this.resolution = Resolution.WONTFIX;
				return;
			}

			else if(Command.R_WORKSFORME.equals(resolutionString)) {
				this.resolution = Resolution.WORKSFORME;
				return;
			}
			
			else if("".equals(resolutionString) && state == closedState) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
			
			else if(!Command.R_FIXED.equals(resolutionString) && state == verifyingState) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
			
			else if("".equals(resolutionString) && state != closedState) {
				this.resolution = null;
			}
			
			else {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
		}

		/**
		 * Sets the issue's notes
		 * @param notes the issue's notes
		 * @throws IllegalArgumentException if notes are null or if notes are empty (passed notes)
		 */
		private void setNotes(ArrayList<String> notes) {
			if(notes == null || notes.isEmpty()) {
				throw new IllegalArgumentException("Issue cannot be created.");
			}
			
			else {
				this.notes = notes;
			}
		}

		/**
		 * Gets the issue's id
		 * @return id the issue's id
		 */
		public int getIssueId() {
			return issueId;
		}

		/**
		 * Gets the issue's state name
		 * @return String the issue's state name as a String
		 */
		public String getStateName() {
			if(state == newState) {
				return NEW_NAME;
			}

			else if(state == workingState) {
				return WORKING_NAME;
			}

			else if(state == confirmedState) {
				return CONFIRMED_NAME;
			}

			else if(state == verifyingState) {
				return VERIFYING_NAME;
			}

			else if(state == closedState) {
				return CLOSED_NAME;
			}

			else {
				return null;
			}
		}

		/**
		 * Gets the issue's type as a String
		 * @return String the issue's type as a String
		 */
		public String getIssueType() {
			if(issueType == IssueType.ENHANCEMENT) {
				return I_ENHANCEMENT;
			}

			else if(issueType == IssueType.BUG) {
				return I_BUG;
			}

			else {
				return null;
			}
		}

		/**
		 * Gets the issue's resolution code as a String
		 * @return String the issue's resolution code as a String
		 */
		public String getResolution() {
			if(resolution == Resolution.DUPLICATE) {
				return Command.R_DUPLICATE;
			}

			else if(resolution == Resolution.FIXED) {
				return Command.R_FIXED;
			}

			else if(resolution == Resolution.WONTFIX) {
				return Command.R_WONTFIX;
			}

			else if(resolution == Resolution.WORKSFORME) {
				return Command.R_WORKSFORME;
			}

			else {
				return null;
			}
		}

		/**
		 * Gets the issue's owner
		 * @return owner the issue's owner
		 */
		public String getOwner() {
			return owner;
		}

		/**
		 * Gets the issue's summary
		 * @return summary the issue's summary
		 */
		public String getSummary() {
			return summary;
		}

		/**
		 * Gets the issue's notes
		 * @return notes the issue's notes
		 */
		public ArrayList<String> getNotes(){
			return notes;
		}

		/**
		 * Gets the issue's notes as a String
		 * @return String the issue's notes as a String
		 */
		public String getNotesString() {
			String allNotes = "";

			if(!notes.isEmpty()) {
				for (int i = 0; i < notes.size(); i++) {
					allNotes += "-" + notes.get(i) + "\n";
				}
			}

			return allNotes;
		}

		/**
		 * Gets the issue's confirmed status
		 * @return confirmed the issue's confirmed status
		 */
		public boolean isConfirmed() {
			return confirmed;
		}

		/**
		 * Returns String representation of class
		 * @return String the String representation of class
		 */
		@Override
		public String toString() {
		   if(getResolution() == null) {
			   return getIssueId() + "," +  getStateName() + "," + getIssueType() + "," + getSummary() + "," + getOwner() + "," + Boolean.toString(isConfirmed()) + ",";
		   }
		   
		   else {
			   return getIssueId() + "," +  getStateName() + "," + getIssueType() + "," + getSummary() + "," + getOwner() + "," + Boolean.toString(isConfirmed()) + "," + getResolution();
		   }
		}

		/**
		 * Adds a note to note list
		 * @param note the note to be added to list
		 * @throws IllegalArgumentException if notes is null or notes is an empty string
		 */
		private void addNote(String note) {
			// Handles the cases when new issues are created, and
			// when Issue notes are being added from a file to make sure their notes
			// aren't null or empty
			if(note == null || "".equals(note)) {
				throw new IllegalArgumentException("Invalid information.");
			}

			String formattedNote = "";

			formattedNote = "[" + getStateName() + "] " + note;

			notes.add(formattedNote);
		}

		/**
		 * Updates command
		 * @param c the passed Command to update issue state
		 * @throws UnsupportedOperationException if an invalid Command for a state is received
		 */
		public void update(Command c) {
			state.updateState(c);
		}

		/**
		 * The IssueTypes which include enhancement and bug
		 * @author Jake Donovan
		 *
		 */
		public enum IssueType { ENHANCEMENT, BUG }

		/**
		 * The IssueState interface which will be implemented by all state classes
		 * which relate to the IssueState that an issue is in
		 * @author Jake Donovan
		 *
		 */
		public interface IssueState {
			/** Updates an issue's state (contract)
			 * @param c the Command
			 */
			void updateState(Command c);
			/** Gets an issue's state name (contract)
			 * @return String the state's name
			 */
			String getStateName();
		}

		/**
		 * This class is responsible for creating a NewState object and correctly updating
		 * an issue in the NewState
		 * @author Jake Donovan
		 *
		 */
		public class NewState implements IssueState {
			/**
			 * New State Class Constructor
			 */
			private NewState() {
				//
			}
			/**
			 * Updates an issue in the NewState with a passed Command value
			 * @param c the Command value
			 * @throws UnsupportedOperationException if a valid command in New State is not found
			 */
			@Override
			public void updateState(Command c) {
				if(issueType == IssueType.ENHANCEMENT && c.getCommand() == CommandValue.ASSIGN) {
					setOwner(c.getOwnerId());
					state = workingState;
					addNote(c.getNote());
					return;
				}
				
				else if(issueType == IssueType.BUG && c.getCommand() == CommandValue.CONFIRM) {
					setConfirmed(true);
					state = confirmedState;
					addNote(c.getNote());
					return;
				}
				
				else if(c.getCommand() == CommandValue.RESOLVE && issueType == IssueType.BUG && c.getResolution() != Resolution.FIXED) {
					resolution = c.getResolution();
					state = closedState;
					addNote(c.getNote());
					return;
				}
				
				else if(c.getCommand() == CommandValue.RESOLVE && issueType == IssueType.ENHANCEMENT && c.getResolution() != Resolution.WORKSFORME && c.getResolution() != Resolution.FIXED) {
					resolution = c.getResolution();
					state = closedState;
					addNote(c.getNote());
					return;
				}
				
				else {
					throw new UnsupportedOperationException("Invalid information.");
				}
			}

			/**
			 * Gets the current state's name and returns as a String
			 */
			@Override
			public String getStateName() {
				return NEW_NAME;
			}

		}

		/** This class is responsible for creating a WorkingState object and correctly updating
		 * an issue in the WorkingState
		 * @author Jake Donovan
		 *
		 */
		public class WorkingState implements IssueState {
			/**
			 *  Working State Constructor
			 */
			private WorkingState() {
				//
			}

			/**
			 * Updates an issue in the WorkingState with a passed Command value
			 * @param c the Command value
			 * @throws UnsupportedOperationException if a valid Command is not found for Working State
			 */
			@Override
			public void updateState(Command c) {
				if(c.getCommand() == CommandValue.RESOLVE) {
					if(c.getResolution() == Resolution.FIXED) {
						resolution = c.getResolution();
						state = verifyingState;
						addNote(c.getNote());
					}

					else if(issueType == IssueType.ENHANCEMENT && (c.getResolution() == Resolution.DUPLICATE || c.getResolution() == Resolution.WONTFIX)){
						resolution = c.getResolution();
						state = closedState;
						addNote(c.getNote());
					}

					else if(issueType == IssueType.BUG && (c.getResolution() == Resolution.DUPLICATE || c.getResolution() == Resolution.WONTFIX
							|| c.getResolution() == Resolution.WORKSFORME)) {
						resolution = c.getResolution();
						state = closedState;
						addNote(c.getNote());
					}
					
					else {
						throw new UnsupportedOperationException("Invalid information.");
					}
				}

				else {
					throw new UnsupportedOperationException("Invalid information.");
				}
			}

			/**
			 * Gets the current state's name as a String
			 */
			@Override
			public String getStateName() {
				return WORKING_NAME;
			}
		}

		/** This class is responsible for creating a ConfirmedState object and correctly updating
		 * an issue in the ConfirmedState
		 * @author Jake Donovan
		 *
		 */
		public class ConfirmedState implements IssueState {
			/**
			 * ConfirmedState constructor
			 */
			private ConfirmedState() {
				//
			}

			/**
			 * Updates an issue the ConfirmedState with a passed Command value
			 * @param c the Command value
			 * @throws UnsupportedOperationException if a valid command is not found for Confirmed state
			 */
			@Override
			public void updateState(Command c) {
				if(c.getCommand() == CommandValue.ASSIGN) {
					setOwner(c.getOwnerId());
					state = workingState;
					addNote(c.getNote());
				}

				else if(c.getCommand() == CommandValue.RESOLVE && c.getResolution() == Resolution.WONTFIX) {
					resolution = c.getResolution();
					state = closedState;
					addNote(c.getNote());
				}

				else {
					throw new UnsupportedOperationException("Invalid information.");
				}
			}

			/**
			 * Gets the current state's name as a String
			 */
			@Override
			public String getStateName() {
				return CONFIRMED_NAME;
			}
		}

		/** This class is responsible for creating a VerifyingState object and correctly updating
		 * an issue in the VerifyingState
		 * @author Jake Donovan
		 *
		 */
		public class VerifyingState implements IssueState {
			/**
			 * Verifying State constructor
			 */
			private VerifyingState() {
				//
			}

			/**
			 * Updates an issue in the Verifying State with a passed Command value
			 * @param c the Command value
			 * @throws UnsupportedOperationException if a valid Command for Verifying state is not found
			 */
			@Override
			public void updateState(Command c) {
				if(c.getCommand() == CommandValue.VERIFY) {
					state = closedState;
					addNote(c.getNote());
				}

				else if(c.getCommand() == CommandValue.REOPEN) {
					resolution = null;
					state = workingState;
					addNote(c.getNote());
				}

				else {
					throw new UnsupportedOperationException("Invalid information.");
				}
			}

			/**
			 * Gets the current state's name as a String
			 */
			@Override
			public String getStateName() {
				return VERIFYING_NAME;
			}
		}

		/** This class is responsible for creating a ClosedState object and correctly updating
		 * an issue in the ClosedState
		 * @author Jake Donovan
		 *
		 */
		public class ClosedState implements IssueState {
			/**
			 * ClosedState constructor
			 */
			private ClosedState() {
				//
			}

			/**
			 * Updates an issue in the ClosedState with a passed Command value
			 * @param c the passed Command value
			 * @throws UnsupportedOperationException if a valid Command is not found for Closed state
			 */
			@Override
			public void updateState(Command c) {
				if(c.getCommand() == CommandValue.REOPEN) {
					resolution = null;
					
					if(issueType == IssueType.ENHANCEMENT && getOwner() != null && !"".equals(getOwner())) {
						state = workingState;
						addNote(c.getNote());
					}

					else if(issueType == IssueType.BUG && isConfirmed() && getOwner() != null && !"".equals(getOwner())) {
						state = workingState;
						addNote(c.getNote());
					}

					else if(issueType == IssueType.BUG && isConfirmed() && (getOwner() == null || "".equals(getOwner()))) {
						state = confirmedState;
						addNote(c.getNote());
					}

					else if(getOwner() == null || "".equals(getOwner()) || issueType == IssueType.BUG && !isConfirmed()) {
							state = newState;
							addNote(c.getNote());
					}
				}

				else {
					throw new UnsupportedOperationException("Invalid information.");
				}
			}

			/**
			 * Gets the current state's name as a String
			 */
			@Override
			public String getStateName() {
				return CLOSED_NAME;
			}
		}
	}
