package lab6;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor
{
  public static void main(String[] args) throws FileNotFoundException
  {
    Scanner sc = new Scanner(System.in);
    File outFile = new File("mydocument.txt");
    PrintWriter out = new PrintWriter(outFile);
    


    // Echo keyboard input out to the file.
    while (sc.hasNextLine())
    {
      String line = sc.nextLine();
      out.println(line);
    }
    
    System.out.println("Done");
    out.close(); // Important: don't forget to close!
    sc.close();
  }
}