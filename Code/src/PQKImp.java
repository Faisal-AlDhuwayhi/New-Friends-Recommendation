
class PQNode<T, P> {
	public T data;
	public P priority;
	public PQNode<T, P> next;

	public PQNode() {
		next = null;
	}

	public PQNode(T e, P p) {
		data = e;
		priority = p;
	}

}

public class PQKImp<P extends Comparable<P>, T> implements PQK<P, T> {
	private PQNode<T, P> head;
	private int size;
	private int maxsize;

	public PQKImp(int k) {
		head = null;
		size = 0;
		maxsize = k;
	}

	// Return the length of the queue
	public int length() {
		return size;
	}

	// Enqueue a new element. The queue keeps the k elements with the highest
	// priority. In case of a tie apply FIFO.
	public void enqueue(P pr, T e) {
		PQNode<T, P> node = new PQNode<T, P>(e, pr);
		if ((size == 0) || (pr.compareTo(head.priority)) > 0) {
			node.next = head;
			head = node;
		} else {
			PQNode<T, P> p = head;
			PQNode<T, P> q = null;
			while ((p != null) && (p.priority.compareTo(pr) >= 0)) {
				q = p;
				p = p.next;
			}
			node.next = p;
			q.next = node;
		}
		size++;
		if (size > maxsize) {
			PQNode<T, P> w = head;
			while (w.next.next != null)
				w = w.next;
			w.next = null;
			size--;
		}
	}

	// Serve the element with the highest priority. In case of a tie apply FIFO.
	public Pair<P, T> serve() {
		PQNode<T, P> node = head;
		Pair<P, T> p = new Pair<P, T>(node.priority, node.data);
		head = head.next;
		size--;
		return p;
	}

}
