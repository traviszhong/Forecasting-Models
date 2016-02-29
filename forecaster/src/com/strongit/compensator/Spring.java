package com.strongit.compensator;

import com.strongit.DataCleaner.Cleanser;

/**
 * 
 * @author Zhong Ziyue
 * 
 * @Email zhongzy@strongit.com.cn
 * 
 * @Date 2014-1-9
 * 
 * @version 1.0
 * 
 *          Without spring lock
 * 
 */

public class Spring {

	private double neg_weight; // the default value of neg_weight is 1
	private int reactance; // the default value of reactance is 2
	private int weight_valid;
	private int sliding_flag;

	public Spring() {

		this.setNeg_weight(1);
		this.setReactance(2);
		this.setWeight_valid(5);
		this.setSliding_flag(1); // Sliding Spring in default
	}

	public Spring(int slFlag) {

		this.setNeg_weight(1);
		this.setReactance(2);
		this.setWeight_valid(5);
		this.setSliding_flag(slFlag); // Fixed: flag = 0
	}

	public double resilienceParser(double[] data_set) {

		double resilience = 0;
		int scale = data_set.length; // The best length of this dataset must be
										// greater than reactance.

		if (scale < reactance) {
			if (scale != 0) {

				resilience = data_set[scale - 1];
			}

			return resilience;
		}

		if (sliding_flag == 0) {
			
			weightUpdate2(data_set);
		} else {
			
			weightUpdate(data_set);
		}

		for (int i = scale - reactance; i < scale; i++) {

			if (data_set[i] >= 0) {

				resilience += data_set[i];

			} else {

				resilience += data_set[i] * neg_weight;
			}
		}

		resilience = resilience * (-1); // Force Reversing
		
		if (resilience < 0) {

			resilience = resilience / neg_weight;
		}

		return resilience;
	}

	protected void weightUpdate(double[] data_set) {

		// neg_weight = �� positive values / (�� negative values * (-1))

		double pos_sum = 0;
		double neg_sum = 0;
		int scale = data_set.length;
		int interv = (weight_valid <= scale) ? weight_valid : scale;

		for (int i = scale - interv; i < scale; i++) {

			if (data_set[i] >= 0) {
				pos_sum += data_set[i];
			} else {
				neg_sum += data_set[i];
			}
		}

		if (neg_sum != 0) {

			this.setNeg_weight(pos_sum / (neg_sum * (-1)));
		} else {

			return;
		}
	}

	protected void weightUpdate2(double[] data_set) {

		// neg_weight = �� positive values / (�� negative values * (-1))

		double pos_sum = 0;
		double neg_sum = 0;
		int scale = data_set.length;

		for (int i = 0; i < scale; i++) {

			if (data_set[i] >= 0) {
				pos_sum += data_set[i];
			} else {
				neg_sum += data_set[i];
			}
		}

		if (neg_sum != 0) {

			this.setNeg_weight(pos_sum / (neg_sum * (-1)));
		} else {

			return;
		}

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

		return resilienceParser(dataset);
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

			return resilienceParser(dataset);
		} else {
			
			double rs;
			rs = resilienceParser(toRatio(dataset));
			return dataset[(dataset.length - 1)] + rs
					* dataset[(dataset.length - 1)];
		}
	}

	public double getNeg_weight() {
		return neg_weight;
	}

	public void setNeg_weight(double neg_weight) {
		this.neg_weight = neg_weight;
	}

	public int getReactance() {
		return reactance;
	}

	public void setReactance(int reactance) {
		this.reactance = reactance;
	}

	public int getWeight_valid() {
		return weight_valid;
	}

	public void setWeight_valid(int weight_valid) {
		this.weight_valid = weight_valid;
	}

	public int getSliding_flag() {
		return sliding_flag;
	}

	public void setSliding_flag(int sliding_flag) {
		this.sliding_flag = sliding_flag;
	}

}
