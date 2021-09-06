/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: GnomeSort.java
 * Description: This file provides an object that
 * when given an array of integers, performs a 
 * Gnome Sort.
 */
public class GnomeSort {
	// Creates array field
	private int[] array;
	
	/**
	 * Creates an object to hold an array
	 * and performs a Gnome Sort
	 * @param array int[] the array that will be sorted
	 */
	public GnomeSort(int[] array) {
		this.array = array;
	}
	
	/**
	 * Performs a Gnome sort on the array
	 * @return the sorted array
	 */
	public int[] sort() {
		// Sets the starting index to 0
		int i = 0;
		
		// Loops until the end of the array is reached
		while (i < this.array.length) {
			// Checks the indices and iterates forward or backwards
			if (i == 0) {
				i++;
			} else if (this.array[i] >= this.array[i-1]) {
				i++;
			} else {
				swap(i-1,i);
				i--;
			}
		}
		
		// Returns the sorted array
		return this.array;
	}
	
	/**
	 * Swaps the values of two indices of the array
	 * @param j int the index of the element on the right
	 * @param i int the index of the element on the left
	 */
	private void swap(int j, int i) {
		// Creates a temporary value
		int temp = this.array[j];
		
		// Swaps the positions of the array
		this.array[j] = this.array[i];
		this.array[i] = temp;
	}
}
