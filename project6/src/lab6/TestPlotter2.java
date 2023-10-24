package lab6;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;
import plotter.Plotter;
import plotter.Polyline;


public class TestPlotter2 {

	private static Polyline parseOneLine(String line) throws FileNotFoundException {
	//	File file = new File("test");
		final int DEFAULT = 1;
		Scanner sc = new Scanner(line);
		int width = DEFAULT; // width of 1 by default

		if (sc.hasNextInt()) { //if first scan is a number, set width to it
			width = sc.nextInt();
		}
		String color = sc.next(); //for next scan, after the width, should be color
		Polyline p = new Polyline(color, width);
		
		while(sc.hasNextInt()) { //while there are more ints to scan
			int x = sc.nextInt(); //first of the two points 
			int y = sc.nextInt(); //second of the two points
			p.addPoint(new Point(x, y)); 
			
		}
		sc.close();
		return p;
		
		
	}
	
	private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException {
		ArrayList<Polyline> linesList = new ArrayList<>(); //creates an array list of polyline objects
		
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		while(sc.hasNextLine()) { //loop to keep scanning next line
			
			String line = sc.nextLine();
			line.trim(); //trims whitespace
			if (line.length() > 0 && !line.contains("#")) { //if length of the line is greaters than 0 and
															   // the line is not a comment
			linesList.add(parseOneLine(line)); //add the parsed line to the lines list
			}
		}
		sc.close();
		return linesList;
		
	}
	
	
	/*public static void main(String[] args) throws FileNotFoundException {
		
		Plotter plotter = new Plotter();
		Polyline p = parseOneLine("red 100 100 200 100 200 200 100 200 100 100");
		plotter.plot(p);
		
		p = parseOneLine(" 2 blue 250 100 400 350 100 350 250 100");
		plotter.plot(p);
		
	}
	*/
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Polyline> list = readFile("hello");
		Plotter plotter = new Plotter();
		
		for (Polyline p : list) 
		{
			plotter.plot(p);
		}
	}

}
