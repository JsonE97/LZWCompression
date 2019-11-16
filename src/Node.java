
public class Node {
	
	private char letter; // label on incoming branch
	private Node sibling; // next sibling (when it exists)
	private Node child; // first child (when it exists)
	private long codeLength;
	private Node parent;
	
	/** create a new node with letter c */
	public Node(char c){
		letter = c;
		sibling = null;
		child = null;
		parent = null;
		codeLength = 0;
	}
	
	// include required accessors and mutators for
	// the various components of the class
	
	//accessors
	public Node getChild(){
		return child;
	}
	
	public Node getSibling(){
		return sibling;
	}
	
	public char getLetter(){
		return letter;
	}
	
	//mutators
	public void setChild(Node c){
		child = c;
	}

	public void setSibling(Node s){
		sibling = s;
	}

}
