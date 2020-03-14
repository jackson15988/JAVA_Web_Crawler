package com.fubon.esb.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class KillNumber {

	public static void main(String args[]) {

		// one missing number
		ArrayList<Integer> ex = escapeNumber(7);
		System.out.println("取回的下單號碼" + ex);
		ex = fetchTripsCar(ex);
		System.out.println("進行擷取優化之後");
		System.out.println(ex);
//		// two missing number
//		printMissingNumber(new int[] { 1, 2, 3, 4, 6, 7, 9, 8, 10 }, 10);
//
//		// three missing number
//		printMissingNumber(new int[] { 1, 2, 3, 4, 6, 9, 8 }, 10);
//
//		// four missing number
//		printMissingNumber(new int[] { 1, 2, 3, 4, 9, 8 }, 10);

		// Only one missing number in array
//		int[] iArray = new int[] { 1, 2, 3, 5 };
//		int missing = getMissingNumber(iArray, 5);
//		System.out.printf("Missing number in array %s is %d %n", Arrays.toString(iArray), missing);
	}

	public static ArrayList<Integer> getNextBetNumber(Integer keyNumber) {
		ArrayList<Integer> resultNo = escapeNumber(keyNumber);
		resultNo = fetchTripsCar(resultNo);
		return resultNo;

	}

	public static ArrayList<Integer> fetchTripsCar(ArrayList<Integer> escapeNumber) {
		ArrayList<Integer> betNumber = new ArrayList<>();
		betNumber.add(escapeNumber.get(0));
		betNumber.add(escapeNumber.get(1));
		betNumber.add(escapeNumber.get(4));
		betNumber.add(escapeNumber.get(5));
		betNumber.add(escapeNumber.get(6));
		return betNumber;

	}

	public static ArrayList<Integer> escapeNumber(int keyType) {
		ArrayList<Integer> nextNumber = new ArrayList<>();

		switch (keyType) {
		case 1:
			for (int i = 1; i <= 7; i++) {
				nextNumber.add(i);
			}
			break;
		case 2:
			for (int i = 2; i <= 8; i++) {
				nextNumber.add(i);
			}
			break;

		case 3:
			for (int i = 3; i <= 9; i++) {
				nextNumber.add(i);
			}
			break;
		case 4:
			for (int i = 4; i <= 10; i++) {
				nextNumber.add(i);
			}
			break;

		case 5:
			for (int i = 5; i <= 10; i++) {
				nextNumber.add(i);
			}
			nextNumber.add(1);

			break;

		case 6:
			for (int i = 6; i <= 10; i++) {
				nextNumber.add(i);
			}
			nextNumber.add(1);
			nextNumber.add(2);
			break;

		case 7:
			for (int i = 7; i <= 10; i++) {
				nextNumber.add(i);
			}
			nextNumber.add(1);
			nextNumber.add(2);
			nextNumber.add(3);
			break;

		case 8:
			nextNumber.add(8);
			nextNumber.add(9);
			nextNumber.add(10);
			for (int i = 1; i <= 4; i++) {
				nextNumber.add(i);
			}
			break;

		case 9:
			nextNumber.add(9);
			nextNumber.add(10);
			for (int i = 1; i <= 5; i++) {
				nextNumber.add(i);
			}
			break;

		case 10:
			nextNumber.add(10);
			for (int i = 1; i <= 6; i++) {
				nextNumber.add(i);
			}
			break;

		}
		return nextNumber;

	}

	/**
	 * A general method to find missing values from an integer array in Java. This
	 * method will work even if array has more than one missing element.
	 */
	public static void printMissingNumber(int[] numbers, int count) {
		int missingCount = count - numbers.length;
		BitSet bitSet = new BitSet(count);

		for (int number : numbers) {
			bitSet.set(number - 1);
		}

//		System.out.printf("Missing numbers in integer array %s, with total number %d is %n", Arrays.toString(numbers),
//				count);
		int lastMissingIndex = 0;

		for (int i = 0; i < missingCount; i++) {
			lastMissingIndex = bitSet.nextClearBit(lastMissingIndex);
			System.out.print(" " + ++lastMissingIndex);
		}

	}

	/**
	 * Java method to find missing number in array of size n containing numbers from
	 * 1 to n only. can be used to find missing elements on integer array of numbers
	 * from 1 to 100 or 1 - 1000
	 */
	private static int getMissingNumber(int[] numbers, int totalCount) {
		int expectedSum = totalCount * ((totalCount + 1) / 2);
		int actualSum = 0;
		for (int i : numbers) {
			actualSum += i;
		}

		return expectedSum - actualSum;
	}

}