package com.xiaogua.better.apache;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonValidatorCode {
	private static final Logger log = LoggerFactory.getLogger(CommonValidatorCode.class);

	public static boolean validateLuhn(String num) {
		LuhnCheckDigit luhn = new LuhnCheckDigit();
		return luhn.isValid(num);
	}

	public static String calculateLuhn(String panNUmber) {
		LuhnCheckDigit luhn = new LuhnCheckDigit();
		String checkdigit = null;
		try {
			checkdigit = luhn.calculate(panNUmber);
		} catch (Exception e) {
			log.error("calculateLuhn", e);
		}
		return checkdigit;
	}

}
