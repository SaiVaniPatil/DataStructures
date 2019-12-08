// Data structure to store a Binary Tree node
class Node
{
	int key;
	Node left = null, right = null;

	Node(int key) {
		this.key = key;
	}
}

class IdenticalBinaryTrees {

	// Recursive function to check if two given binary trees are identical or not
	public static boolean isIdentical(Node x, Node y)
	{
		// if both trees are empty, return true
		if (x == null && y == null) {
			return true;
		}

		// if both trees are non-empty and value of their root node matches,
		// recur for their left and right sub-tree
		return (x != null && y != null) && (x.key == y.key) &&
				       isIdentical(x.left, y.left) &&
				       isIdentical(x.right, y.right);
	}

	// main function
	public static void main(String[] args) {
		// construct first tree
		Node x = new Node(15);
		x.left = new Node(10);
		x.right = new Node(20);
		x.left.left = new Node(8);
		x.left.right = new Node(12);
		x.right.left = new Node(16);
		x.right.right = new Node(25);

		// construct second tree
		Node y = new Node(15);
		y.left = new Node(10);
		y.right = new Node(20);
		y.left.left = new Node(8);
		y.left.right = new Node(12);
		y.right.left = new Node(16);
		y.right.right = new Node(25);

		if (isIdentical(x, y)) {
			System.out.print("Given binary Trees are identical");
		} else {
			System.out.print("Given binary Trees are not identical");
		}
	}
}