package CS565;

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
	
	// The constructor should store the fraction in reduced form
	// NOTE: apply Euclid's algorithm
}
