package lab6;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineNumberer3
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File file = new File("C:/Users/omran/eclipse-workspace/project5/src/lab5/SimpleLoops.java");
    System.out.println(file.exists());          // true if the file exists
    System.out.println(file.getName());         // name of the file 
    System.out.println(file.getAbsolutePath()); // absolute path to the file
    System.out.println(file.length());          // size of the file
    System.out.println();
    Scanner scanner = new Scanner(file);
    int lineCount = 1;

    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      System.out.print(lineCount + " ");
      System.out.println(line);
      lineCount += 1;
    }
    scanner.close();
  }
}
