public class BST<E extends Comparable<E>> {
    private Node<E> root;

    public BST(){
        root = null;
    }
    // down and slow, focus
    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Find the right spot in the tree for the new node
        // Make sure to check if anything is in the tree
        // Hint: if a node n is null, calling n.getData() will cause an error
        
    	Node<E> newNode = new Node<E>(data);
        Node<E> node = root;
        Node<E> parentNode;

        // if root is null, we make data the new root
        if(root == null){
            root = new Node<E>(data);
        }
        else if(node.getData() != data){
            // loop to check if the data is greater than or less than
            while((node.getData().compareTo(data) < 0) || (node.getData().compareTo(data) > 0)){
                parentNode = node;
                if(node.getData().compareTo(data) < 0){
                    parentNode = node;
                    node = node.getRightChild();

                    if(node == null){
                        parentNode.setRightChild(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                }
                else{
                    node = node.getLeftChild();
                    if(node == null){
                        parentNode.setLeftChild(newNode);
                        newNode.setParent(parentNode);
                        return;
                    }
                }
            }
        }
    }

    public Node<E> find(E data){
        // Search the tree for a node whose data field is equal to data

        // start at the root
        Node<E> node = root;

        while(node != null && node.getData().compareTo(data) != 0){

        	// determining if the node is a left or right chold
            if (data.compareTo(node.getData()) < 0){
                node = node.getLeftChild();
            }
            else {
                node = node.getRightChild();
            }
        }
        return node;
    }

    public void delete(E data){
        // Find the right node to be deleted

        // If said node has no children, simply remove it by setting its parent to point to null instead of it.
        // If said node has one child, delete it by making its parent point to its child.
        // If said node has two children, then replace it with its successor,
        //          and remove the successor from its previous location in the tree.
        // The successor of a node is the left-most node in the node's right subtree.
        // If the value specified by delete does not exist in the tree, then the structure is left unchanged.

        // Hint: You may want to implement a findMin() method to find the successor when there are two children

    	// find the node we want to delete
        Node<E> node = find(data);

		if(node == null){
			return;
		}

		Node<E> leftChild = node.getLeftChild();
		Node<E> rightChild = node.getRightChild();

		// case the node we want to delete is the root
		if(node == root){

			// if there are no children, this is a null tree
			if(leftChild == null && rightChild == null){
				root = null;
			}

			// case there is only a right child
			else if(leftChild == null && rightChild != null){
				root = node.getRightChild();
				node.getRightChild().setParent(root);
			}

			// case there is only a left choild
			else if(leftChild != null && rightChild == null){
				root = node.getLeftChild();
				node.getLeftChild().setParent(root);
			}

			// case there are both children present
			else{
				Node<E> successor = getMin(node);

				// case the successor is the right child
				if(successor == rightChild){
					successor.setLeftChild(leftChild);
					successor.getLeftChild().setParent(successor);
					successor.setParent(root.getParent());
					root = successor;
				}

				// case the successor is the left child
				else{
					if(successor.getParent().getLeftChild() == successor){
						successor.getParent().setLeftChild(null);
					}
					else if (successor.getParent().getRightChild() == successor){
						successor.getParent().setRightChild(null);
					}

					successor.setLeftChild(leftChild);
					root.getLeftChild().setParent(successor);
					successor.setRightChild(rightChild);
					root.getRightChild().setParent(successor);
					successor.setParent(root.getParent());
					root = successor;
				}
			}
        }

        // node we want to delete isn't the root
        else{
            Node<E> parentNode = node.getParent();

            // case there are no children
            if(leftChild == null && rightChild == null){
                if(node == parentNode.getLeftChild()){
                    parentNode.setLeftChild(null);
                }
                else{
                    parentNode.setRightChild(null);
                }
            }

            // case there is only a left child
            else if(leftChild != null && rightChild == null){
                if(node == parentNode.getLeftChild()){
                    parentNode.setLeftChild(node.getLeftChild());
                    node.getLeftChild().setParent(parentNode);
                }
                else{
                    parentNode.setRightChild(node.getLeftChild());
                    node.getLeftChild().setParent(parentNode);
                }
            }

            // case there is only a right child
            else if(leftChild == null && rightChild != null){
                if(node == parentNode.getLeftChild()){
                    parentNode.setLeftChild(node.getRightChild());
                    node.getRightChild().setParent(parentNode);
                }
                else{
                    parentNode.setRightChild(node.getRightChild());
                    node.getRightChild().setParent(parentNode);
                }
            }

            // case there are both children present
            // use getMin to find successor
            else{
                Node<E> min = getMin(node);
                if(node == parentNode.getLeftChild()){
                    parentNode.setLeftChild(min);
                }
                else{
                    parentNode.setRightChild(min);
                }
                min.setLeftChild(node.getLeftChild());
            }
        }
    }

    public void traverse(String order, Node<E> top) {
        if (top != null){
            switch (order) {
                case "preorder":
                    System.out.print(top.getData() + " ");
                    traverse(order, top.getLeftChild());
                    traverse(order, top.getRightChild());
                    break;

                case "inorder":
                    traverse(order, top.getLeftChild());
                    System.out.print(top.getData() + " ");
                    traverse(order, top.getRightChild());
                    break;

                case "postorder":
                    traverse(order, top.getLeftChild());
                    traverse(order, top.getRightChild());
                    System.out.print(top.getData() + " ");
                    break;
            }
        }
    }

    public Node<E> getMin(Node<E> top){
        // Return the min node
        Node<E> min = top.getRightChild();

        while(min.getLeftChild() != null){
            min = min.getLeftChild();
        }
        return min;
    }
}
