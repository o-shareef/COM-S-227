package lab3;

public class Basketball {

private double diameter;

private boolean isInflated;


	public Basketball(double givenDiameter) {
		isInflated = false;
		diameter = givenDiameter;
	}
	
	public boolean isDribbleable () {
		return isInflated;
	}
	
	public double getDiameter () {
		return diameter;
	}
	
	public double getCircumference() {
		double result = Math.PI * diameter;
		return result;
	}
	
	public void inflate() {
		isInflated = true;
		
	}
}