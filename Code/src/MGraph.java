
public class MGraph<K extends Comparable<K>> implements Graph<K> {
	public Map<K, List<K>> adj; 

	public MGraph() {
		adj = new BSTMap<K, List<K>>();
	}

	// Add a node to the graph if it does not exist and return true. If the node
	// already exists, return false.
	public boolean addNode(K i) {
		if (isNode(i))
			return false;
		adj.insert(i, new LinkedList<K>());
		return true;
	}

	// Check if i is a node
	public boolean isNode(K i) {
		return adj.retrieve(i).first;
	}

	// Add an edge to the graph if it does not exist and return true. If i or j do
	// not exist or the edge (i, j) already exists, return false.
	public boolean addEdge(K i, K j) {
		if (!isNode(i) || !isNode(j) || isEdge(i,j))
			return false;
		adj.retrieve(i).second.insert(j);
		adj.retrieve(j).second.insert(i);
		return true;
	}

	// Check if (i, j) is an edge.
	public boolean isEdge(K i, K j) {
		if (!isNode(i) || !isNode(j))
			return false;
		return adj.retrieve(i).second.exists(j);
	}

	// Return the set of neighbors of node i. If i does not exist, the method
	// returns null.
	public List<K> neighb(K i) {
		if (!isNode(i))
			return null;
		return adj.retrieve(i).second;
	}

	// Return the degree (the number of neighbors) of node i. If i does not exist,
	// the method returns -1.
	public int deg(K i) {
		if (!isNode(i))
			return -1;
		return adj.retrieve(i).second.size();
	}

	// Return a list containing the nodes in increasing order.
	public List<K> getNodes() {
		return adj.getKeys();
	}
}
