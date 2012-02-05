package CS565;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RationalTestRun {
	
	public static void main(String[] args) {
		// numerator and denominator for both Rationals
		int n1, d1, n2, d2;
		// operation to perform
		String op = "+";
		// rationals for operands and result
		Rational first, second, result;
		
		// scan the input with whitespace and the "/" as delimiters
		Scanner input = new Scanner(System.in).useDelimiter("/|\\s*");

		puts("Enter your expression in the form n/n [+-*/] n/n where n is an integer or EXIT to quit.");
		puts("\tExamples: 1/2 + 2/1 \t 1/2 - 1/3 \t 1/2 * 1/3 \t 1/2 / 1/3");
		
		// indicate whether we should continue asking for input 
		boolean done = false; 
		
		while (done == false) {
			try {
				// get the first Rational
				n1 = input.nextInt();
				d1 = input.nextInt();
				first = new Rational(n1, d1);
				
				// get the operation
				if (input.hasNext("[+-/*]")) {
					op = input.next("[+-/*]");
				} else {
					// since "/" is a delimiter, it won't register in the scanner and we must assume
					op = "/";
					// since "/" is a delimiter, we need to push the scanner object forward
					// I don't know why we need to move it ahead 2 places, but it's required
					input.next(); input.next();
				}
				
				// get the second Rational
				n2 = input.nextInt();
				d2 = input.nextInt();
				second = new Rational(n2, d2);
				
				// choose an operation based on user input
				if (op.equals("+")) {
					result = Rational.add(first, second);
				} else if (op.equals("-")) {
					result = Rational.subtract(first, second);
				} else if (op.equals("*")) {
					result = Rational.multiply(first, second);
				} else { // (op == "/") {
					result = Rational.divide(first, second);
				}
				
				puts("\t"+ first.toFractionString() +" "+ op +" "+ second.toFractionString() + " == "+ result.toFractionString());
			} catch (InputMismatchException iex) {
				if (input.nextLine().equalsIgnoreCase("EXIT")) {
					puts("Thanks for playing!");
					done = true; //stop looping
				} else {
					puts("Oops, you typed something wrong. Try again or type EXIT to quit.");
				}
			}
		}
	}
	
	// shortcut functions to simplify and shorten above code
	private static void puts(String out) {
		System.out.println(out);
	}
}