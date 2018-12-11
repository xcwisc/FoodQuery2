package application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Implementation of a B+ tree to allow efficient access to
 * many different indexes of a large data set. 
 * BPTree objects are created for each type of index
 * needed by the program.  BPTrees provide an efficient
 * range search as compared to other types of data structures
 * due to the ability to perform log_m N lookups and
 * linear in-order traversals of the data items.
 * 
 * @author sapan (sapan@cs.wisc.edu)
 *
 * @param <K> key - expect a string that is the type of id for each item
 * @param <V> value - expect a user-defined type that stores all data for a food item
 */
public class BPTree<K extends Comparable<K>, V> implements BPTreeADT<K, V> {

	// Root of the tree
	private Node root;

	// Branching factor is the number of children nodes 
	// for internal nodes of the tree
	private int branchingFactor;


	/**
	 * Public constructor
	 * 
	 * @param branchingFactor 
	 */
	public BPTree(int branchingFactor) {
		if (branchingFactor <= 2) {
			throw new IllegalArgumentException(
					"Illegal branching factor: " + branchingFactor);
		}
		this.root = new LeafNode();
		this.branchingFactor = 3; // Hard coded because this code has bugs for > 3
	}

	/**
	 * Inserts a key/value pair into a B-plus tree
	 * @param key
	 * @param value
	 */
	@Override
	public void insert(K key, V value) {

		// Insert key value pair into tree
		root.insert(key, value);

		// If the root node is a leaf
		if (root.keys.size() > 1) {

			if (root.isOverflow()) {	

				// Instantiate new InternalNode to be the new root
				InternalNode newRoot = new InternalNode();
				newRoot.children.add(root);
				newRoot.children.add(root.split());

				// Set key to smallest key in second subtree; there can only be two
				// subtrees when we split the root
				newRoot.keys.add(newRoot.children.get(1).getFirstLeafKey());

				root = newRoot;
			}
		}
	}

	/**
	 * Performs a range search on a B-plus tree. Returns a
	 * list of all the elements of the tree that are within
	 * the specified range.
	 * @param key
	 * @param comparator
	 * @return List<V> list of values
	 */
	@Override
	public List<V> rangeSearch(K key, String comparator) {

		// Array to hold values in range
		List<V> inRangeValues = new ArrayList<V>();

		if (!comparator.contentEquals(">=") && 
				!comparator.contentEquals("==") && 
				!comparator.contentEquals("<=") )
			return inRangeValues;

		// Calls a private helper method to pass array to hold values
		return rangeSearchHelper(key, comparator, inRangeValues);
	}

	/**
	 * Private method that calls the rangeSearch function on the root node.
	 * @param key
	 * @param comparator
	 * @param inRangeValues
	 * @return List<V> list of values
	 */
	private List<V> rangeSearchHelper(K key, String comparator, List<V> inRangeValues) {
		return root.rangeSearch(key, comparator, inRangeValues);
	}

	/**
	 * Converts B-plus tree to a string representation.
	 * @return String
	 */
	@Override
	public String toString() {

		Queue<List<Node>> queue = new LinkedList<List<Node>>();
		queue.add(Arrays.asList(root));
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			Queue<List<Node>> nextQueue = new LinkedList<List<Node>>();
			while (!queue.isEmpty()) {
				List<Node> nodes = queue.remove();
				sb.append('{');
				Iterator<Node> it = nodes.iterator();
				while (it.hasNext()) {
					Node node = it.next();
					sb.append(node.toString());
					if (it.hasNext())
						sb.append(", ");
					if (node instanceof BPTree.InternalNode)
						nextQueue.add(((InternalNode) node).children);
				}
				sb.append('}');
				if (!queue.isEmpty())
					sb.append(", ");
				else {
					sb.append('\n');
				}
			}
			queue = nextQueue;
		}
		return sb.toString();
	}

	/**
	 * This abstract class represents any type of node in the tree
	 * This class is a super class of the LeafNode and InternalNode types.
	 * 
	 * @author sapan
	 */
	private abstract class Node {

		// List of keys
		List<K> keys;

		/**
		 * Package constructor
		 */
		Node() {
			keys = new ArrayList<K>();
		}

		/**
		 * Inserts key and value in the appropriate leaf node 
		 * and balances the tree if required by splitting
		 *  
		 * @param key
		 * @param value
		 */
		abstract void insert(K key, V value);

		/**
		 * Gets the first leaf key of the tree
		 * 
		 * @return key
		 */
		abstract K getFirstLeafKey();

		/**
		 * Gets the new sibling created after splitting the node
		 * 
		 * @return Node
		 */
		abstract Node split();

		/*
		 * (non-Javadoc)
		 * @see BPTree#rangeSearch(java.lang.Object, java.lang.String)
		 */
		abstract List<V> rangeSearch(K key, String comparator, List<V> inRangeValues);

		/**
		 * 
		 * @return boolean
		 */
		abstract boolean isOverflow();

		public String toString() {
			return keys.toString();
		}

	} // End of abstract class Node

	/**
	 * This class represents an internal node of the tree.
	 * This class is a concrete sub class of the abstract Node class
	 * and provides implementation of the operations
	 * required for internal (non-leaf) nodes.
	 * 
	 * @author sapan
	 */
	private class InternalNode extends Node {

		// List of children nodes
		List<Node> children;

		/**
		 * Package constructor
		 */
		InternalNode() {
			super();
			children = new ArrayList<Node>();
		}

		/**
		 * Gets the smallest key in the subtree rooted at this InternalNode
		 * @return K key
		 */
		K getFirstLeafKey() {
			return children.get(0).getFirstLeafKey();
		}

		/**
		 * Checks if the node has more children nodes than allowed
		 * @return boolean true if node has too many children
		 */
		boolean isOverflow() {
			if (children.size() > branchingFactor) {
				return true;
			}
			return false;
		}

		/**
		 * Inserts a key value pair into the tree. It recursively goes down
		 * the tree to find the correct location for the key/value at the 
		 * leaf level. Eventually a new LeafNode will be instantiated with
		 * the key/value as its data. When an insert violates the branching
		 * factor property of the tree, this method will split the 
		 * InternalNode.
		 * @param key
		 * @param value
		 */
		void insert(K key, V value) {

			// Insert down left-most branch
			if (key.compareTo(keys.get(0)) < 0) {
				children.get(0).insert(key, value);

				// Split the overflowed child into two children
				if (children.get(0).isOverflow()) {
					children.add(1, children.get(0).split());
					keys.add(children.get(keys.size()+1).getFirstLeafKey());
					keys.set(0, children.get(1).getFirstLeafKey());
				}
			}

			else {

				// Goes to true when we insert somewhere in the 
				// interior branches of the tree
				boolean insertedAlready = false;

				// Iterate through keys. When key parameter is
				// between two keys, insert key/value down that
				// respective branch
				for (int i=0; i<keys.size()-1; i++) {
					if ((key.compareTo(keys.get(i)) >= 0) &&
							(key.compareTo(keys.get(i+1)) < 0)) {
						children.get(i+1).insert(key, value);

						// Split the overflowed child into two children
						if (children.get(i+1).isOverflow()) {
							children.add(i+2, children.get(i+1).split());
						}

						insertedAlready = true;
					}
				}

				// Insert down right-most branch
				if (!insertedAlready) {
					children.get(keys.size()).insert(key, value);

					// Split the overflowed child into two children
					if (children.get(keys.size()).isOverflow()) {
						children.add(keys.size()+1, children.get(keys.size()).split());
						keys.add(children.get(keys.size()+1).getFirstLeafKey());
					}


				}
			}
		}

		/**
		 * Returns the new sibling node after an InternalNode split.
		 * @return InternalNode
		 */
		InternalNode split() {
			InternalNode sibling = new InternalNode();

			// Copy larger half of children to new sibling
			for (int i=children.size()/2; i<children.size(); i++) {
				sibling.children.add(children.get(i));
			}

			// Delete data that moved into sibling from original InternalNode
			for (int i=children.size()-1; i>(children.size())/2; i--) {
				children.remove(i);
			}

			for (int i=keys.size()-1; i>(keys.size()-1)/2; i--) {
				keys.remove(i);
			}

			// Reassign key values based on the smallest keys in each 
			// of the siblings child subtrees starting at child 1
			for (int j=1; j<sibling.children.size(); j++) {
				sibling.keys.add(sibling.children.get(j).getFirstLeafKey());
			}

			return sibling;
		}

		/**
		 * Returns a list of elements in the subtree below this InternalNode
		 * whose keys follow a comparator/key rule
		 * @param key 
		 * @param comparator a string ("<=", ">=" or "==")
		 * @return List<V>
		 */
		List<V> rangeSearch(K key, String comparator, List<V> inRangeValues) {

			// When the comparator if "<=" we want to start our traversal at
			// the left-most leaf and work our way towards key parameter. This
			// goes down the left most branch of a subtree
			if (comparator.equals("<=") || key.compareTo(keys.get(0)) < 0) {
				//				System.out.println("Enters first if");
				return children.get(0).rangeSearch(key, comparator, inRangeValues);
			}

			// When comparator not ">=", we want to find our key and then begin
			// traversal to the end. When comparator is "==" we just want to find
			// our key and then stop.
			else {
				//				System.out.println("Enters first else");

				// Iterates though keys in the InternalNode
				for (int i=0; i<keys.size()-1; i++) {
					//					System.out.println("Enters first for");

					// If the key we are looking for is between two keys in the
					// InternalNode, we want to go down that branch
					if (key.compareTo(keys.get(i)) >= 0 &&
							key.compareTo(keys.get(i+1)) < 0) {
						//						System.out.println("Enters second loop");

						return children.get(i+1).rangeSearch(key, comparator, inRangeValues);
					}
				}

				// Go down right-most branch of tree
				return children.get(children.size()-1).rangeSearch(key, comparator, inRangeValues);				
			}
		}
	} // End of class InternalNode

	/**
	 * This class represents a leaf node of the tree.
	 * This class is a concrete sub class of the abstract Node class
	 * and provides implementation of the operations that are
	 * required for leaf nodes.
	 * 
	 * @author sapan
	 */
	private class LeafNode extends Node {

		// Holds the arraylist of values for each key in the LeafNode
		List<List<V>> values; 
		LeafNode next; // Reference to the next leaf node

		/**
		 * Package constructor
		 */
		LeafNode() {
			super();
			values = new ArrayList<List<V>>();
			next = null;
		}

		/**
		 * Returns the key for this LeafNode
		 * @return K key
		 */
		K getFirstLeafKey() {
			// The key associated with this LeafNode
			return keys.get(0);
		}

		/**
		 * LeafNodes only allow a single unique key per node. They can have unlimited values.
		 * If the size of the keys list exceeds 1, we have overflow.
		 * @return boolean true if the LeafNode has more than 1 key
		 */
		boolean isOverflow() {
			if (keys.size() > 1) {
				return true;
			}
			return false;
		}


		/**
		 * If the key parameter matches the preexisting key of this LeafNode, simply
		 * adds the value to preexisting values entry. Otherwise, inserts
		 * a new key to this LeafNode's keys and a new value to values. Calls the split 
		 * method because inserting into an existing LeafNode will guarantee that the 
		 * size of keys is greater than 1. 
		 * @param key
		 * @param value
		 */
		void insert(K key, V value) {

			// Special case where we insert into tree for the first time
			if (keys.isEmpty()) {
				keys.add(key);

				// Arraylist to hold all the values that correspond
				// with this specific key
				ArrayList<V> siblingValues = new ArrayList<V>();
				siblingValues.add(value);
				values.add(siblingValues);
			}

			// Not the first insert
			else {

				// Key smaller
				if (key.compareTo(keys.get(0)) < 0) {

					// Add new key to beginning of keys
					keys.add(0, key);

					// Create a new siblingValues to hold our value and add it
					// to the end of values
					ArrayList<V> siblingValues = new ArrayList<V>();
					siblingValues.add(value);
					values.add(0, siblingValues);

					// Key larger
				} else if (key.compareTo(keys.get(0)) > 0) {

					// Add new key to the end of keys
					keys.add(key);

					// Create a new siblingValues to hold our value and add it
					// to the end of values
					ArrayList<V> siblingValues = new ArrayList<V>();
					siblingValues.add(value);
					values.add(siblingValues);

					// Key equal
				} else {

					// Add value to preexisting values entry
					values.get(0).add(value);
				}
			}
		}

		/**
		 * When a LeafNode has more than 1 key after an insert, we split the LeafNode
		 * into two LeafNodes. split() returns the created sibling LeafNode.
		 * @return LeafNode new sibling
		 */
		LeafNode split() {

			LeafNode sibling = new LeafNode();

			// We initialize the sibling's key to the second
			// index of this siblings keys array because
			// we only allow a single key
			sibling.keys.add(this.keys.get(1));

			// Add the entire list of values that is in the
			// second index of the values array to the 
			// new sibling.
			sibling.values.add(this.values.get(1));

			// Reassign next pointers
			sibling.next = next;
			next = sibling;

			// Delete data that went into sibling form original LeafNode
			keys.remove(1);
			values.remove(1);

			// The sibling's data will match the data from 
			// the item in the original LeafNode that has a 
			// larger key
			return sibling;
		} 

		/**
		 * Returns the list of values associated with this LeafNode
		 * @param key irrelevant for a LeafNode
		 * @param comparator irrelevant for a LeafNode
		 * @return List<V>
		 */
		List<V> rangeSearch(K key, String comparator, List<V> inRangeValues) {

			// Case 1: need to find only the single list of 
			// values whose key matches key parameter
			if (comparator.equals("==")) {
				if (key.equals(keys.get(0))) {
					inRangeValues.addAll(values.get(0));
				}
			}

			else {

				// Case 2: need to find all values with associated keys 
				// less than the key parameter
				if (comparator.equals("<=")) {
					if (keys.get(0).compareTo(key) <= 0) {
						inRangeValues.addAll(values.get(0));

						// Stop at end of linked list
						if (next != null) {
							next.rangeSearch(key, comparator, inRangeValues);
						}
					}
				}

				// Case 3: need to find all values with associated keys
				// greater than the key parameter
				else {
					if (keys.get(0).compareTo(key) >= 0) {
						inRangeValues.addAll(values.get(0));
					}	

					// Stop at end of linked list
					if (next != null) {
						next.rangeSearch(key, comparator, inRangeValues);
					}
				}
			}	
			return inRangeValues;
		}

	} // End of class LeafNode

} // End of class BPTree
