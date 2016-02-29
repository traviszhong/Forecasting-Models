package com.strongit.MovingAverage;

import com.strongit.DataCleaner.Cleanser;

/**
 * 
 * @author Zhong Ziyue
 * 
 * @Email zhongzy@strongit.com.cn
 * 
 * @Date 2014-1-15
 * 
 */

public class MovingAverage {

	private int interval;

	public MovingAverage() {

		this.setInterval(5);
	}

	public MovingAverage(int interv) {

		this.setInterval(interv);
	}

	protected double averaging(double[] data_set) {

		double result = 0;
		int scale = data_set.length;
		int interv = (interval <= scale) ? interval : scale;
		double sum = 0;

		for (int i = scale - interv; i < scale; i++) {

			sum += data_set[i];
		}

		result = sum / interv;

		return result;
	}

	protected double[] toRatio(double[] data_set) {

		int scale = data_set.length;
		
		if (scale < 2) {
			
			double ratio[] = new double[1];
			ratio[0] = 0;
			return ratio;
		}
		
		double ratio[] = new double[scale - 1];

		for (int i = 0; i < scale - 1; i++) {

			ratio[i] = (data_set[i + 1] - data_set[i]) / data_set[i];
		}

		return ratio;
	}

	public double forecast(double[] data_set) {
		
	//****************************Use the Cleanser********************
		Cleanser cs = new Cleanser();
		double[] dataset;

		dataset = cs.cleaning(data_set);

		if (dataset[0] == Double.MIN_VALUE) {

			return Double.MAX_VALUE - 100; // Exception: mean_value = 0
		}
	//**************************************************
				
				
		int scale = dataset.length;

		if (scale < 3) {
					
			return Double.MIN_VALUE;  // Exception: Data available in the dataset is not enough
		}

		return averaging(dataset);
	}

	public double forecast(double[] data_set, int ratioFlag) {
		
		//****************************Use the Cleanser********************
		Cleanser cs = new Cleanser();
		double[] dataset;

		dataset = cs.cleaning(data_set);

		if (dataset[0] == Double.MIN_VALUE) {

			return Double.MAX_VALUE - 100; // Exception: mean_value = 0
		}
		//**************************************************
						
		int scale = dataset.length;

		if (scale < 3) {
							
			return Double.MIN_VALUE;  // Exception: Data available in the dataset is not enough
		}

		if (ratioFlag == 0) {
			
			return averaging(dataset);
			
		} else {
			
			double rt_avg;
			rt_avg = averaging(toRatio(dataset));
			return dataset[(dataset.length - 1)] + rt_avg * dataset[(dataset.length - 1)];
		}
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

}
