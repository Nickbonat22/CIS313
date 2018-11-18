// Nicholas Bonat

import java.util.Comparator;
import java.lang.Math;

public class MaxHeap<E extends Comparable> {
    private E[] myArray;
    private int maxSize;
    private int length;

    public MaxHeap(int s){
    	// Build the constructor
        setMaxSize(s);
        setLength(0);
        myArray = (E[]) new Comparable[s];
    }

	// helper functions
    public E[] getArray(){
        return myArray;
    }

    public void setArray(E[] newArray){
    	if (newArray.length > maxSize){
    		return;
    	}
        myArray = newArray;
        length = newArray.length-1;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void setMaxSize(int ms){
        maxSize = ms;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int l){
        length = l;
    }

    private int getParent(int i){
        return i / 2;
    }

    private void exchange(int i, int parent, E data){
        // helper function to exchange index of i with index of parent 
        myArray[i] = myArray[parent];
        myArray[parent] = data;
    }

    // Other Methods
    public void insert(E data){
    	// Insert an element into your heap.
    	
    	// When adding a node to your heap, remember that for every node n, 
    	// the value in n is less than or equal to the values of its children, 
    	// but your heap must also maintain the correct shape.
		// (ie there is at most one node with one child, and that child is the left child.)
		// (Additionally, that child is farthest left possible.)

        setLength(length + 1);
        myArray[length] = data;
        int i = length;
        int parent = getParent(length);
        while(i > 1 && myArray[parent].compareTo(myArray[i]) < 0 ){
            exchange(i,parent,data);
            i = parent;
            parent  = getParent(i);
        }
    }

    public Comparable<E> maximum(){
        // return the minimum value in the heap
        if(myArray.length != 0){
            return myArray[1];
        }
        else {
            return null;
        }
    }

    public Comparable<E> extractMax(){
        // remove and return the minimum value in the heap
        if(myArray.length != 0){
            Comparable<E> max = maximum();
            myArray[1] = myArray[length];
            setLength(length - 1);
            heapify(1);
            return max;
        }
        else {
            return null;
        }
    }

    private void swap(int current, int largest){
        // helper function to swap for the largest
        E max = myArray[current];
        myArray[current] = myArray[largest];
        myArray[largest] = max;
    }
    
    public void heapify(int i){
    	// helper function for reshaping the array
        int largest = i;
        int leftChild = i * 2;
        int rightChild = i * 2 + 1;

        //if(myArray[leftChild] != null && myArray[rightChild] != null){
            if(leftChild <= length && myArray[leftChild].compareTo(myArray[largest]) > 0){
                largest = leftChild;
            }
            else {
                largest = i;
            }
            if(rightChild <= length && myArray[rightChild].compareTo(myArray[largest]) > 0){
                largest = rightChild;
            }
            // swap
            if(i != largest){
                swap(i, largest);
                heapify(largest);
            }
        //}
    }
    
    public void buildHeap(E[] newArr){
		// used for the extra credit
        setMaxSize(newArr.length);
        setArray(newArr);
        for(int i = length / 2; i >= 1; i--){
            heapify(i);
        }
	}
}
