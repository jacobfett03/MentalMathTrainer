package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyEmail {
	boolean validEmail;
	
	public VerifyEmail(String email) {
		validEmail = validEmail(email);
	}
	
	public static boolean validEmail(String email) {
		
		String regexEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern patternEmail = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
		Matcher match = patternEmail.matcher(email);
		return match.find();
	}
	
	
}
