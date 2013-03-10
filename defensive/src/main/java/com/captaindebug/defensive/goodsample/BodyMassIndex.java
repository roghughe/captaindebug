package com.captaindebug.defensive.goodsample;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.commons.lang3.Validate;

public class BodyMassIndex {

	/**
	 * Calculate the BMI using Weight(kg) / height(m)2
	 * 
	 * @return Returns the BMI to four significant figures eg nn.nn
	 */
	public Double calculate(Double weight, Double height) {

		Validate.notNull(weight, "Your weight cannot be null");
		Validate.notNull(height, "Your height cannot be null");

		Validate.validState(weight.doubleValue() > 0, "Your weight cannot be zero");
		Validate.validState(height.doubleValue() > 0, "Your height cannot be zero");

		Double tmp = weight / (height * height);

		BigDecimal result = new BigDecimal(tmp);
		MathContext mathContext = new MathContext(4);
		result = result.round(mathContext);

		return result.doubleValue();
	}
}
