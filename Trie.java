
public class Trie {
	
	// create root of the trie
	private Node root; 
	
	public Trie() {
		// null character in the root
		root = new Node(Character.MIN_VALUE); 
	}        
	
	
	public Node getRoot(){
		return this.root;
	}
	
	// possible outcomes of a search
	private enum Outcomes {PRESENT, ABSENT, UNKNOWN}
	
	/** search trie for word w */
	public boolean search(String w) {
		
		// initially outcome unknown
		Outcomes outcome = Outcomes.UNKNOWN;
		
		// position in word so far searched (start at beginning)
		int i = 0;

		// start with first child of the root
		Node current = root.getChild();
		
		// start search
		while (outcome == Outcomes.UNKNOWN) {
			
			if (current == null) outcome = Outcomes.ABSENT; // dead-end
			else if (current.getLetter() == w.charAt(i)) { // positions match				
				if (i == w.length() - 1) {
					outcome = Outcomes.PRESENT; // matched word
				}
				else { // descend one level...
					current = current.getChild(); // in trie
					i++; // in word being searched
				}
			}	
			else { // positions not matched so try next sibling
				current = current.getSibling();
			}
		}
		// return answer
		if (outcome != Outcomes.PRESENT) return false;
		else return	true;
	}
		
	/* now changed so insertion is performed in a lexicographical order (task 3) */
		
	/** insert word w into trie */
	public void insert(String w){
			
		int i = 0; // position in word (start at beginning)
		Node current = root; // current node in trie (start at root)
		Node next = current.getChild(); // first child of current node we are testing
			
		while (i < w.length()) { // not reached end of word
			if (next !=null && next.getLetter() == w.charAt(i)) { // chars match: decend a level
				current = next; // update node to the child node
				next = current.getChild(); // update child node
				i++; // next position in the word
			} 
			else if (next != null) { // try next sibling
				next = next.getSibling();
			}
			else { // no more siblings: need new node
			
				// could have kept track of the siblings when searching
				// to avoid going through the siblings twice
				
				Node x = new Node(w.charAt(i)); // label with ith element of the word
				
				
				// search for the correct (lexicographical) insertion position
				// which will end up being between nodes z and y
				
				Node z = null; // initially z is empty (i.e. start thinking we insert as first child)
				Node y = current.getChild(); // first child
				
				// find correct place to insert
				while (y != null && y.getLetter() < w.charAt(i)){ 
					// while dictionary position is after y
					z = y; // z becomes y (we insert z after y)
					y = y.getSibling(); // y becomes next sibling
				}
				// found position to insert
				if (z == null) { // special case insert as first child
					current.setChild(x); // x becomes first child of current node
					x.setSibling(y); // y is the sibling of x
				} else { // general case: insert x between y and z
					z.setSibling(x); // x is the sibling of z
					x.setSibling(y); // y is the sibling of x
				}
				// now continue as before
				current = x; // move to the new node
				next = current.getChild(); // update child node
				i++; // next position in word
			}
		}
		// current represents word w
	}

	
}
