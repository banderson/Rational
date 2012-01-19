package CS565;

import java.lang.Exception;

public class Rational {
	private int numerator, denominator;
	
	// ctor that takes a numerator and denominator to form a Rational
	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	// no-arg ctor to initialize a "1"
	public Rational() {
		this.numerator = 1;
		this.denominator = 1;
	}

	// convert to fraction string
	public String toFractionString() {
		return this.numerator + "/" + this.denominator;
	}
	
	// convert to floating point string
	public String toFloatString() {
		// cast each to float so we don't lose precision from ints
		float result = (float)this.numerator / (float)this.denominator;
		
		return result + ""; // + "" converts it to a string 
	}

	public static Rational add(Rational one, Rational two) {
		// temporary limitation until we get the LCD stuff done
		if (one.denominator != two.denominator)
			throw new RuntimeException("To add two Rationals they must have the same denominator");
		
		Rational result = new Rational(one.numerator + two.numerator, one.denominator);
		
		return result;
	}

	public static Rational subtract(Rational one, Rational two) {
		// temporary limitation until we get the LCD stuff done
		if (one.denominator != two.denominator)
			throw new RuntimeException("To subtract two Rationals they must have the same denominator");
		
		Rational result = new Rational(one.numerator - two.numerator, one.denominator);
		
		return result;
	}

	public static Rational multiply(Rational one, Rational two) {
		return new Rational(one.numerator * two.numerator, one.denominator * two.denominator);
	}

	public static Rational divide(Rational one, Rational two) {
		return new Rational(one.numerator * two.denominator, one.denominator * two.numerator);
	}
}
