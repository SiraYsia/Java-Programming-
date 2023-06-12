package searchTree;

import java.util.Collection;

/**
 * This class is used to represent the empty search tree: a search tree that
 * contains no entries.
 * 
 * This class is a singleton class: since all empty search trees are the same,
 * there is no need for multiple instances of this class. Instead, a single
 * instance of the class is created and made available through the static field
 * SINGLETON.
 * 
 * The constructor is private, preventing other code from mistakenly creating
 * additional instances of the class.
 * 
 */
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	/**
	 * This static field references the one and only instance of this class. We
	 * won't declare generic types for this one, so the same singleton can be used
	 * for any kind of EmptyTree.
	 */
	private static EmptyTree SINGLETON = new EmptyTree();

	public static <K extends Comparable<K>, V> EmptyTree<K, V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 * 
	 */
	private EmptyTree() {
		// Nothing to do
	}

	/*
	 * This method finds the value that this key is bound to in this tree.
	 */
	public V search(K key) {
		return null;
	}

	/*
	 * This method inserts the Tree with a new key:value pair.
	 */
	public NonEmptyTree<K, V> insert(K key, V value) {
		// returns a new non empty tree with the key and value inserted
		return new NonEmptyTree<K, V>(key, value, SINGLETON, SINGLETON);
	}

	/*
	 * This method deletes any binding the key has in this tree. If the key isn't
	 * bound, this is a no-op This method returns a reference to an Tree that
	 * represents the updated value.
	 */
	public Tree<K, V> delete(K key) {
		return SINGLETON;
	}

	/*
	 * This method returns the maximum key in the subtree
	 * 
	 */
	public K max() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	/*
	 * This method return the minimum key in the subtree
	 * 
	 */
	public K min() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	/*
	 * This method return number of keys that are bound in this tree.
	 */
	public int size() {
		return 0;
	}

	/*
	 * This method adds all keys bound in this tree to the collection c.
	 */
	public void addKeysToCollection(Collection<K> c) {
		return;
	}

	/*
	 * This method returns a Tree containing all entries between fromKey and toKey,
	 * inclusive
	 */
	public Tree<K, V> subTree(K fromKey, K toKey) {
		return SINGLETON;
	}
}