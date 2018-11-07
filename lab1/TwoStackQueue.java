public class TwoStackQueue<E> {
	private Stack<E> stackTail;
	private Stack<E> stackHead;

	public TwoStackQueue(){
		stackTail = new Stack<E>();
		stackHead = new Stack<E>();
	}

	private void moveTailtoHead(){
		while(!stackTail.isEmpty()){
			stackHead.push(stackTail.pop().getData());
		}
	}

	public void enqueue(E newData){
		Node<E> newNode = new Node<E>(newData, null);
		stackTail.push(newData);
	}

	public Node<E> dequeue(){
		if(stackHead.isEmpty()){
			while (!stackTail.isEmpty()) {
				stackHead.push(stackTail.pop().getData());
			}
		}
		return stackHead.pop();
	} 

	public boolean isEmpty(){
		return stackHead == null;
	}

	public void printQueue(){
		
		// Loop through your queue and print each Node's data
		Node<E> c = stackHead.pop();
		while(c != null){
			System.out.println(c.getData() + "");
			c = c.getNext();
		}
		
	}


}