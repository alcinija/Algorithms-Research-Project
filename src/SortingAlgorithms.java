import java.util.Arrays;

/**
 * Name: Joe Alcini (alcinija)
 * Instructor: Dr Raychoudhury
 * Course: CSE 374, Section D
 * Date: 5/5/2021
 * Assignment: Final Project (Sorting)
 * File: SortingAlgorithms.java
 * Description: This file performs the test
 * of how long each algorithm takes to sort the 
 * same random data and shows 
 * the user the results in console.
 */
public class SortingAlgorithms {

	public static void main(String[] args) {
		// Creates the sizes of the input arrays
		int[] sizes = {10, 50, 100, 500, 1000, 5000, 10000};
		
		// Creates the amount of trials for each size
		int trials = 5;
		
		// Loops through each input size
		for (int i = 0; i < sizes.length; i++){
			// Loops once for each trial
			for (int j = 0; j < trials; j++) {
				// Creates an array of random values
				int[] vals = createArray(sizes[i]);
				
				// Creates copies of the random array for each sort
				int[] bpsVals = Arrays.copyOf(vals, vals.length);
				int[] gsVals = Arrays.copyOf(vals, vals.length);
				int[] tsVals = Arrays.copyOf(vals, vals.length);
				int[] rsVals = Arrays.copyOf(vals, vals.length);
				int[] psVals = Arrays.copyOf(vals, vals.length);
				int[] xVals = Arrays.copyOfRange(vals, 0, vals.length / 2);
				int[] yVals = Arrays.copyOfRange(vals, vals.length / 2, vals.length);
				
				// Creates the sort objects
				BurntPancakeSort bps1 = new BurntPancakeSort(bpsVals);
				GnomeSort gs1 = new GnomeSort(gsVals);
				TournamentSort ts1 = new TournamentSort(tsVals);
				RadixSort rs1 = new RadixSort(rsVals, sizes[i]);
				PatienceSort ps1 = new PatienceSort(psVals);
				XPlusYSort xys1 = new XPlusYSort(xVals, yVals);
				
				// Notifies the user what trial is being completed
				System.out.println("Trial " + (j + 1) + ", Size: " + sizes[i]);
				
				// Times the Burnt Pancake Sort
				long startTime = System.currentTimeMillis();
				bps1.sort();
				System.out.println("Burnt Pancake Sort Time: " + (((double)System.currentTimeMillis() - startTime) / 1000.0));
				
				// Times the Burnt Gnome Sort
				startTime = System.currentTimeMillis();
				gs1.sort();
				System.out.println("Gnome Sort Time: " + (((double)System.currentTimeMillis() - startTime) / 1000.0));
				
				// Times the Tournament Sort
				startTime = System.currentTimeMillis();
				ts1.sort();
				System.out.println("Tournament Sort Time: " + (((double)System.currentTimeMillis() - startTime) / 1000.0));
				
				// Times the Radix Sort
				startTime = System.currentTimeMillis();
				rs1.sort();
				System.out.println("Radix Sort Time: " + (((double)System.currentTimeMillis() - startTime) / 1000.0));
				
				// Times the Patience Sort
				startTime = System.currentTimeMillis();
				ps1.sort();
				System.out.println("Patience Sort Time: " + (((double)System.currentTimeMillis() - startTime) / 1000.0));
				
				// Times the X + Y Sort
				startTime = System.currentTimeMillis();
				xys1.sort();
				System.out.println("X + Y Sort Time: " + (((double)System.currentTimeMillis() - startTime) / 1000.0));
				
				System.out.println("\n");
			}
			
			System.out.println("\n");
		}
		
		System.out.println("Tests Complete");
	}
	
	/**
	 * Create an array with random values in it
	 * @param size int the size of the array
	 * @return the array of random values
	 */
	private static int[] createArray(int size) {
		// Create new array
		int[] result = new int[size];
		
		// Loop through array until it is filled with random values
		for (int i = 0; i < result.length; i++) {
			result[i] = (int)Math.floor(Math.random()*(size-1+1)+1);
		}
		
		// Return the new array
		return result;
	}

}
