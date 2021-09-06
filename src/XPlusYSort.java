import java.util.Arrays;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: XPlusYSort.java
 * Description: This file provides an object that
 * when given an array of integers, performs a 
 * X + Y Sort.
 */
public class XPlusYSort {
	// Creates the X and Y array fields
	private int[] x;
	private int[] y;
	
	/**
	 * Creates an object to preform X + Y sorts
	 * @param x int[] the X array
	 * @param y int[] the Y array
	 */
	public XPlusYSort(int[] x, int[] y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sorts the list of sums using X + Y sort
	 * @return the sorted list of sums
	 */
	public int[] sort() {
		// Sorts the X array
		MergeSort mergeX = new MergeSort(this.x);
		this.x = mergeX.mergeSort();
		
		// Sorts the Y array
		MergeSort mergeY = new MergeSort(this.y); 
		this.y = mergeY.mergeSort();
		
		// Creates a new array to hold the sums
		int[] sums = new int[this.x.length * this.y.length];
		
		// Increments the index
		int sumsPointer = 0;
		
		// Loops through all of the X elements
		for (int i = 0; i < this.x.length; i++) {
			// Loops through all of the Y elements
			for (int j = 0; j < this.y.length; j++) {
				// Sets the value at the index to the sum of Xval and Yval
				sums[sumsPointer] = this.x[i] + this.y[j];
				
				// Increments the index
				sumsPointer++;
			}
		}
		
		// Sorts the sums using merge sort
		MergeSort mergeSums = new MergeSort(sums);
		sums = mergeSums.mergeSort();
		
		// Returns the sorted list of sums
		return sums;
	}
	
	/**
	 * Creates a merge sort object to perform merge sorts 
	 * on unsorted lists
	 */
	private class MergeSort {
		// Creates an array field
		private int[] array;
		
		/**
		 * Creates a merge sort object to sort an array
		 * @param array int[] the array to be sorted
		 */
		public MergeSort(int[] array) {
			this.array = array;
		}
		
		/**
		 * This function performs a merge sort on an array of
		 * pre-specified integers and displays the result in 
		 * ascending and descending order
		 * @param list the array of int values the user gives
		 * @return the sorted array sorted ascending and descending
		 */
		public int[] mergeSort() { 
			// Returns the result of 2 single element arrays if only one element is provided
			if (this.array.length == 1) {
				int result[] = this.array;
				return result;
			}
			
			// Creates the array with the original lists
			int result[] = this.array;
			
			// Sorts the arrays
			result = mergeSort(result);
			
			// Returns the 2-D arrays
			return result;
		}
		
		/**
		 * This function recursively sorts the arrays
		 * @param list the array of the passed in elements
		 * @return the sorted array of elements
		 */
		private int[] mergeSort(int[] list) {
			// Terminates  if either sort is equal to 1
			if (list.length == 1) {
				return list;
			}
			
			// Takes the left half of elements in the lists
			int[] left = Arrays.copyOfRange(list, 0, list.length / 2);
			
			// Takes the right half of elements in the list
			int[] right = Arrays.copyOfRange(list,  list.length / 2, list.length);
			
			// Result of sorting beach sides
			left = mergeSort(left);
			right = mergeSort(right);
			
			// the result of combining both sides together
			int result[] = merge(left, right);
			
			// Returns the sorted  array of elements
			return result;
		}
		
		/**
		 * This function merges the left and right sorted arrays
		 * into two combined arrays, one ascending and one descending
		 * @param left the array with the elements from the left side
		 * @param right the array with the elements from the right side
		 * @return The combination of the sides sorted in an array
		 */
		private int[] merge(int[] left, int[] right) {
			// Creates a new array with the combined length of both sides
			int[] result = new int[left.length + right.length];
			
			// Creates pointers one for each side
			int lIndex = 0;
			int rIndex = 0;
			
			// Loops through the Array i is each index in a row
			for (int i = 0; i< result.length; i++) {
				// Checks i index is inbounds and which value is larger then adds to the result
				if (lIndex >= left.length || (rIndex < right.length && right[rIndex] <= left[lIndex])) {
					result[i] = right[rIndex];
					rIndex++;
				} else {
					result[i] = left[lIndex];
					lIndex++;
				}
			}
			
			// Returns the combination of the 2 sides
			return result;
		}
	}
}
