// Nicholas Bonat
//lab1

import java.util.Scanner;  

public class lab1 {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
    	int numProblems = scanner.nextInt();
    	String a = scanner.nextLine();

    	// loop through the input
	    while(scanner.hasNext()){
	    	String b = scanner.nextLine();
	    	
	    	// check to see if the input is a Plaindrome or not
	    	if(isPalindrome(b)){
	    		System.out.println("This is a Palindrome.");
	    	} else{
	    		System.out.println("Not a Palindrome.");
	    	}
	    }
			
		scanner.close();

	}
	
	public static boolean isPalindrome(String s){
		// Create a stack and a Queue of chars that represents the passed in string
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a xc or not
		
	    Queue<Integer> queue = new Queue<Integer>();
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
	
	public static boolean isPalindromeEC(String s){

		//TwoStackQueue<Integer> tsq = new TwoStackQueue<Integer>();
		// Implement if you wish to do the extra credit.
		return false;
		
	}
}
