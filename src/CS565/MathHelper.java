package CS565;

public class MathHelper {
	
	// Find the Greatest Common Divisor using Euclid's Algorithm
	// Inspired by: http://en.wikipedia.org/wiki/Greatest_common_divisor#Using_Euclid.27s_algorithm
	public static int GreatestCommonDivisor(int a, int b) {
		// base case: when b is zero, we've found the GCD 
		if (b == 0) {
			return a;
		}
		
		// recurse until we find base case
		return GreatestCommonDivisor(b, a % b);
	}
	
	
	// Inspired by: http://en.wikipedia.org/wiki/Least_common_multiple#Reduction_by_the_greatest_common_divisor
	public static int LeastCommonMultiple(int a, int b) {
		return (Math.abs(a * b)) / GreatestCommonDivisor(a, b);
	}
}
