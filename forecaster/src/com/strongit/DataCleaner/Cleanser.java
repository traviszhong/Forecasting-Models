package com.strongit.DataCleaner;

/**
 * 
 * @author Zhong Ziyue
 * 
 * @Email zhongzy@strongit.com.cn
 * 
 * @Date 2014-1-20
 * 
 */

public class Cleanser {

	// based on k-means

	private double threshold;

	public Cleanser() {

		this.setThreshold(0.4);
	}

	public Cleanser(double th) {

		this.setThreshold(th);
	}

	public double[] cleaning(double[] dataset) {

		int scale = dataset.length;
		int scalecount = scale;
		double mean_value = 0;
		double deviation;
		double[] data_set;
		int index = 0;

		for (int i = 0; i < scale; i++) {

			mean_value += dataset[i];
		}

		mean_value = mean_value / scale; // the mean_value can never be zero
											// since the growth cannot be
											// negative, all of them are
											// positive figures.

		if (mean_value == 0) {

			dataset[0] = Double.MIN_VALUE;
			return dataset;
		}

		for (int i = 0; i < scale; i++) {

			deviation = Math.abs(dataset[i] - mean_value) / mean_value;
			if (deviation > threshold) {

				dataset[i] = Double.MIN_VALUE;
				scalecount--;
			}
		}

		data_set = new double[scalecount];

		for (int i = 0; i < scale; i++) {

			if (dataset[i] != Double.MIN_VALUE) {
				
				data_set[index] = dataset[i];
				index++;
			}
		}
		
		return data_set;

	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

}
