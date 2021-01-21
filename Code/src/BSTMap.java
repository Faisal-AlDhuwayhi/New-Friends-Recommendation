
class BSTNode<K extends Comparable<K>, T> {
	public K key;
	public T data;
	public BSTNode<K, T> left, right;

	public BSTNode(K key, T data) {
		this.key = key;
		this.data = data;
		left = right = null;
	}
}

public class BSTMap<K extends Comparable<K>, T> implements Map<K, T> {
	public BSTNode<K, T> root; 
	public int size;

	public BSTMap() {
		root = null;
		size = 0;
	}

	// Return the size of the map.
	public int size() {
		return size;
	}

	// Return true if the map is full.
	public boolean full() {
		return false;
	}

	// Remove all elements from the map.
	public void clear() {
		root = null;
		size = 0;
	}

	// Update the data of the key k if it exists and return true. If k does not
	// exist, the method returns false.
	public boolean update(K k, T e) {
		if (root == null)
			return false;
		BSTNode<K, T> p = root;
		boolean flag = false;
		while (p != null) {
			if (k.compareTo(p.key) == 0) {
				p.data = e;
				flag = true;
				break;
			} else if (k.compareTo(p.key) > 0)
				p = p.right;
			else if (k.compareTo(p.key) < 0)
				p = p.left;
		}
		return flag;
	}

	// Search for element with key k and returns a pair containing true and its data
	// if it exists. If k does not exist, the method returns false and null.
	public Pair<Boolean, T> retrieve(K k) {
		if (root == null)
			return new Pair<Boolean, T>(false, null);
		BSTNode<K, T> p = root;
		boolean flag = false;
		while (p != null) {
			if (k.compareTo(p.key) == 0) {
				flag = true;
				break;
			} else if (k.compareTo(p.key) > 0)
				p = p.right;
			else if (k.compareTo(p.key) < 0)
				p = p.left;
		}
		if (p == null)
			return new Pair<Boolean, T>(flag, null);
		return new Pair<Boolean, T>(flag, p.data);
	}

	// Insert a new element if does not exist and return true. If k already exists,
	// return false.
	public boolean insert(K k, T e) {
		BSTNode<K, T> node = new BSTNode<K, T>(k, e);
		if (root == null) {
			root = node;
			size++;
			return true;
		}
		BSTNode<K, T> p = root;
		boolean flag = false;
		boolean flag2 = false;
		while (p != null) {
			if (k.compareTo(p.key) == 0) {
				flag = true;
				break;
			} else if (k.compareTo(p.key) > 0) {
				if (p.right == null) {
					flag2 = true;
					break;
				}
				p = p.right;
			} else {
				if (p.left == null)
					break;
				p = p.left;
			}
		}
		if (flag)
			return false;
		if (flag2)
			p.right = node;
		else
			p.left = node;
		size++;
		return true;
	}

	// Remove the element with key k if it exists and return true. If the element
	// does not exist return false.
	public boolean remove(K k) {
		if (root == null)
			return false;

		// Search for k
		K k1 = k;
		BSTNode<K, T> p = root;
		BSTNode<K, T> q = null; // Parent of p
		while (p != null) {
			if (p.key.compareTo(k1) > 0) {
				q = p;
				p = p.left;
			} else if (k1.compareTo(p.key) > 0) {
				q = p;
				p = p.right;
			} else { // Found the key
						// Check the three cases
				if ((p.left != null) && (p.right != null)) {
					// Case 3: two children
					// Search for the min in the right subtree
					BSTNode<K, T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
					// Now fall back to either case 1 or 2
				}

				// The subtree rooted at p will change here
				if (p.left != null) { // One child
					p = p.left;
				} else { // One or no children
					p = p.right;
				}

				if (q == null) { // No parent for p, root must change
					root = p;
				} else {
					if (q.key.compareTo(k1) > 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				size--;
				return true;

			}
		}

		return false; // Not found
	}

	// Return the list of keys in increasing order.
	public List<K> getKeys() {
		List<K> keys = new LinkedList<K>();
		getKeysHelp(root, keys);
		return keys;
	}

	private void getKeysHelp(BSTNode<K, T> p, List<K> list) {
		if (p == null)
			return;
		getKeysHelp(p.left, list);
		list.insert(p.key);
		getKeysHelp(p.right, list);
	}
}
