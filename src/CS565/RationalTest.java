package CS565;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RationalTest {

	Rational half, whole;
	
	@Before
	public void setUp() throws Exception {
		half = new Rational(1, 2);
		whole = new Rational(); // defaults to (1,1)
	}
	
	@Test
	public void CanInitializeWithDefaultCtor() {
		Assert.assertNotNull("full ctor should return a valid object", half);
	}
	
	@Test
	public void CanInitializeWithoutValues() {
		Assert.assertNotNull("default ctor should return a valid object", whole);
	}
	
	@Test
	public void CanReturnStringValAsFraction() {
		Assert.assertTrue("String value should be 1/2", half.toFractionString().equals("1/2"));
		Assert.assertTrue("String value should be 1/1", whole.toFractionString().equals("1/1"));
	}
	
	@Test
	public void CanReturnStringValAsFloat() {
		Assert.assertTrue("String value should be 1/2, was "+ half.toFloatString(), half.toFloatString().equals("0.50"));
		Assert.assertTrue("String value should be 1/1, was "+ whole.toFloatString(), whole.toFloatString().equals("1.00"));
	}
	
	@Test
	public void CanAddRationalNumbers() {
		Rational another = new Rational(4,2); // overrides class whole variable
		Rational result = Rational.add(half, another);
		
		Assert.assertTrue("String value should be 5/2, "+ result.toFractionString(), result.toFractionString().equals("5/2"));
		Assert.assertTrue("String value should be 2.5", result.toFloatString().equals("2.50"));
	}

	@Test
	public void CanSubtractRationalNumbers() {
		Rational another = new Rational(4,2); // overrides class whole variable
		Rational result = Rational.subtract(another, half);
		
		Assert.assertTrue("String value should be 3/2, was "+ result.toFractionString(), result.toFractionString().equals("3/2"));
		Assert.assertTrue("String value should be 1.5", result.toFloatString().equals("1.50"));
	}
	
	@Test
	public void TryAddRationalWithDiffDenominatorIsFine() {
		try {
			Rational.add(whole, half);
		} catch (Exception e) {
			Assert.fail("Should no longer throw exception when denominators are different");
		}
	}

	@Test
	public void TrySubtractRationalWithDiffDenominatorIsFine() {
		try {
			Rational.subtract(whole, half);
		} catch (Exception e) {
			Assert.fail("Should no longer throw exception when denominators are different");
		}
	}

	@Test
	public void CanMultiplyRationalNumbers() {
		Rational result1 = Rational.multiply(whole, half);
		Assert.assertTrue("String value should be 1/2, was "+ result1.toFractionString(), result1.toFractionString().equals("1/2"));
		Assert.assertTrue("String value should be 0.5", result1.toFloatString().equals("0.50"));

		Rational another = new Rational(4,2); // overrides class whole variable
		Rational result2 = Rational.multiply(another, half);
		Assert.assertTrue("String value should be 4/4, was "+ result2.toFractionString(), result2.toFractionString().equals("1/1"));
		Assert.assertTrue("String value should be 1.0", result2.toFloatString().equals("1.00"));
	}

	@Test
	public void CanDivideRationalNumbers() {
		Rational result1 = Rational.divide(whole, half);
		Assert.assertTrue("String value should be 2/1, was "+ result1.toFractionString(), result1.toFractionString().equals("2/1"));
		Assert.assertTrue("String value should be 2.0", result1.toFloatString().equals("2.00"));

		Rational another = new Rational(4,2); // overrides class whole variable
		Rational result2 = Rational.divide(another, half);
		Assert.assertTrue("String value should be 8/2, was "+ result2.toFractionString(), result2.toFractionString().equals("4/1"));
		Assert.assertTrue("String value should be 4.0", result2.toFloatString().equals("4.00"));
	}
 
	@Test
	public void TryDivideByZero() {
		//TODO: finish this!!
		Rational zero = new Rational(0,0);// Rational.divide(whole, half);
		try {
			Rational.divide(half, zero);
		} catch (RuntimeException re) {
			// do nothing, this is expected
			Assert.assertTrue("The divide-by-zero dexception should have been thrown -> "+ re.toString(), re.toString().endsWith("(divide by zero exception)"));
			return;
		}
		
		Assert.fail("Should've returned with divide-by-zero exception");
	}
	
	@Test
	public void ShouldReturnReducedForm() {
		Rational full = new Rational(4,4); // should reduce to 1/1
		Rational mid = new Rational(3,6); // should reduce to 1/2
		Rational edge = new Rational(17,51); // should reduce to 1/3
		Rational third = new Rational(3,9); // should reduce to 1/3
		
		Assert.assertTrue("Should equal equivalent Rational in reduced form (1/1)", whole.equals(full));
		Assert.assertTrue("Should be 1/1 in reduced form", whole.toFractionString().equals("1/1"));
		
		Assert.assertTrue("Should equal equivalent Rational in reduced form (1/2)", half.equals(mid));
		Assert.assertTrue("Should be 1/2 in reduced form", half.toFractionString().equals("1/2"));
		
		Assert.assertTrue("Should equal equivalent Rational in reduced form (1/3)", edge.equals(third));
		Assert.assertTrue("Should be 1/3 in reduced form", third.toFractionString().equals("1/3"));
	}
	
	@Test
	public void CanAddWithDifferentDenominators() {
		Rational threeHalfs = new Rational(6, 4);
		Assert.assertTrue("Result should be same as reduced half Rationals", Rational.add(whole, half).equals(threeHalfs));
	}
	
	@Test
	public void AddShouldBeCommutative() {
		Assert.assertTrue("Adding rationals should be commutative", Rational.add(whole, half).equals(Rational.add(half, whole)));
	}
	
	@Test
	public void CanSubtractWithDifferentDenominators() {
		Rational.subtract(whole, half);
		
		Assert.assertTrue("Result should be same as reduced half Rationals", Rational.subtract(whole, half).equals(half));
		Assert.assertTrue("Final result should be 1/2", Rational.subtract(whole, half).toFractionString().equals("1/2"));
	}
	
	@Test
	public void CanSpecifyNumDecimals() {
		Assert.assertEquals("Should have 2 decimals", whole.toFloatString(2), "1.00");
		Assert.assertEquals("Should have 0 decimals", whole.toFloatString(0), "1");
		
		Assert.assertEquals("Should have 1 decimals", half.toFloatString(1), "0.5");
		Assert.assertEquals("Should have 0 decimals, rounded", half.toFloatString(0), "1");
		
		Rational third = new Rational(3,9); // should reduce to 1/3
		Assert.assertEquals("Should have 5 decimals", third.toFloatString(5), "0.33333");
		Assert.assertEquals("Should have 0 decimals", third.toFloatString(0), "0");
	}

}
