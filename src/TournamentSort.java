import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: TournamentSort.java
 * Description: This file provides an object that
 * when given an array of integers, performs a 
 * Tournament Sort.
 */
public class TournamentSort {
	// Creates the fields
	private ArrayList<Integer> nums;
	private PriorityQueue<Node> tree;
	private ArrayList<Integer> sorted;
	
	/**
	 * Creates an Object to perform a
	 * Tournament sort on an array
	 * @param array int[] that array that will be sorted
	 */
	public TournamentSort(int[] array) {
		this.nums = new ArrayList<Integer>();
		this.tree = new PriorityQueue<Node>();
		this.sorted = new ArrayList<Integer>();
		
		// Adds the value to an arraylist
		for (int i = 0; i < array.length; i++) {
			nums.add(array[i]);
		}	
	}
	
	/**
	 * Performs the Tournament Sort on the array
	 * @return the list of sorted values
	 */
	public ArrayList<Integer> sort() {
		// Loops while unsorted values exist
		while (!this.nums.isEmpty()) {
			// Fills up our tree to its limit
			fillTree();
			
			// Sorts values until all values are invalid
			matchups();
			
			// Combines the sorted lists an invailid values
			this.sorted = combine();
		}
		
		// Returns the sorted list
		return this.sorted;
	}
	
	/**
	 * Combines the sorted list with the
	 * invalid values in the tree
	 * @return the new sorted list
	 */
	private ArrayList<Integer> combine() {
		// Creates a list to hold the new combined list
		ArrayList<Integer> newSorted = new ArrayList<Integer>();
		
		// Loops while valid comparisons can be made
		while (!this.nums.isEmpty() && !this.sorted.isEmpty()) {
			if (this.nums.get(0) <= this.sorted.get(0)) {
				newSorted.add(this.nums.get(0));
				this.nums.remove(0);
			} else {
				newSorted.add(this.sorted.get(0));
				this.sorted.remove(0);
			}
		}
		
		// Adds remaining sorted values if the unsorted list runs out
		if (this.nums.isEmpty()) {
			// Loops while values exist
			while (!this.sorted.isEmpty()) {
				newSorted.add(this.sorted.get(0));
				this.sorted.remove(0);
			}
		}
		
		// Returns the new sorted list
		return newSorted;
	}
	
	/**
	 * Fills the tree with initial values
	 */
	private void fillTree() {
		// Fills will values exist or capacity is not reached
		while (!this.nums.isEmpty() && this.tree.size() < 7) {
			this.tree.add(new Node(this.nums.get(0), false));
			this.nums.remove(0);
		}
	}
	
	/**
	 * Performs the comparisons and 
	 * adds values to the sorted list
	 */
	private void matchups() {
		// Loops while valid entries exist or the capacity is not reached
		while (!treeLocked() && !this.nums.isEmpty()) {
			// Adds a new value to the list
			this.sorted.add(this.tree.poll().getValue());
			
			// Adds a new value and determines if it is valid
			if (this.nums.get(0) <= this.sorted.get(this.sorted.size()-1)) {
				this.tree.add(new Node(this.nums.get(0), false));
				this.nums.remove(0);
			} else {
				this.tree.add(new Node(this.nums.get(0), true));
				this.nums.remove(0);
			}
		}
		
		// Adds remaining vlues to the tree if no new values exist
		while (!this.tree.isEmpty() && !treeLocked()) {
			this.sorted.add(this.tree.poll().getValue());
		}
		
		// Creates a list for invalid values
		ArrayList<Integer> lockedVals = new ArrayList<Integer>();
		
		// Adds the invalid values to the list
		while (!this.tree.isEmpty()) {
			lockedVals.add(this.tree.poll().getValue());
		}
		
		// Adds the invalid values in sorted order to the entry list
		while (!lockedVals.isEmpty()) {
			this.nums.add(0, lockedVals.get(lockedVals.size()-1));
			lockedVals.remove(lockedVals.size()-1);
		}
	}
	
	/**
	 * Checks if all spots on the tree are occupied by 
	 * values lower than the last index in the sorted list
	 * @return true if all values are locked, false otherwise
	 */
	private boolean treeLocked() {
		// Checks if values exist
		if (this.tree.size() > 0) {
			// Checks if the first index is locked
			if (this.tree.peek().getLocked()) {
				return true;
			}
		}
		
		// Return false if not locked
		return false;
	}
	
	/**
	 * Creates a node object to compare the 
	 * values in the tree and check their validity
	 * to be added to the sorted list
	 */
	private class Node implements Comparable<Node>{
		// Creates the fields
		private int value;
		private boolean locked;
		
		/**
		 * Creates a node object to store the value
		 * and locked status of the node
		 * @param value int the value from the array
		 * @param locked boolean determines if the value 
		 * is smaller than largest sorted value
		 */
		public Node(int value, boolean locked) {
			this.value = value;
			this.locked = locked;
		}
		
		/**
		 * Returns the numerical value of the node
		 * @return the numerical value
		 */
		public int getValue() {
			return this.value;
		}
		
		/**
		 * Returns whether or not the value is locked
		 * @return true if locked false otherwise
		 */
		public boolean getLocked() {
			return this.locked;
		}

		/**
		 * Compares two nodes based on if they are locked or not
		 * and which value is smaller
		 * @param otherNode Node the node that is being compared to
		 */
		@Override
		public int compareTo(Node otherNode) {
			// Checks which values are locked
			if (this.locked && otherNode.getLocked()) {
				// Checks which value is larger
				if (this.getValue() > otherNode.getValue()) {
					return 1;
				} else {
					return -1;
				}
			} else if (this.getLocked()) {
				return 1;
			} else if (otherNode.getLocked()) {
				return -1;
			} else {
				// Checks which value is larger
				if (this.getValue() > otherNode.getValue()) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		
	}
}
