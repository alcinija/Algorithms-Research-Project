/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: BurntPancakeSort.java
 * Description: This file provides an object that
 * when given an array of integers, performs a 
 * Burnt Pancake Sort.
 */
public class BurntPancakeSort {
	// Creates array field
	private int[] array;
	
	/**
	 * Creates an object to hold an array
	 * and performs a Burnt Pancake Sort
	 * @param array int[] the array
	 */
	public BurntPancakeSort(int[] array) {
		this.array = array;
	}
	
	/**
	 * Sorts the array using a burnt pancake sort
	 * @return the sorted array
	 */
	public int[] sort() {
		// Loops through each index of the array
		for (int i = this.array.length-1; i >= 0; i--) {
			// Finds the location of the largest absolute value
			int maxIndex = findMax(i);
			
			// Flips the stack so that value is on top
			flipStack(maxIndex, i);
			
			// Makes sure the entry is oriented correctly
			if (this.array[i] > 0) {
				this.array[i] = -1 * this.array[i];
			}
		}
		
		// Returns the sorted array
		return this.array;
	}
	
	/**
	 * Finds the location of the max value
	 * @param i int sets the starting index to being the search
	 * @return the index of the max value
	 */
	private int findMax(int i) {
		// The index to be returned
		int index = i;
		
		// Loops through every value
		for (int j = i; j >= 0; j--) {
			// Checks the value of each index and compares it to the max
			if (Math.abs(this.array[j]) <= Math.abs(this.array[index])) {
				index = j;
			}
		}
		
		// Returns the index of the max value
		return index;
	}
	
	/**
	 * Reverses the order of the values in the array range
	 * @param maxIndex int location of max value
	 * @param size the length of the array
	 */
	private void flipStack(int maxIndex, int size) {
		// Loops through the values until it reaches the midpoint
		for (int i = maxIndex; i <= (size - maxIndex) / 2 + maxIndex; i++) {
			// Holds the value at the first index
			int temp = this.array[i];
			
			// Flips the values
			this.array[i] = -1 * this.array[size];
			this.array[size] = -1 * temp;
			
			// Decrements the back side
			size--;
		}
	}
}
