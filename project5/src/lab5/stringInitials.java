package lab5;

import java.util.Scanner;

public class stringInitials {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = sc.nextLine();
		System.out.println("Initials: " + initials(name));
		
		
		System.out.println("Enter a word ");
		String word = sc.nextLine();
		System.out.println("Fisrt vowel index: " + vowelIndex(word));
		
		
		
		
	}

	public static String initials(String userName) {
		String result = "";
		Scanner sc = new Scanner(userName);
		while(sc.hasNext()) {
			
			char current = sc.next().charAt(0);
			result = result + current;
			
			
		}
	return result;
			
		}
	
	public static int vowelIndex(String userInput) {
		
		
		for(int i = 0; i < userInput.length(); i++) {
			char current = userInput.charAt(i);
			if("aeiouAEIOU".indexOf(current) >= 0) {
				return i;
				
			
			}
			
			}
		return -1;
		}
		
		
		
		
		
	}
	

		

		

	
	
	
	
	
	

