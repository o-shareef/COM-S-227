package lab3;

import balloon1.Balloon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author omran shareef
 *
 */
public class BalloonTests {

	@Test
	public void testInitialRadius ()
	{
		Balloon b = new Balloon(5);
		assertEquals(0, b.getRadius();
	}

	@Test
	public void testIsPopped() {
		Balloon b = new Balloon(5);
		assertEquals(false, b.isPopped());
	}

	@Test
	public void testBlow() {

		Balloon b = new Balloon(5);
		assertEquals(5, b.blow(5));
	}

}
