/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: RadixSort.java
 * Description: This file provides an object that
 * when given an array of integers, performs a 
 * Radix Sort.
 */
public class RadixSort {
	// Creates the fields
	private int[] array;
	private int max;
	
	/**
	 * Creates an object to perform a Radix Sort
	 * @param array int[] the array that needs to be sorted
	 * @param max the max value possible for the array
	 */
	public RadixSort(int[] array, int max) {
		this.array = array;
		this.max = max;
	}
	
	/**
	 * Sorts the array using a radix sort
	 * @return the sorted array
	 */
	public int[] sort() {
		// Loops as many times for didgits in the max value
		for (int i = 1; this.max / i > 0; i = 10 * i) {
			// Sorts each digit
			CountingSort cs1 = new CountingSort(this.array, i);
			this.array = cs1.sort();
		}
		
		// Returns the sorted array
		return this.array;
	}
	
	/**
	 * Sorts the values using a Counting sort
	 */
	private class CountingSort {
		// Creates the fields
		private int[] array;
		private int divisor;
		
		/**
		 * Creates an object to perform a counting sort
		 * @param array int[] the array of integers
		 * @param divisor the value each number will be divided by
		 */
		public CountingSort(int[] array, int divisor) {
			this.array = array;
			this.divisor = divisor;
		}
		
		/**
		 * Sorts the array using a counting sort
		 * @return the sorted array
		 */
		public int[] sort() {
			// Creates the arrays
			int[] result = new int[this.array.length];
			int[] vals = new int[10];
			int total = 0;
			
			// Fills the vals array with 0 in all indices
			for (int i = 0; i < vals.length; i++) {
				vals[i] = 0;
			}
			
			// Fills the vals array with the count of occurrences of each value
			for (int i = 0; i < this.array.length; i++) {
				int val = (this.array[i] / divisor) % 10;
				vals[val] = vals[val] + 1;
			}
			
			// Sums the aggregate total for the vals array
			for (int i = 1; i < vals.length; i++) {
				total = vals[i-1];
				vals[i] = vals[i] + total;
			}
			
			// Sortes the array based on the values in vals
			for (int i = this.array.length-1; i >= 0; i--) {
				// Finds the coresponding vals count
				int val = ((this.array[i] / divisor) % 10);
				int index = vals[val] - 1;
				
				// Decrements that value
				vals[val]--;
				
				// Assigns that array value to the new array
				result[index] = this.array[i];
			}
			
			// Sets the array to the sorted array
			this.array = result;
			
			// Returns the sorted array
			return this.array;
		}
	}
}
