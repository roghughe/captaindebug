package com.captaindebug.defensive.badsample;

import java.math.BigDecimal;
import java.math.MathContext;

public class BodyMassIndex {

	/**
	 * Calculate the BMI using Weight(kg) / height(m)2
	 * 
	 * @return Returns the BMI to four significant figures eg nn.nn
	 */
	public Double calculate(Double weight, Double height) {

		Double result = null;

		if ((weight != null) && (height != null) && (weight > 0.0) && (height > 0.0)) {

			Double tmp = weight / (height * height);

			BigDecimal bd = new BigDecimal(tmp);
			MathContext mathContext = new MathContext(4);
			bd = bd.round(mathContext);
			result = bd.doubleValue();
		}

		return result;
	}
}
