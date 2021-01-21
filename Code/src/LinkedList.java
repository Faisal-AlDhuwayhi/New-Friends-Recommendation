
class Node<T> {
	public T data;
	public Node<T> next;
	
	public Node() {
		data = null;
		next = null;
	}
	
	public Node(T val) {
		data = val;
		next = null;
	}

}

public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;
	private int size;

	public LinkedList () {
		head = current = null;
	}

	public boolean empty () {
		return head == null;
	}

	public boolean last () {
		return current.next == null;
	}

	public boolean full () {
		return false;
	}
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	public T retrieve () {
		return current.data;
	}
	public void update (T val) {
		current.data = val;
	}
	
	public void insert (T val) {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (val);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (val);
			current = current.next;
			current.next = tmp;
		}
		size++;
	}
	
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
		size--;
	}
	
	// Return the number of elements in the list.
	public int size() {
		return size;
	}

	// Searches for e in the list. Current must not change.
	public boolean exists(T e) {
		Node<T> c = current;
		boolean flag = false;
		findFirst();
		for(int i=0;i < size;i++) {
			if(current.data.equals(e)) {
				flag = true;
				break;
			}
			findNext();
		}
		current = c;
		return flag;
	}
	
	
}
