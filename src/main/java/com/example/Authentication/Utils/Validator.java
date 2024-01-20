package com.example.Authentication.Utils;

import java.util.regex.Pattern;

public class Validator {

	public boolean isValidEmail(String email) {

		String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailRegexPattern);

		return pattern.matcher(email).matches();
	}
}
