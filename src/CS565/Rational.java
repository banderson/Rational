package CS565;

public class Rational {
	private int numerator, denominator;
	private static final int DEFAULT_NUM_DECIMALS = 2;
	
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
	
	// reduce the fraction to it's lowest form
	public void Reduce() {
		int gcd = MathHelper.GreatestCommonDivisor(this.numerator, this.denominator);
		
		// avoid divide by 0
		if (gcd > 0) {
			this.numerator = this.numerator / gcd;
			this.denominator = this.denominator / gcd;
		}
	}
	
	// given an LCM, adjust the fraction to it's equivalent form
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
		return this.toFloatString(DEFAULT_NUM_DECIMALS);
	}
	
	// convert to floating point string
	public String toFloatString(int numDecimals) {
		// cast each to float so we don't lose precision from ints
		float result = (float)this.numerator / (float)this.denominator;
		
		return String.format("%."+ numDecimals +"f", result); // + "" converts it to a string 
	}

	// Rational addition
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

	// Rational subtraction
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

	// Rational multiplication
	public static Rational multiply(Rational one, Rational two) {
		return new Rational(one.numerator * two.numerator, one.denominator * two.denominator);
	}

	// Rational division, using cross multiplication
	public static Rational divide(Rational one, Rational two) {
		// prevent divide by zero
		if (two.numerator == 0)
			throw new RuntimeException("Cannot divide by Rational with numerator of 0 (divide by zero exception)");
					
		return new Rational(one.numerator * two.denominator, one.denominator * two.numerator);
	}
	
	@Override public boolean equals(Object o) {
		// if it's an object of different type it's always false
		if (!(o instanceof Rational))
			return false;
		
		// cast the object to Rational
		Rational other = (Rational)o;
		// since the Rational is always reduced we can do this simple compare
		if (this.numerator == other.numerator && this.denominator == other.denominator) {
			return true;
		}
		
		return false;
	}
	
	// we must override this since we overrode equals above
	@Override public int hashCode() {
		// for details on the use of result and prime below, see Effective Java, 2E, page 47-48
		int result = 7; // arbitrary number
		int prime = 31; // this ensures order matters
		
		// factor in each field used in the equals method
		result = prime * result + numerator;
		result = prime * result + denominator;
		
		return result;
	}
}
