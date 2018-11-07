// Nicholas Bonat
// Lab 2 Extra Credit

import java.util.Scanner;

public class TreeCompare {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        BST<Integer> bst1 = new BST<Integer>();
        BST<Integer> bst2 = new BST<Integer>();
        int numProblems = scan.nextInt();
        String skip = scan.nextLine();
        String input;
        String task;

        while(scan.hasNext()){
        	input = scan.nextLine();

            // switch to inserting to the second tree
            if(input.length() == 1){
                while(scan.hasNext()){
                    input = scan.nextLine();
                    String[] phrases = input.split(" ");
                    task = phrases[0];
                    switch(task){
                        case "insert":
                            bst2.insert(Integer.parseInt(phrases[1]));
                            break;

                        case "delete":
                            bst2.delete(Integer.parseInt(phrases[1]));
                            break;

                        case "find":
                            bst2.find(Integer.parseInt(phrases[1])).getData();
                            break;

                        case "inorder":
                            bst2.traverse("inorder", bst2.getRoot());
                            System.out.println("");
                            break;

                        case "preorder":
                            bst2.traverse("preorder", bst2.getRoot());
                            System.out.println("");
                            break;

                        case "postorder":
                            bst2.traverse("postorder", bst2.getRoot());
                            System.out.println("");
                            break;
                    }
                }
                
            }
        	String[] phrases = input.split(" ");
        	task = phrases[0];
        	switch(task){
        		case "insert":
        			bst1.insert(Integer.parseInt(phrases[1]));
        			break;

        		case "delete":
        			bst1.delete(Integer.parseInt(phrases[1]));
        			break;

                case "find":
                    bst1.find(Integer.parseInt(phrases[1]));
                    break;

        		case "inorder":
        			bst1.traverse("inorder", bst1.getRoot());
        			System.out.println("");
        			break;

        		case "preorder":
        			bst1.traverse("preorder", bst1.getRoot());
        			System.out.println("");
        			break;

        		case "postorder":
        			bst1.traverse("postorder", bst1.getRoot());
        			System.out.println("");
        			break;
        	}
        }
        scan.close();

        //check to see if that tree's shaoe is the same
        if(compareTrees(bst1.getRoot(), bst2.getRoot())){
            System.out.println("The trees have the same shape.");
        }
        else{
            System.out.println("The trees do not have the same shape.");
        }
    }

    public static boolean compareTrees(Node bstOneRoot, Node bstTwoRoot){

            // evading null pointer exception
            if(bstOneRoot == null){
                return false;
            }

            if(bstTwoRoot == null){
                return false;
            }

            if(bstOneRoot == bstTwoRoot){
                return true;
            }

            boolean compare1 = compareTrees(bstOneRoot.getLeftChild(), bstTwoRoot.getLeftChild());
            boolean compare2 = compareTrees(bstOneRoot.getRightChild(), bstTwoRoot.getRightChild());

            if(compare1 && compare2){
                return true;
            }
            else{
                return false;
            }
        }
}