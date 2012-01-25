package CS565;

import java.lang.Exception;

public class Rational {
	private int numerator, denominator;
	
	// ctor that takes a numerator and denominator to form a Rational
	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		
		this.Reduce(); //reduce immediately
	}
	
	// no-arg ctor to initialize a "1"
	public Rational() {
		this(1,1);
	}
	
	public void Reduce() {
		int gcd = MathHelper.GreatestCommonDivisor(this.numerator, this.denominator);
		
		// avoid divide by 0
		if (gcd > 0) {
			this.numerator = this.numerator / gcd;
			this.denominator = this.denominator / gcd;
		}
	}
	
	private void adjustToLCM(int lcm) {
		int factor = lcm/this.denominator;
		
		this.numerator *= factor;
		this.denominator = lcm;
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
		
		int lcm = MathHelper.LeastCommonMultiple(one.denominator, two.denominator);
		one.adjustToLCM(lcm);
		two.adjustToLCM(lcm);
		
		// leaving this in as a sanity check
		if (one.denominator != two.denominator)
			throw new RuntimeException("To add two Rationals they must have the same denominator");
		
		Rational result = new Rational(one.numerator + two.numerator, one.denominator);
		
		return result;
	}

	public static Rational subtract(Rational one, Rational two) {
		int lcm = MathHelper.LeastCommonMultiple(one.denominator, two.denominator);
		one.adjustToLCM(lcm);
		two.adjustToLCM(lcm);
		
		// leaving this in as a sanity check
		if (one.denominator != two.denominator)
			throw new RuntimeException("To subtract two Rationals they must have the same denominator");
		
		Rational result = new Rational(one.numerator - two.numerator, one.denominator);
		
		return result;
	}

	public static Rational multiply(Rational one, Rational two) {
		return new Rational(one.numerator * two.numerator, one.denominator * two.denominator);
	}

	public static Rational divide(Rational one, Rational two) {
		// prevent divide by zero
		if (two.numerator == 0)
			throw new RuntimeException("Cannot divide by Rational with numerator of 0 (divide by zero exception)");
					
		return new Rational(one.numerator * two.denominator, one.denominator * two.numerator);
	}
	
	//TODO override equals and hashcode instead
	public boolean Equals(Rational other) {
		if (this.numerator == other.numerator && this.denominator == other.denominator) {
			return true;
		}
		
		return false;
	}
}
