///////////////////////////////////////////////////////////////////////////////
//////////////////////// De La Briandais Trie Class ///////////////////////////
///////////////////////////////////////////////////////////////////////////////

class DeLaBriandais {

  // Terminate character...
	public char terminate = '+';
	Node root;

	/*
	 * Constructor for De La Briandais Trie
	 */

	public DeLaBriandais() {
		root = new Node();
	}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

  /*
   * The add function takes a String input and adds it to the De La Briandais Trie
   */

	public boolean add(String word) {

    // Used to validate a successful add to the trie...
    boolean check = false;

    // Adds the terminator character to the end of the input String...
		word += terminate;

		Node current = root;

    // Adds each letter of the passed string to a node...
		for (int i = 0; i < word.length(); i++) {

			char letter = word.charAt(i);
			Add_Node set = set_child(current, letter);
			current = set.node;
			check = set.add_check;
		}

		return check;
	}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

  /*
   * The search function looks through the De La Briandais Trie for a
   * specified word
   */

	public int search(String word) {

		Node current = root;

		for (int i = 0; i < word.length(); i++) {

			char letter = word.charAt(i);
			current = get_child(current, letter);

			if (current == null) {
        // If the current node is null returns 0...
				return 0;
			}
		}

		Node terminator = get_child(current, terminate);

		if (terminator == null) {
      // If the terminator is null then there is a prefix found...
			return 1;
		}

      // If the sibling of the terminator is null the there is a word found...
		else if (terminator.sibling == null) {
			return 2;
		}

      // If there is a sibling to the terminator node then there is both a word and a prefix found...
		else {
			return 3;
		}
	}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

	/*
   * The set_sibling function adds a letter unless the node already exists
	 */

	public Add_Node set_sibling(Node sibling, char letter) {

		if (sibling == null) {

			sibling = new Node(letter);
			return new Add_Node(sibling, true);
		}

		else {

			Node next = sibling;

      // Loop until letter is encountered or not present...
			while (next.sibling != null) {

				if (next.letter == letter) {
          break;
        }
				next = next.sibling;
			}

			if (next.letter == letter) {
        // New sibling does not need to be added...
				return new Add_Node(next, false);
			}

			else {
        // Create new sibling with the assigned letter...
				next.sibling = new Node(letter);
				return new Add_Node(next.sibling, true);
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

  /*
   * The get_sibling function returns the sibling of the selected node that
   * contains the character that is specified
   */

	public Node get_sibling(Node sibling, char letter) {

		Node next = sibling;

		while (next != null) {
			if (next.letter == letter) {
        break;
      }
			next = next.sibling;
		}

		return next;
	}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

	/*
   * The set_child function assigns a parent node and letter relative to the current node
	 */

	public Add_Node set_child(Node parent, char letter) {

		if (parent.child == null) {

      // If node is not associated with a child, assign a child to it...
			parent.child = new Node(letter);

			return new Add_Node (parent.child, true);
		}

		else {

      // Otherwise set the child's sibling...
			return set_sibling(parent.child, letter);
		}
	}

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

  /*
   * The get_child function returns the child of the selected node that
   * contains the character that is specified
   */

	public Node get_child(Node parent, char letter) {

		return get_sibling(parent.child, letter);
	}

///////////////////////////////////////////////////////////////////////////////
//////////////////////////////// Node Class ///////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

	class Node {

		Node sibling;
		Node child;
		char letter;

	  /*
	   * Constructor for the Node class
	   */

		public Node() {}

	  /*
	   * Constructor for the Node class
	   */

		public Node(char letter) {
			this(letter, null, null);
		}

	  /*
	   * Constructor for the Node class
	   */

		public Node(char letter, Node sibling, Node child) {
			this.letter = letter;
			this.sibling = sibling;
			this.child = child;
		}
	}

///////////////////////////////////////////////////////////////////////////////
////////////////////////////// Add_Node Class /////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

	class Add_Node {

		Node node;
		boolean add_check;

	  /*
	   * Constructor for the Add_Node class
	   */

		public Add_Node(Node node, boolean add_check) {
			this.node = node;
			this.add_check = add_check;
		}
	}
}
