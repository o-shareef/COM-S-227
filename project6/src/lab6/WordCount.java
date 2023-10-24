package lab6;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("story.txt");
		Scanner sc = new Scanner(file);
		int wordCountPerLine = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			line.trim();
			String[] words = line.split(" ");
			
			wordCountPerLine = words.length;
			
			if (words.length == 1) { //if no words
				wordCountPerLine = 0;
			}
			
			
			System.out.println(wordCountPerLine + " ");
		
		}
	
		sc.close();

	}

}
