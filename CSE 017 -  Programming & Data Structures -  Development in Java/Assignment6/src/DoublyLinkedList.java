
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
	// Data members
	public Node head, tail;
	int size;

	// Inner class Node - O(1)
	public class Node {
		E value;
		Node next;
		Node previous;
		Node(E initialValue) {
			value = initialValue;
			next = null;
			previous = null;
		}
	}

	// Constructor - O(1)
	public DoublyLinkedList() {
		head = tail = null;
		size = 0;
	}

	//Add first - O(1)
	public boolean addFirst(E item) {
		Node newNode = new Node(item);
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
		return true;
	}
	//Add last - O(1)
	public boolean addLast(E item) {
		Node newNode = new Node(item);
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}
	//Add first essentially - O(1)
	public boolean add(E item) {
		return addLast(item);
	}

	// Retrieving an item from the list - O(1)
	public E getFirst() {
		if (head == null)
			throw new NoSuchElementException();
		return head.value;
	}
	//O(1)
	public E getLast() {
		if (head == null)
			throw new NoSuchElementException();
		return tail.value;
	}

	// Removing an item from the list - O(1)
	public boolean removeFirst() {
		if (head == null)
			throw new NoSuchElementException();
		head = head.next;
		if (head == null)
			tail = null;
		size--;
		return true;
	}

	//Remove last - O(n)
	public boolean removeLast() {
		if (head == null)
			throw new NoSuchElementException();
		if (size == 1)
			return removeFirst();
		Node current = head;
		Node previous = null;
		while (current.next != null) {
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;
		size--;
		return true;
	}

	// toString() method - O(n)
	public String toString() {
		String output = "[";
		Node current = head;
		while (current != null) {
			output += current.value + " ";
			current = current.next;
		}
		output += "]";
		return output;
	}

	// clear, check if empty, and size of the list - O(1)
	public void clear() {
		head = tail = null;
		size = 0;
	}
	
	//check list - O(1)
	public boolean isEmpty() {
		return (size == 0);
	}
	
	//size - O(1)
	public int size() {
		return size;
	}

	//check index - O(n)
	private int indexOf(Node node) {
		int index = 0;
		Node current = head;
		while (current != null) {
			if (current.value.equals(node)) {
				return index;
			}
			current = current.next;
			index++;
			// System.out.println(current.value.toString());
		}
		return -1;
	}

	//Iterator calls to doubly O(1)
	public ListIterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	public ListIterator<E> iterator(int index) {
		return new DoublyLinkedListIterator(index);
	}

	//Internal private class
	private class DoublyLinkedListIterator implements ListIterator<E> {
		private Node current;

		// Constructors - O(1)
		DoublyLinkedListIterator() {
			current = head;
		}
		//O(n)
		public DoublyLinkedListIterator(int index) {
			if (index < 0 || index > size)
				throw new ArrayIndexOutOfBoundsException();
			current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			} // after the for loop, current is pointing to the element at position index
		}
		//O(1)
		public E next() {
			if (current == null)
				throw new NoSuchElementException();
			E value = current.value;
			current = current.next;
			return value;
		}
		//O(1)
		public E previous() {
            if (current == null) {
                throw new NoSuchElementException();
            }
                E value = current.value;
                current = current.previous;
                return value;
        }
		//O(1)
		public int nextIndex() {
			if (current == null)
				throw new NoSuchElementException();
			int currentIndex = indexOf(current);
			return currentIndex + 1;
		}
		//O(1)
		public int previousIndex() {
			if (current == null)
				throw new NoSuchElementException();
			int currentIndex = indexOf(current);
			return currentIndex - 1;
		}
		//O(1)
		public void set(E e) {
			if (current == null)
				throw new UnsupportedOperationException();
			Node newE = new Node(e);
			current = newE;
		}
		//O(2n)
		public void remove() {
			if (current == null)
				throw new UnsupportedOperationException();
			int indexOfCurrent = indexOf(current);
			Node current = head;
			Node previous = null;
			for (int i = 0; i < indexOfCurrent; i++) {
				previous = current;
				current = current.next;
			}
			@SuppressWarnings("unused")
			Node temp = current;
			current = current.next;
			while (current.next != null) {
				previous = current;
				current = current.next;
			}
			tail = previous;
			size--;
		}
		//O(1)
		public boolean hasPrevious() {
			return (current != null);
		}
		//O(1)
		public boolean hasNext() {
			return (current != null);
		}
		//O(1)
		public void add(E e) {
			Node newNode = new Node(e);
			if (head == null) {
				head = tail = newNode;
			} else {
				newNode.next = head;
				head = newNode;
			}
			size++;
		}
	}
}
