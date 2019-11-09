
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
	
	// include accessors and mutators for
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
	
	public long getCodeLength(){
		return codeLength;
	}
	
	public Node getParent(){
		return parent;
	}
	
	
	//mutators
	public void setChild(Node c){
		child = c;
	}


	public void setLetter(char c){
		letter = c;
	}
	
	public void setSibling(Node s){
		sibling = s;
	}
	
	public void setCodeLength(long c){
		codeLength = c;
	}
	
	public void setParent(Node p){
		parent = p;
	}
}
