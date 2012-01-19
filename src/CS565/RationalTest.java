package CS565;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RationalTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void CanInitializeWithDefaultCtor() {
		Rational frac = new Rational(1, 2);
		Assert.assertNotNull("full ctor should return a valid object", frac);
	}
	
	@Test
	public void CanInitializeWithoutValues() {
		Rational frac = new Rational();
		Assert.assertNotNull("default ctor should return a valid object", frac);
	}

}
