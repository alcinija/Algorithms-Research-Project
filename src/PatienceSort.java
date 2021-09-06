import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: Patience.java
 * Description: This file provides an object that
 * when given an array of integers, performs a 
 * Patience Sort.
 */
public class PatienceSort {
	// Creates fields to store the values
	private int[] array;
	private ArrayList<Pile> stacks;
	
	/**
	 * Creates an object to perform a Patience Sort
	 * @param array int[] the array that will be sorted
	 */
	public PatienceSort(int[] array) {
		this.array = array;
		this.stacks = new ArrayList<Pile>();
	}
	
	/**
	 * Sorts the array using a patience sort
	 * @return
	 */
	public ArrayList<Integer> sort(){
		// Creates an output list of sorted values
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		
		// Loops through each entry of the original array
		for (int i = 0; i < this.array.length; i++) {
			// Finds a stack to place the value in
			if (this.stacks.isEmpty() || highMin(this.array[i]) == -1) {
				// Creates a new pile value is too large
				Pile newPile = new Pile();
				newPile.addValue(this.array[i]);
				
				this.stacks.add(newPile);
			} else {
				// Finds an existing stack to add the value to
				int index = highMin(this.array[i]);
				
				// Adds value to that pile
				Pile newPile = this.stacks.get(index);
				newPile.addValue(this.array[i]);
				
				// Update the list of stacks
				this.stacks.set(index, newPile);
			}
		}
		
		// order stacks by their min values
		PriorityQueue<Pile> priority = new PriorityQueue<Pile>();
		
		// Adds the piles to the priority queue
		for (Pile p1 : this.stacks) {
			priority.add(p1);
		}
		
		// Loop while values exist in stacks
		while (!priority.isEmpty()) {
			// Remove the min pile
			Pile minPile = priority.poll();
			
			// Take the min value from the pile
			int min = minPile.getValues().get(minPile.getValues().size()-1);
			minPile.getValues().remove(minPile.getValues().size()-1);
			
			// Add min value to sorted
			sorted.add(min);
			
			// Re-add the pile to the priority queue
			if (minPile.getValues().size() > 0) {
				priority.add(minPile);
			}
		}
		
		// Return the sorted list
		return sorted;
	}
	
	/**
	 * Tries to find the lowest minimum stack
	 * that the value could be added to
	 * @param num int the value that is being checked
	 * @return the index of the stack to add the value, -1 if not found
	 */
	private int highMin(int num) {
		// reates the returned index
		int index = -1;
		
		// Loops throught the stacks
		for (int i = this.stacks.size()-1; i >= 0; i--) {
			// Checks if the stack is valid
			if (this.stacks.get(i).getValues().get(this.stacks.get(i).getValues().size()-1) > num) {
				index = i;
			}
		}
		
		// Returns the index of the min value
		return index;
	}
	
	/**
	 * Creates an Object to store
	 * the lists of values and allows them
	 * to be compared
	 */
	private class Pile implements Comparable<Pile> {
		// Creates a list of values
		private ArrayList<Integer> values;
		
		/**
		 * Creates a Pile object to hold values
		 */
		public Pile() {
			this.values = new ArrayList<Integer>();
		}
		
		/**
		 * Returns the list of values in the pile
		 * @return the list of values
		 */
		public ArrayList<Integer> getValues() {
			return this.values;
		}
		
		/**
		 * Adds a new minimum value to the pile
		 * @param value
		 */
		public void addValue(int value) {
			this.values.add(value);
		}
		
		/**
		 * Allows to piles to be compared to 
		 * place the value in the correct pile
		 * @param otherPile Pile the other pile being compared
		 */
		public int compareTo(Pile otherPile) {
			// Checks the minimum value of each pile
			if (this.values.get(this.values.size()-1) > otherPile.values.get(otherPile.values.size()-1)) {
				return 1;
			} else {
				return -1;
			}
			
		}
	}
}
