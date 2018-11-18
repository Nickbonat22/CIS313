// Nicholas Bonat

import java.util.Scanner;

public class lab3 {
    public static void main(String[] args) {
    
    	// Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, maximum, extractMax, isEmpty, or print
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!
        
    	Scanner scan = new Scanner(System.in);
        
        int numProblems = scan.nextInt();
        pQueue<Integer> pQ = new pQueue<Integer>(numProblems);
        String skip = scan.nextLine();
        String input;
        String task;

        while(scan.hasNext()){
        	input = scan.nextLine();   	
        	String[] phrases = input.split(" ");
        	task = phrases[0];
        	switch(task){
        		case "insert":
        			pQ.insert(Integer.parseInt(phrases[1]));
        			break;

        		case "maximum":
        			System.out.println(pQ.maximum());
        			break;

        		case "extractMax":
        			System.out.println(pQ.extractMax());
        			break;

        		case "isEmpty":
        			if(pQ.isEmpty()){
        				System.out.println("Empty");
        			}
        			else {
        				System.out.println("Not Empty");
        			}
        			break;

        		case "print":
        			pQ.print();
        			break;

        		case "build":
					// remove first and last character (the brackets) from the input string
					// credit: https://stackoverflow.com/questions/8846173/how-to-remove-first-and-last-character-of-a-string
					String numString = phrases[1].substring(1, phrases[1].length() - 1);
					String[] numStrArray = numString.split(",");
                    Integer[] numIntArray = new Integer[numStrArray.length + 1];

					for(int i = 1; i < numIntArray.length; i++){
						numIntArray[i] = Integer.parseInt(numStrArray[i - 1]); 
					}
					pQ.build(numIntArray);
					break;
        	}
        }
        scan.close();
    }
}
