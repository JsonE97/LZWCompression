/**
 * Class to represent a node in a Trie
 */

public class Node {

    /**
     * Private instance attributess
     */
    private char letter;        // label on incoming branch
    private Node sibling;        // next sibling (when it exists)
    private Node child;        // first child (when it exists)

    /**
     * Initializer for Node
     *
     * @param c - char - letter for node
     */
    public Node(char c) {
        letter = c;
        sibling = null;
        child = null;
    }


    /**
     * Public getters
     */

    /**
     * @return - Node - child of a node
     */
    public Node getChild() {
        return child;
    }

    /**
     * @return - Node - sibling of a node
     */

    public Node getSibling() {
        return sibling;
    }

    /**
     * @return - char - returns the letter of the node
     */

    public char getLetter() {
        return letter;
    }

    /**
     * Public mutators
     */

    /**
     * @param c - Node - sets the child of the node
     */

    public void setChild(Node c) {
        child = c;
    }

    /**
     * @param s - Node - sibling to set for current node
     */

    public void setSibling(Node s) {
        sibling = s;
    }

}
