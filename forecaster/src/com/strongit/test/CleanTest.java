package com.strongit.test;

import com.strongit.DataCleaner.Cleanser;

public class CleanTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cleanser cs = new Cleanser();

		double dataset[] = { 238.98, 309.77, 290.76, 292.87, 352.91, 338.96,
				340.95, 99.84, 370.23, 375.5, 376.62 };
		double[] data_set;

		data_set = cs.cleaning(dataset);

		int scale = data_set.length;

		for (int i = 0; i < scale; i++) {
			
			System.out.println(data_set[i]);
		}

	}

}
