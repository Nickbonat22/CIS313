// Nicholas Bonat
//lab0

import java.util.Scanner;                         //import Scanner package

public class lab0 {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int numProblems = scanner.nextInt();          //retrieve the number of lines
  
    for(int i = 0; i < numProblems; ++i){
      int a = scanner.nextInt();                  //retrieve the first integer
      int b = scanner.nextInt();                  //retrieve the second integer
 
      int g = gcd(a,b);
      int l = lcm(a,b);
 
      System.out.println(g + " " + l);
    }

    scanner.close();
 }

 public static int gcd(int a, int b){
  if (a == 0)
        return b;
      
    while (b != 0) {
        if (a > b)
            a = a-b;
        else
            b = b-a;
    }

    return a;
 }

 public static int lcm(int a, int b){
  int lcm = a*(b/gcd(a, b));

  return lcm;
 }
 
}// end class lab0
