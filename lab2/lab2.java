import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, preform the corresponding function
        //      (ie) insert, delete, find, inoder, preorder, or postorder
        // Hint: Use a switch-case statement

        // Don't forget to close your Scanner!

        Scanner scan = new Scanner(System.in);
        BST<Integer> bst = new BST<Integer>();
        int numProblems = scan.nextInt();
        String skip = scan.nextLine();
        String input;
        String task;

        while(scan.hasNext()){
        	input = scan.nextLine();   	
        	String[] phrases = input.split(" ");
        	task = phrases[0];
        	switch(task){
        		case "insert":
        			bst.insert(Integer.parseInt(phrases[1]));
        			break;

        		case "delete":
        			bst.delete(Integer.parseInt(phrases[1]));
        			break;

        		case "find":
        			bst.find(Integer.parseInt(phrases[1])).getData();
        			break;

        		case "inorder":
        			bst.traverse("inorder", bst.getRoot());
        			System.out.println("");
        			break;

        		case "preorder":
        			bst.traverse("preorder", bst.getRoot());
        			System.out.println("");
        			break;

        		case "postorder":
        			bst.traverse("postorder", bst.getRoot());
        			System.out.println("");
        			break;
        	}
        }
        scan.close();
    }
}