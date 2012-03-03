package CS565;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class RationalTestRun {
	
	public static void main(String[] args) {
		// numerator and denominator for both Rationals
		int n1, d1, n2, d2;
		// operation to perform
		String op = "+";
		// rationals for operands and result
		Rational first, second, result;

		System.out.println("Enter your expression in the form n/n [+-*/] n/n where n is an integer or EXIT to quit.");
		System.out.println("\tExamples: 1/2 + 2/1 \t 1/2 - 1/3 \t 1/2 * 1/3 \t 1/2 / 1/3");
		
		// indicate whether we should continue asking for input 
		boolean done = false; 
		
		while (done == false) {
			Scanner input = new Scanner(System.in);
			// scan the input for the correct rational expression pattern
			input.findInLine("(\\d+)/(\\d+)\\s*([+*/-])\\s*(\\d+)/(\\d+)");
			try {
				MatchResult matches = input.match();
				// get the first Rational
				n1 = Integer.parseInt(matches.group(1));
				d1 = Integer.parseInt(matches.group(2));
				first = new Rational(n1, d1);
				
				op = matches.group(3);
				
				// get the second Rational
				n2 = Integer.parseInt(matches.group(4));
				d2 = Integer.parseInt(matches.group(5));
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
				
				System.out.println("\t"+ first.toFractionString() +" "+ op +" "+ second.toFractionString() + " == "+ result.toFractionString());
				
			} catch (IllegalStateException e) {
				// if the pattern wasn't found, handle other cases
				if (input.nextLine().equalsIgnoreCase("EXIT")) {
					System.out.println("Thanks for playing!");
					done = true; //stop looping
				} else {
					System.out.println("Your input doesn't match the correct expression pattern. Try again...\n");
				}
			}
		}
	}
}