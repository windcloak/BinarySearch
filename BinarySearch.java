package binarysearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Allows user to input an array and look for a specific number in the array
 * Finds location of number in array using Binary Search
 * Finds number of occurrences of number in array
 * @author May Guo
 * 2019-9-4
 *
 */

public class BinarySearch {
	
	/** BinaryPosition
	 * Returns location of number if found or -1 if not found
	 * Recursive, binary search method
	 * @param A array
	 * @param x number being searched for
	 * @param min smallest element in array being searched
	 * @param max biggest element in array being searched
	 * @return position of number
	 */
	public static int binaryPosition(int [] A, int x, int min, int max) {
		
		while (min<=max) {
			int mid = (min + max)/2;
			if (x > A[mid]){
				min = mid + 1;
				return binaryPosition(A, x, min, max);
				}
			else if (x < A[mid]){
				max = mid - 1;
				return binaryPosition(A, x, min, max);
				}
			else { // x = A[mid]
				return mid;			
			}
		}
		return -1; //not found
	}


	/** findFirstLast
	 * Find first or last occurrence of a given number
	 * @param A array
	 * @param x number being searched for
	 * @param findFirst true if looking for first occurrence
	 * false if looking for last occurrence
	 * @return location of first or last occurrence of number
	 * or -1 if not found
	 */
	
	public static int findFirstLast(int[] A, int x, boolean findFirst) {
		int location = -1;	//returns -1 if not found
		int min = 0;
		int max = A.length - 1;
		
		while (min<=max) {
			int mid = (min + max)/2;
			
			//update location
			if (x == A[mid]) {
				location = mid;
				
				//if looking for first occurrence
				if (findFirst) {
					max = mid - 1;
				}
				//if looking for last occurrence
				else {
					min = mid + 1;
				}
			}
			else if (x > A[mid]){
				min = mid + 1;	//search right side
				}
			else {	// x < A[mid] //search left side
				max = mid - 1;
				}
		}
		return location;
	}
	
	/**
	 * binaryCount
	 * @param A array
	 * @param x number being searched for
	 * @return how many times a number occurs in an array
	 */
	
	public static int binaryCount(int[] A, int x) {
		int count = 0;
		int first = findFirstLast(A, x, true); //first occurrence
		int last = findFirstLast(A, x, false); //last occurrence
		if (first > -1 || last > -1) { //if it is found
			count = last-first+1;
		}
		return count;
	}
	
	/**
	 * Asks user to input array
	 * @return arr
	 */
	
	public static int[] getArray() {
		System.out.println("How many elements are in the array? (Enter integer)");
		Scanner in =  new Scanner(System.in);
		int size = in.nextInt();
		int arr[] = new int[size];
		System.out.println("Enter the elements:");
		for (int i = 0; i < size; i++) {
			arr[i] = in.nextInt();
		}
		Arrays.sort(arr);	//sort array
		return arr;
	}
	
	public static void main(String[] args) {
		int A[] = getArray(); //Array being searched
		int min = 0;
		int max = A.length - 1;
		
        System.out.println("What number are you looking for? (Enter integer)");
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		
		//Location of number in array
		int location = binaryPosition(A, x, min, max);
		if (location >= 0) {
			System.out.printf("%d is at position %d\n", x, location);
		}
		else {
			System.out.printf("%d is not in the array\n", x);
		}
		
		//Number of occurrences of number in array
		int count = binaryCount(A, x);
		System.out.println(x + " occurs " + count + " time(s).");
		in.close();
	}

}
