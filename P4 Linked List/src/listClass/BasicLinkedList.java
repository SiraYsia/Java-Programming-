package listClass;

import java.util.Iterator;

/*
 * This class represents an implementation of a collection with a parameterized type 
 * and linked list methods
 * 
 */
public class BasicLinkedList<T> implements Iterable<T> {
	/*
	 * A private static nested class, with features like "data", an element in the
	 * list, and "next", a reference to the successor node
	 */
	private static class Node<T> {
		private T data;
		private Node<T> next;

		// A constructor initializing the data and setting the next reference to null
		private Node(T data) {
			this.data = data;
			next = null;
		}

	}

	/*
	 * head and tail references for the nodes and a size to keep track of the
	 * current size of the list.
	 */
	private Node<T> head;
	private Node<T> tail;
	private int size;

	// A constructor initializing the list as empty.
	public BasicLinkedList() {
		head = null;
		tail = null;
		size = 0;

	}

	/*
	 * This method returns the size of a node after adding or removing from the
	 * linked list
	 */
	public int getSize() {
		return size;

	}

	/*
	 * This method adds the data element to the tail of the list. It holds a data to
	 * be added to the linked list as a parameter and returns a reference to the
	 * current object
	 */
	public BasicLinkedList<T> addToEnd(T data) {

		// A node holding a reference to the data to be inserted
		Node<T> node = new Node<>(data);
		if (tail == null) {
			tail = node;
			tail.next = null;
			// if the list is empty then both the head and tail will reference to "node"
			if (head == null) {
				head = node;
				tail = head;
			} else {
				head.next = tail;
			}
		} else {
			tail.next = node;
			tail = node;
			tail.next = null;
		}
		size++;// incrementing the size of the linked list by one
		return this;
	}

	/*
	 * This method adds the element to the head of the list. It holds the data to be
	 * added to the linked list as a parameter and returns a reference to the
	 * current object
	 */
	public BasicLinkedList<T> addToFront(T data) {

		// A node holding a reference to the data to be inserted
		Node<T> node = new Node<>(data);
		if (head != null) {
			node.next = head;
			head = node;
			// if the list is empty both the head and tail will reference to the new "node".
		} else {
			head = node;
			tail = node;

		}
		size++;
		return this;

	}

	/**
	 * This method returns the head element without removing it, or it returns null
	 * if the list is empty.
	 */
	public T getFirst() {
		// if not empty and there is more than one element present
		// return the data for the head element

		if (head != null || size > 0) {
			return head.data;
		} else {
			return null;
		}
	}

	/*
	 * This method returns the tail element without removing it, or null if the list
	 * is empty.
	 */
	public T getLast() {
		if (tail != null && head != null) {
			return tail.data;
		} else {
			return null;
		}
	}

	/*
	 * This method removes and returns the head element. If the list is empty, this
	 * method returns null.
	 */
	public T retrieveFirstElement() {
		// A reference to the head of the linked list
		Node<T> prev = head;
		if (head == null) {
			return null;
		}
		// if there is only one node, remove it so head and tail will be null
		if (head == tail) {
			head = null;
			tail = null;
			size--;// decrementing the size by 1
			return prev.data;// returns a reference to the head node(before removing).
		}
		// if size is more than 1 the second node will be the head
		if (head != null) {
			head = head.next;
			size--;// removes the previous head element
			return prev.data;

		}
		return null;

	}

	/*
	 * This method removes and returns the tail element. If the list is empty, this
	 * method returns null.
	 */
	public T retrieveLastElement() {

		// if empty return null
		if (head == null) {
			return null;

			// if there is only one element both the head and tail will be null and the size
			// will be 0
		} else if (head.next == null) {
			size--;
			T prevTail = head.data;
			head = null;
			tail = null;
			return prevTail;// returning the previous tail element
		} else {
			T prevTail = tail.data;
			Node<T> curr = head;
			while (curr.next != tail) {
				curr = curr.next;
			}
			tail = curr;// one node prior to the tail node is now the tail
			tail.next = null;
			size--;
			return prevTail;
		}

	}

	/*
	 * This method removes all instances of the target element from the list. The
	 * method returns a reference to the current object.
	 */
	public BasicLinkedList<T> removeAllInstances(T targetData) {
		Node<T> prev = null, curr = head;
		if (head == null) {
			return null;
		}
		// the loop goes up to the tail
		while (curr != null) {
			if (curr.data.equals(targetData)) {
				// if the target is the head remove it and check the next one.
				if (curr == head) {
					head = head.next;
					curr = head;
					/*
					 * if the target is the at the tail curr is now null and the tail will be
					 * referencing to where curr used to be before it was null.
					 */
				} else if (curr == tail) {
					curr = null;
					tail = prev;
					prev.next = null;

				} else {
					prev.next = curr.next;
					curr = curr.next;
				}
				// decrementing by one each time a target element from the list is found
				size--;
				/*
				 * up until curr is not the target, prev will be curr, and curr will keep
				 * checking the next node to see if it is the target element
				 */
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		return this;
	}

	/*
	 * This method returns an instance of an anonymous inner class that defines an
	 * Iterator over this list from head to tail.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			// A node reference to the head of the linked list
			Node<T> pointer = head;

			@Override
			// Returns true if the linked list is not empty
			public boolean hasNext() {
				return (pointer != null);

			}

			@Override
			// Returns the head of the linked list
			public T next() {
				Node<T> curr = pointer;
				pointer = pointer.next;
				return curr.data;

			}
		};
	}
}