package CS565;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RationalTest {

	Rational half, whole;
	
	@Before
	public void setUp() throws Exception {
		half = new Rational(1, 2);
		whole = new Rational();
	}

	@After
	public void tearDown() throws Exception {
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
		Assert.assertTrue("String value should be 1/2, was "+ half.toFloatString(), half.toFloatString().equals("0.5"));
		Assert.assertTrue("String value should be 1/1, was "+ whole.toFloatString(), whole.toFloatString().equals("1.0"));
	}
	
	@Test
	public void CanAddRationalNumbers() {
		Rational another = new Rational(4,2); // overrides class whole variable
		Rational result = Rational.add(half, another);
		
		Assert.assertTrue("String value should be 5/2", result.toFractionString().equals("5/2"));
		Assert.assertTrue("String value should be 2.5", result.toFloatString().equals("2.5"));
	}

	@Test
	public void CanSubtractRationalNumbers() {
		Rational another = new Rational(4,2); // overrides class whole variable
		Rational result = Rational.subtract(another, half);
		
		Assert.assertTrue("String value should be 3/2, was "+ result.toFractionString(), result.toFractionString().equals("3/2"));
		Assert.assertTrue("String value should be 1.5", result.toFloatString().equals("1.5"));
	}
	
	@Test
	public void TryAddRationalWithDiffDenominatorThrowsException() {
		try {
			Rational.add(whole, half);
		} catch (Exception e) { 
			/* do nothing, it was supposed to be caught */
			return;
		}
		
		Assert.fail("Should have thrown exception since denominators are different");
	}

	@Test
	public void TrySubtractRationalWithDiffDenominatorThrowsException() {
		try {
			Rational.subtract(whole, half);
		} catch (Exception e) { 
			/* do nothing, it was supposed to be caught */
			return;
		}
		
		Assert.fail("Should have thrown exception since denominators are different");
	}

}
