package com.xiaogua.better.apache;

import java.util.Arrays;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.junit.Test;

public class TestCommonsMath {

	@Test
	public void testCommonsMathSimpleRegression() {
		// 一元线性回归
		SimpleRegression regression = new SimpleRegression();
		regression.addData(1d, 2d);
		regression.addData(3d, 3d);
		regression.addData(3d, 3d);
		// double[][] data = { { 1, 3 }, {2, 5 }, {3, 7 }, {4, 14 }, {5, 11 }};
		// regression.addData(data);

		System.out.println(regression.getIntercept());
		System.out.println(regression.getSlope());
	}

	@Test
	public void testCommonsMathMultipleLinearRegression() {
		// 多元线性回归
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		double[] y = new double[] { 11.0, 12.0, 13.0, 14.0, 15.0, 16.0 };
		double[][] x = new double[6][];
		x[0] = new double[] { 0, 0, 0, 0, 0 };
		x[1] = new double[] { 2.0, 0, 0, 0, 0 };
		x[2] = new double[] { 0, 3.0, 0, 0, 0 };
		x[3] = new double[] { 0, 0, 4.0, 0, 0 };
		x[4] = new double[] { 0, 0, 0, 5.0, 0 };
		x[5] = new double[] { 0, 0, 0, 0, 6.0 };
		regression.newSampleData(y, x);
		double[] beta = regression.estimateRegressionParameters();
		System.out.println(Arrays.toString(beta));
	}
}
