package com.strongit.test;

import com.strongit.MovingAverage.MovingAverage;
import com.strongit.compensator.Spring;
//import com.strongit.regression.QuadRegressor;
//import com.strongit.regression.Regressor;

/**
 * 
 * @author Zhong Ziyue
 * 
 * @Email zhongzy@strongit.com.cn
 * 
 * @Date 2014-1-9
 * 
 */

public class testone {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

//		 double dataset[] = {367.68,443.37,395.58,420.53,499.74,464.23,501.42};
//		 double dataset[] = {367.68,443.37,395.58,420.53,499.74,464.23};
//		 double dataset[] = {367.68,443.37,395.58,420.53,499.74};
//		 double dataset[] = {367.68,443.37,395.58,420.53}; // QuadRegressor unavailable
//		 double dataset[] = {367.68,443.37,395.58}; // QuadRegressor unavailable
		// **************************************************
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90, 430.11, 410.28,410.75, 456.04,506.30,490.41}; //539.11
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90, 430.11, 410.28,410.75, 456.04,506.30};
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90, 430.11, 410.28,410.75, 456.04};
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90, 430.11, 410.28,410.75};
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90, 430.11, 410.28};
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90, 430.11};
//		double dataset[] = { 347.78, 364.93, 332.34, 356.90}; // QuadRegressor unavailable
//		double dataset[] = { 347.78, 364.93, 332.34}; // QuadRegressor unavailable
	//*********************************************************************************
//		double dataset[] = {238.98,309.77,290.76,292.87,352.91,338.96,340.95,370.23,375.50};  //376.62	
//		double dataset[] = {238.98,309.77,290.76,292.87,352.91,338.96,340.95,370.23};
		double dataset[] = {238.98,309.77,290.76,292.87,352.91,338.96,340.95};
//		double dataset[] = {238.98,309.77,290.76,292.87,352.91,338.96};
//		double dataset[] = {238.98,309.77,290.76,292.87,352.91};
//		double dataset[] = {238.98,309.77,290.76,292.87};
//		double dataset[] = {238.98,309.77,290.76};
//		double dataset[] = {878200,649300,937000,823900,885400};
//		double dataset[] = {878200,649300,937000,823900};
//		double dataset[] = {878200,649300,937000};
		double forecast1;
		double forecast2;
		double forecast3;
		double forecast4;
		double forecast5;

		Spring sp = new Spring();
		forecast1 = sp.forecast(dataset, 1);
		
		Spring sp2 = new Spring(0);
		forecast5 = sp2.forecast(dataset, 1);

//		QuadRegressor qr = new QuadRegressor();
//		forecast2 = qr.forecast(dataset);

//		Regressor rg = new Regressor();
//		forecast3 = rg.forecast(dataset);

		MovingAverage ma = new MovingAverage();
		forecast4 = ma.forecast(dataset, 1);

		System.out.println("spring:" + forecast1);
		System.out.println("Fixedspring:" + forecast5);
//		System.out.println("Quadratic Regressor:" + forecast2);
//		System.out.println("Rregressor:" + forecast3);
		System.out.println("Moving Average:" + forecast4);
	}

}
