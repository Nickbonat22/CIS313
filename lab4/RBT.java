public class RBT<E extends Comparable<E>> {
    private Node<E> root;
    private Node<E> nullNode;

    public RBT(){
        nullNode = new Node<E>(null);
        // all null nodes are black
        nullNode.setColor('b');
        // root is initially null and black
        root = nullNode;
    }

    public Node<E> getRoot(){
        return root;
    }

    public void insert(E data){
        // Preform a regular insert
        // Check to make sure the tree remains an RBT tree

        // psuedeocode page 315
        Node<E> x = root;
        Node<E> y = nullNode;
        Node<E> z = new Node<E>(data);

        while(x != nullNode){
            y = x;
            if (z.getData().compareTo(x.getData()) < 0){
                x = x.getLeftChild();
            }
            else{
                x = x.getRightChild();
            }
        }
        z.setParent(y);
        if(y == nullNode){
            root = z;
        }
        else{
            if(z.getData().compareTo(y.getData()) < 0){
                y.setLeftChild(z);
            }
            else {
                y.setRightChild(z);
            }
        }

        // set the new node's children to be null nodes and make the color red
        z.setLeftChild(nullNode);
        z.setRightChild(nullNode);
        z.setColor('r');

        // fix rbt if neccessary
        insertFixup(z);
    }

    private void insertFixup(Node<E> z){
        // psuedeocode page 316
        while(z.getParent().getColor() == 'r'){
            if(z.getParent() == z.getParent().getParent().getLeftChild()){
                Node<E> y = z.getParent().getParent().getRightChild();

                // case 1
                if(y.getColor() == 'r'){
                    z.getParent().setColor('b');
                    y.setColor('b');
                    z.getParent().getParent().setColor('r');
                    z = z.getParent().getParent();
                }
                else{
                    // case 2
                    if(z == z.getParent().getRightChild()){
                        z = z.getParent();
                        leftRotate(z);
                    }
                    // case 3
                    z.getParent().setColor('b');
                    z.getParent().getParent().setColor('r');
                    rightRotate(z.getParent().getParent());
                }
            }

            // do the same thing but with right and left exchanged
            else{
                Node<E> y = z.getParent().getParent().getLeftChild();
                if(y.getColor() == 'r'){
                    z.getParent().setColor('b');
                    y.setColor('b');
                    z.getParent().getParent().setColor('r');
                    z = z.getParent().getParent();
                }
                else{
                    if(z == z.getParent().getLeftChild()){
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().setColor('b');
                    z.getParent().getParent().setColor('r');
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        root.setColor('b');
    }

    public Node<E> search(E data){
        // Return the node that corresponds with the given data
        // Note: No need to worry about duplicate values added to the tree

        // start at the root
        Node<E> current = root;

        while(current != nullNode){

            // return the node if it is the one we are searching for
            if(current.getData().compareTo(data) == 0){
                return current;
            }

            // go down left side
            else if(current.getData().compareTo(data) > 0){
                current = current.getLeftChild();
            }

            // go down right side
            else if(current.getData().compareTo(data) < 0){
                current = current.getRightChild();
            }
        }

        // node was not found
        return null;
    }

    public void delete(E data){
        // Preform a regular delete
        // Check to make sure the tree remains an RBT tree

        // psudeocode page 324
        // find the node we want to delete
        Node<E> z = search(data);
        Node<E> y = z;
        Node<E> x = nullNode;
        Character yC = y.getColor();

        if(z.getLeftChild() == nullNode){
            x = z.getRightChild();
            transplant(z, z.getLeftChild());
        }
        else if(z.getRightChild() == nullNode){
            x = z.getLeftChild();
            transplant(z, z.getLeftChild());
        }
        else{
            y = minimum(z.getRightChild());
            yC = y.getColor();
            x = y.getRightChild();
            if(y.getParent() == z){
                x.setParent(y);
            }
            else{
                transplant(y, y.getRightChild());
                y.setRightChild(z.getRightChild());
                y.getRightChild().setParent(y);
            }
            transplant(z,y);
            y.setLeftChild(z.getLeftChild());
            y.getLeftChild().setParent(y);
            y.setColor(z.getColor());
        }
        if(yC == 'b'){
            deleteFixup(x);
        }
    }

    private void transplant(Node<E> u, Node<E> v){
        // psudeocode page 323
        if(u.getParent() == nullNode){
            root = v;
        }
        else if(u == u.getParent().getLeftChild()){
            u.getParent().setLeftChild(v);
        }
        else{
            u.getParent().setRightChild(v);
        }
        v.setParent(u.getParent());
    }

    private Node<E> minimum(Node<E> z){
        // leftmost node will be the smallest
        if(z.getLeftChild() != nullNode){
            return minimum(z.getLeftChild());
        }
        else{
            return z;
        }
    }

    public void traverse(String order, Node<E> top) {
        // Preform a preorder traversal of the tree
        if (top != null) {
            if (top.getData() != null) {
                System.out.print(top.getData().toString() + " ");
                traverse(order, top.getLeftChild());
                traverse(order, top.getRightChild());
            }
        }
    }

    private void deleteFixup(Node<E> x){
        // psudeocode page 326
        while(x != root && x.getColor() == 'b'){
            if(x == x.getParent().getLeftChild()){
                Node<E> w = x.getParent().getRightChild();

                // case 1
                if(w.getColor() == 'r'){
                    w.setColor('b');
                    x.getParent().setColor('r');
                    leftRotate(x.getParent());
                    w = x.getParent().getRightChild();
                }

                // case 2
                if(w.getLeftChild().getColor() == 'b' && w.getRightChild().getColor() == 'b'){
                    w.setColor('r');
                    x = x.getParent();
                }
                else{

                    // case 3
                    if(w.getRightChild().getColor() == 'b'){
                        w.getLeftChild().setColor('b');
                        w.setColor('r');
                        rightRotate(w);
                        w = x.getParent().getRightChild();

                    }

                    // case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor('b');
                    w.getRightChild().setColor('b');
                    leftRotate(x.getParent());
                    x = root;
                }
            }

            // same as above with right and left switched
            else {
                Node<E> w = x.getParent().getLeftChild();

                // case 1
                if(w.getColor() == 'r'){
                    w.setColor('b');
                    x.getParent().setColor('r');
                    rightRotate(x.getParent());
                    w = x.getParent().getLeftChild();
                }

                // case 2
                if(w.getRightChild().getColor() == 'b' && (w.getLeftChild().getColor() == 'b')){
                    w.setColor('r');
                    x = x.getParent();
                }
                else{

                    // case 3
                    if(w.getLeftChild().getColor() == 'b'){
                        w.getRightChild().setColor('b');
                        w.setColor('r');
                        leftRotate(w);
                        w = x.getParent().getLeftChild();
                    }

                    // case 4
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor('b');
                    w.getLeftChild().setColor('b');
                    rightRotate(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor('b');
    }

    public void rightRotate(Node<E> y){
        
        /*
        If y is the root of the tree to rotate with right child subtree T3 and left child x, 
        where T1 and T2 are the left and right children of x:
            y becomes right child of x and T1 as its left child of x
            T2 becomes left child of y and T3 becomes right child of y
        */

        Node<E> x = y.getLeftChild();
        y.setLeftChild(x.getRightChild());
        x.getRightChild().setParent(y);
        x.setParent(y.getParent());

        // case to set the root to y
        if(y.getParent() == nullNode){
            root = x;
        }
        else if(y == y.getParent().getLeftChild()){
            y.getParent().setLeftChild(x);
        }
        else{
            y.getParent().setRightChild(x);
        }

        // put x on y's left
        x.setRightChild(y);
        y.setParent(x);
    }

    public void leftRotate(Node<E> x){
    
        /*
        If x is the root of the tree to rotate with left child subtree T1 and right child y, 
        where T2 and T3 are the left and right children of y:
            x becomes left child of y and T3 as its right child of y
            T1 becomes left child of x and T2 becomes right child of x
        */
        // psuedocode page 313
        Node<E> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        y.getLeftChild().setParent(x);
        y.setParent(x.getParent());

        // case to set the root to y
        if(x.getParent() == nullNode){
            root = y;
        }
        else if(x == x.getParent().getLeftChild()){
            x.getParent().setLeftChild(y);
        }
        else{
            x.getParent().setRightChild(y);
        }

        // put x on y's left
        y.setLeftChild(x);
        x.setParent(y);
    }

    public boolean isRBT(Node<E> wt){
        /*
        from the pdf:
        A BST is a Red-Black tree if it satisfies the following Red-Black Properties:
        1. Every node is either red or black
        2. Every leaf node counts as black
        3. If a node is red, then both its children are black
        4. Every simple path from a node to a descendant leaf contains the same number 
        of black nodes 
        5. The root node is always black
        */
        // 1
        if(allRedBlack(wt) == false) {
            return false;
        }

        // 2 and 3
        if(allBlackChildren(wt) == false){
            return false;
        }

        // 4
        if(isBalanced(wt)){
            return false;
        }

        //5
        if(wt.getColor() != 'b'){
            return false;
        }

        return true;
    }
    // credit: https://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
    private boolean isBalanced(Node<E> r){
        int max = 0;
        int min = 0;

        return isBalancedDepth(r, max, min);
    }

    // credit: https://www.geeksforgeeks.org/check-given-binary-tree-follows-height-property-red-black-tree/
    private boolean isBalancedDepth(Node<E> root, int max, int min){
        if(root == nullNode){
            max = 0;
            min = 0;
            return true;
        }

        int leftMax = 0;
        int leftMin = 0;
        int rightMax = 0;
        int rightMin = 0;

        if(isBalancedDepth(root.getLeftChild(), leftMax, leftMin) == false){
            return false;
        }

        if(isBalancedDepth(root.getRightChild(), rightMax, rightMin) == false){
            return false;
        }

        max = Math.max(leftMax, rightMax) + 1;
        min = Math.min(leftMin, rightMin) + 1;

        if(max <= 2 * min){
            return true;
        }

        return false;
    }

    private boolean allRedBlack(Node<E> root){
        // traverse through the whole tree and check if each node is
        // red or black
        if(root != nullNode){
            if((root.getColor() != 'r') || (root.getColor() != 'b')){
                return false;
            }
            return(allRedBlack(root.getLeftChild()) && allRedBlack(root.getRightChild()));
        }
        return true;
    }

    private boolean allBlackChildren(Node<E> root){
        // traverse through the whole tree and check if each red node has
        // black children
        if(root != nullNode){
            if(root.getColor() == 'r'){
                if((root.getLeftChild().getColor() != 'b') || (root.getRightChild().getColor() != 'b')) {
                        return false;
                }
                return true;
            }
            return(allBlackChildren(root.getLeftChild()) && allBlackChildren(root.getRightChild()));
        }
        return true;
    }

    
    // HINT: You may want to create extra methods such as fixDelete or fixInsert
}
