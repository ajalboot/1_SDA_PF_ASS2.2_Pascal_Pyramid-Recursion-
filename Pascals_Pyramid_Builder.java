
/******************************************************************************
 * 
 * Solution for bonus_assignment2.5 : Pascal Pyramid (Recursion)
 * The below program code generates 20 level of Pascal Pyramid diagram without
 * using loop statements like ​for, while​ etc.
 * 
 ******************************************************************************/

import java.util.ArrayList;

public class Pascals_Pyramid_Builder {

	private static int maxRows = 20;

	private static int initVal = 0;

	private static String spcData; // Stores space for each row in the pyramid

	private static String rowData; // Stores data for each row in the pyramid

	private static int calcVal; // Stores calculated value for each tag in the pyramid's row

	private static ArrayList<Integer> currList = 
			new ArrayList<Integer>(); // current list contains updated values

	private static ArrayList<Integer> prevList = 
			new ArrayList<Integer>(); // previous list contains clone of current
									  // list values that uses during calculating
									  // the values on current list

	public static void main(String[] args) {
		if (initVal < maxRows) {
			freeInitVars();
			valCalculater(0, initVal);
			System.out.println("Row#" + (initVal + 1) + ":" + "\t" + spaceBuilder((maxRows - initVal))
					+ dataCollecter(0, currList.size()) + "\n");
			initVal++;
			main(args);
		}
	}

	/**
	 * 
	 * @param X: refers to loop frequency, starts with 0 and increments by 1 for
	 *           each loop
	 * @param Y: refers to max loop frequence for each level of Pyramid starts with
	 *           1 and ends when y = maxRows variable
	 * @return the value of Pyramid tags , every number equals the sum of two
	 *         numbers in the previous row.
	 */
	private static int valCalculater(int X, int Y) {
		if (X < Y) {
			// assign default value (1) for the first and last tags in pyramid row
			if (X > 0) {
				calcVal = Math.addExact((int) prevList.get(X - 1), (int) prevList.get(X));
			} else {
				calcVal = 1;
			}
			currList.add(X, calcVal);
			X++;
			valCalculater(X, Y);
		}
		return calcVal;
	}

	/**
	 * 
	 * @param X: refers to loop frequency, starts with 0 and increments by 1 for
	 *           each loop
	 * @param Y: refers to max loop frequence for each level of Pyramid starts with
	 *           1 and ends when y = maxRows variable
	 * @return Pyramid row data by aggregating its values and those values delimited
	 *         by double Tabs to formalize the Pyramid view
	 */
	private static String dataCollecter(int X, int Y) {
		if (X < Y) {
			rowData += (currList.get(X) + "\t" + "\t");
			X++;
			dataCollecter(X, Y);
		}
		return rowData;
	}

	/**
	 * 
	 * @param X = maxRows - 1 for each loop
	 * @returns required space for each level of Pyramid depends on row level. it's
	 *          decremental by one, The first level of Pyramid has space equals to
	 *          maxRows and vice versa the last level of Pyramid will have 0 space.
	 */
	private static String spaceBuilder(int X) {
		if (X != 1) {
			spcData += "\t";
			X--;
			spaceBuilder(X);
		}
		return spcData;
	}

	/**
	 * Empty initial variables for each row in the Pascal Pyramid
	 */
	private static void freeInitVars() {
		// clone the current list into prevList varialbe to keep it as reference
		prevList = (ArrayList<Integer>) currList.clone();
		currList.clear();
		currList.add(0, 1);
		spcData = "";
		rowData = "";
		calcVal = initVal;
	}

}
