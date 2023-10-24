package lab6;
import java.util.*;
public class LineNumberer
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
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