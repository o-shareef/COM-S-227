package lab3;

import static org.junit.Assert.*;

import org.junit.Test;

import balloon3.Balloon;

public class BalloonTesting {
	
	private static final double EPSILON = 10e-07;
	 
	@Test
	public void testInitialRadius () {
		Balloon b = new Balloon(5);
		assertEquals(0, b.getRadius());
	}
	@Test
	public void testBlow() {

		Balloon b = new Balloon(5);
		b.blow(5);
		assertEquals(5, b.getRadius());
	}
	@Test
	public void testDeflate() {
		
		Balloon b = new Balloon(5);
		b.deflate();
		assertEquals(0, b.getRadius());	
	}
	@Test
	public void testPoppedRadius() {
		
		Balloon b = new Balloon(5);
		b.pop();
		assertEquals(0, b.getRadius());	
	}
	@Test
	public void testMaxRadius() {
		
		Balloon b = new Balloon(5);
		b.blow(10);
		assertEquals(0, b.getRadius(), EPSILON );
		
	}
	@Test 
	public void testNegativeBlow() {
		
		Balloon b = new Balloon(20);
		b.blow (-10);
		assertEquals(0, b.getRadius(), EPSILON);
	}
	@Test
	public void testIsPopped() {
		
		Balloon b = new Balloon(5);
		b.pop();
		assertEquals(true, b.isPopped());
	}
	@Test
	public void testBlowPoppedBalloon() {
		
		Balloon b = new Balloon(5);
		b.pop();
		b.blow(3);
		assertEquals(0,b.getRadius(), EPSILON);
	}
	@Test
	public void testBlowDeflatedBalloon() {
		
		Balloon b = new Balloon(5);
		b.deflate();
		b.blow(3);
		assertEquals(3, b.getRadius(), EPSILON);
	}
	@Test
	public void testIsPopped1() {
		
		Balloon b = new Balloon(5);
		b.blow(4);
		assertEquals(false, b.isPopped());
	}
	@Test
	public void testBlowThenPop() {
		
		Balloon b = new Balloon(5);
		b.blow(4);
		b.pop();
		assertEquals(true, b.isPopped());
		
	}
	@Test
	public void testDeflatePop() {
		
		Balloon b = new Balloon(5);
		b.deflate();
		b.pop();
		assertEquals(true, b.isPopped());
	}
	@Test
	public void testBlowDeflateBlow() {
		
		Balloon b = new Balloon(5);
		b.blow(4);
		b.deflate();
		b.blow(10);
		assertEquals(0, b.getRadius());
	}
	@Test
	public void testDeflatePopped() {
		
		Balloon b = new Balloon(5);
		b.pop();
		b.deflate();
		assertEquals(true, b.isPopped());
	}
	@Test
	public void testInitialRadius1() {
		Balloon b = new Balloon(-5);
		assertEquals(false, b.isPopped());
	}
	
	@Test
	public void testInitialRadius2() {
		Balloon b = new Balloon(-5);
		b.blow(5);
		assertEquals(0, b.getRadius());
	}
	
	@Test
	public void testBlowTwice() {
		Balloon b = new Balloon(5);
		b.blow(4);
		b.blow(3);
		assertEquals(0, b.getRadius());
	}

}
