package searchTree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K, V> left, right;
	private K key;
	private V value;

	/*
	 * A constructor to initialize the instance variables needed
	 */
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/*
	 * This method finds the value that the key in the parameter is bound to in this
	 * tree and returns the value associated with the key by this Tree, or null if
	 * the key does not have an association in this tree.
	 * 
	 */
	public V search(K key) {
		// calculates the difference between the current and the key to be searched for
		int comparasion = key.compareTo(this.key);
		// if the key is found return the value associated with it
		if (comparasion == 0) {
			return this.value;
			// if comparison is less than 0 then look in the left and return when found
		} else if (comparasion < 0) {
			return left.search(key);
			// else look in the right
		} else {
			return right.search(key);
		}

	}
	/*
	 * This method updates the Tree with a new key:value pair. If the key already
	 * exists in the tree, update the value associated with it. If the key doesn't
	 * exist, insert the new key value pair. This method returns a reference to an
	 * Tree that represents the updated value. In many, but not all cases, the
	 * method may just return a reference to this.
	 */

	public NonEmptyTree<K, V> insert(K key, V value) {

		int comparasion = key.compareTo(this.key);
		// if the key already exists update the value
		if (comparasion == 0) {
			this.value = value;
			// if comparison is less than 0, keep looking in the left until found
			// if not found insert the key and value pair
		}
		if (comparasion < 0) {
			this.left = this.left.insert(key, value);
			// else look in the right and insert the key value pair
		}
		if (comparasion > 0) {
			this.right = this.right.insert(key, value);
		}

		return this;

	}

	/*
	 * This method deletes any binding the key has in this tree. If the key isn't
	 * bound, this is a no-op This method returns a reference to an Tree that
	 * represents the updated value. In many, but not all cases, the method may just
	 * return a reference to this.
	 * 
	 */
	public Tree<K, V> delete(K key) {

		int comparasion = key.compareTo(this.key);
		// if the key to be removed is found
		if (comparasion == 0) {
			try {
				// copy data(key and value) from last element to the top and remove it.
				this.key = left.max();
				this.value = this.left.search(this.left.max());
				this.left = left.delete(this.left.max());

			} catch (TreeIsEmptyException e) {

				try {
					// the root is the most minimum from the right side so delete it when the
					// element is identified
					this.key = right.min();
					this.value = this.right.search(this.right.min());
					this.right = right.delete(this.right.min());
				} catch (TreeIsEmptyException ee) {
					return EmptyTree.getInstance();
				}
			}
		}
		// if comparison is negative look in the left
		if (comparasion < 0) {
			this.left = this.left.delete(key);
		}
		// if comparison is positive look in the right and update the key and value as
		// necessary
		if (comparasion > 0) {
			this.right = this.right.delete(key);
		}
		return this;
	}

	/*
	 * This method return the maximum key in the subtree
	 */
	public K max() {
		// throws TreeIsEmptyException if the tree is empty
		try {
			// returns the right most key since its the biggest term
			return right.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}

	}

	/*
	 * This method return the minimum key in the subtree
	 */

	public K min() {

		try {
			return left.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}

	}

	/*
	 * This method return number of keys that are bound in this tree.
	 */
	public int size() {
		// root plus the left and right size
		return 1 + left.size() + right.size();
	}

	/*
	 * This method adds all keys bound in this tree to the collection c. The
	 * elements must be added in their sorted order.
	 */
	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(key);
		right.addKeysToCollection(c);

	}
	/*
	 * This method returns a Tree containing all entries between fromKey and toKey,
	 * inclusive.
	 */

	public Tree<K, V> subTree(K fromKey, K toKey) {
		// compares from fromKey to the current key
		int comparison1 = fromKey.compareTo(key);
		// compares from toKey to the current key
		int comparison2 = toKey.compareTo(key);
		// if fromKey is less than the current key we are on look in the right side
		// until the key is found
		// then return a sub tree from that key to the key given
		if (comparison1 > 0) {
			return this.right.subTree(fromKey, toKey);
		}

		// if comparison 2 is a negative number look in the left subtree and return it
		// when found
		if (comparison2 < 0) {
			return this.left.subTree(fromKey, toKey);
			// else return the tree(sub) by the given bounds
		} else {
			NonEmptyTree<K, V> subTree = new NonEmptyTree<K, V>(this.key, this.value, left.subTree(fromKey, toKey),
					this.right.subTree(fromKey, toKey));
			return subTree;
		}
	}
}