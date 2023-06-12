import java.util.ArrayList;
import java.util.Iterator;

/**
 * The MyHashSet API is similar to the Java Set interface. This collection is
 * backed by a hash table.
 */

/*
 * MyHashSet class implements the Set interface, backed by a hashTable
 * represented by an ArrayList
 */

public class MyHashSet<E> implements Iterable<E> {

	/**
	 * Unless otherwise specified, the table will start as an array (ArrayList) of
	 * this size.
	 */
	private final static int DEFAULT_INITIAL_CAPACITY = 4;

	/**
	 * When the ratio of size/capacity exceeds this value, the table will be
	 * expanded.
	 */
	private final static double MAX_LOAD_FACTOR = 0.75;

	public ArrayList<Node<E>> hashTable;

	private int size; // number of elements in the table

	/*
	 * A private static nested class, with features like "data", an element in the
	 * list, and "next", a reference to the successor node
	 */
	public static class Node<T> {
		private T data;
		public Node<T> next;

		// A constructor initializing the data and setting the next reference to null
		private Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/**
	 * This method initializes an empty table with the specified capacity.
	 *
	 * @param initialCapacity initial capacity (length) of the underlying table
	 */

	public MyHashSet(int initialCapacity) {

		hashTable = new ArrayList<>();
		for (int i = 0; i < initialCapacity; i++) {
			hashTable.add(null);
		}
	}

	/**
	 * This method initializes an empty table of length equal to
	 * DEFAULT_INITIAL_CAPACITY
	 */

	public MyHashSet() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * This method returns the number of elements stored in the table.
	 * 
	 * @return number of elements in the table
	 */
	public int size() {
		return size;
	}

	/**
	 * This method Returns the length of the table (the number of buckets).
	 * 
	 * @return length of the table (capacity)
	 */
	public int getCapacity() {
		return hashTable.size();
	}

	/*
	 * This method re-calculates the hash code of already stored entries by moving
	 * them to a bigger size hash map when the number of elements in the map reaches
	 * the maximum threshold value.
	 */

	private void reHash() {
		// creates a new table with twice the size of the original and assigns to null
		ArrayList<Node<E>> newHashTable = new ArrayList<>();
		for (int i = 0; i < hashTable.size() * 2; i++) {
			newHashTable.add(null);

		}

		for (int i = 0; i < hashTable.size(); i++) {
			if (hashTable.get(i) != null) {
				// current refers to a non null value element at the old table
				Node<E> current = hashTable.get(i);

				/*
				 * This loop traverses through the hash table and checks to see which index of
				 * the table has a a non-null value.
				 */
				while (current != null) {

					int newCapacity = newHashTable.size();
					// re-calculated indexes of the current element by the new table size
					int indexes = Math.abs((current.data.hashCode()) % newCapacity);

					// a node containing the data of the current element(old table)
					Node<E> element = new Node<>(current.data);

					// if a certain bucket is null(empty), we set the "element" node at its proper
					// index in the new table

					if (newHashTable.get(indexes) == null) {
						newHashTable.set(indexes, element);
						/*
						 * else if there already exists elements in that position of the new table, it
						 * traverses through the linked list connected to that bucket until the the tail
						 * element is found. After the tail in the linked list is found, it adds the
						 * elements from the old table to the new one
						 */
					} else {

						Node<E> target = newHashTable.get(indexes);
						while (target.next != null) {
							target = target.next;
						}
						if (target.next == null) {
							target.next = element;
						}
					}
					current = current.next;
				}
			}
		}
		hashTable = newHashTable;
	}

	/**
	 * This method looks for the specified element in the table.
	 * 
	 * @param element to be found
	 * @return true if the element is in the table, false otherwise
	 */
	public boolean contains(Object element) {
		// a value containing the index of the element
		int indexes = Math.abs(element.hashCode() % getCapacity());

		// getting the node at the index of the element in the table
		Node<E> current = hashTable.get(indexes);
		// if element does not exist return false
		if (current == null) {
			return false;
		}
		// if found return true
		if (current.data.equals(element)) {
			return true;
		}
		// the loop goes up to the end and returns true when the element is found in the
		// table
		while (current != null) {
			if (current.data.equals(element)) {
				return true;
			}
			current = current.next;

		}
		return false;
	}

	/**
	 * This method adds the specified element to the collection, if it is not
	 * already present. If the element is already in the collection, then this
	 * method does nothing.
	 * 
	 * @param element the element to be added to the collection
	 */

	public void add(E eleme1nt) {
		int indexes = Math.abs(eleme1nt.hashCode() % getCapacity());

		if (contains(eleme1nt)) {
			return;
		} else {
			// creating a node containing the element to be added
			Node<E> theElement = new Node<E>(eleme1nt);

			// if the hash position in the table is not empty,
			// the new Element will be linked to the list in the
			// bucket of its proper index match
			if (hashTable.get(indexes) != null) {
				Node<E> current = hashTable.get(indexes);
				while (current.next != null) {
					current = current.next;
				}
				// The new Element will be placed at the tail.
				if (current.next == null) {
					current.next = theElement;
				}
				// if the hash position in the table is empty(null) then the new element will be
				// set at that index or position
			} else {
				hashTable.set(indexes, theElement);

			}
		}
		size++;

		// if the size to capacity ratio is reaches the maximum threshold value we
		// simply call the rehash method
		if (((double) size() / (double) getCapacity()) >= MAX_LOAD_FACTOR) {
			reHash();

		}
	}

	/**
	 * This method removes the specified element from the collection. If the element
	 * is not present then this method should do nothing (and return false in this
	 * case).
	 *
	 * @param element the element to be removed
	 * @return true if an element was removed, false if no element removed
	 */

	public boolean remove(Object element) {
		if (!contains(element)) {
			return false;
		} else {
			int indexes = Math.abs(element.hashCode() % getCapacity());
			Node<E> current = hashTable.get(indexes);
			// if the intended element to be removed is found, remove that element
			// by setting the table to the next value
			if (current.data.equals(element)) {
				hashTable.set(indexes, current.next);
				size--;// decrementing size
				return true;
			}
			Node<E> previous = null;

			while (current != null) {
				// if the current node's data equals the data we want to remove, the
				// previous.next is made the current.next data, which removes the current
				// element.
				if (current.data.equals(element)) {
					previous.next = current.next;
					size--;
					return true;
					// else keep checking the next element to find the one to remove
				} else {
					previous = current;
					current = current.next;
				}
			}
		}
		return false;
	}

	/**
	 * Returns an Iterator that can be used to iterate over all of the elements in
	 * the collection.
	 * 
	 * The order of the elements is unspecified.
	 */

	@Override
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			int marker = 0;
			int index = 0;
			Node<E> current = hashTable.get(index); // Represents the current node for the bucket

			@Override
			// This method returns true of the marker is less than the size
			public boolean hasNext() {
				return marker < size();
			}

			@Override
			// This method returns the data of the current element at each index or position
			public E next() {
				while (current == null) {
					index++;
					current = hashTable.get(index);
				}
				E data = current.data;
				current = current.next;
				marker++;
				return data;
			}

		};
	}

}
