// Nicholas Bonat
//lab1 EC

import java.util.Scanner;  

public class EC {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
    	int numProblems = scanner.nextInt();
    	String a = scanner.nextLine();

    	// loop through the input
	    while(scanner.hasNext()){
	    	String b = scanner.nextLine();
	    	
	    	// check to see if the input is a Plaindrome or not
	    	if(isPalindromeEC(b)){
	    		System.out.println("This is a Palindrome.");
	    	} else{
	    		System.out.println("Not a Palindrome.");
	    	}
	    }
			
		scanner.close();

	}
	
	
	
	public static boolean isPalindromeEC(String s){

		// Implement if you wish to do the extra credit.
		TwoStackQueue<Integer> queue = new TwoStackQueue<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        int integer;

        for(int i = 0; i < s.length(); i++){
        	integer = Character.getNumericValue(s.charAt(i));
        	queue.enqueue(integer);
        	stack.push(integer);
        }

        while(!queue.isEmpty() && !stack.isEmpty()){
        	if(queue.dequeue().getData() != stack.pop().getData()){
        		return false;
        	}
        }
        
        
        return true;
		
	}
}
