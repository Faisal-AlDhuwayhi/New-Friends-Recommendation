import java.io.File;
import java.util.Scanner;

public class Recommender {
	// Return the top k recommended friends for use
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendPop(Graph<K> g, K i, int k) {
		if (!g.isNode(i))
			return null;
		PQKImp<Double, K> q = new PQKImp<Double, K>(k);
		List<K> list = g.getNodes();
		list.findFirst();
		for (int j = 0; j < list.size(); j++) {
			if (g.isEdge(list.retrieve(), i) || list.retrieve().equals(i)) {
				list.findNext();
				continue;
			}
			q.enqueue((double) g.deg(list.retrieve()), list.retrieve());
			list.findNext();
		}
		return q;
	}

	// Return the top k recommended friends for user i using common neighbors
	// method. If i does not exist, return null. In case of a tie, users with the
	// lowest id are selected.
	public static <K extends Comparable<K>> PQK<Double, K> recommendCN(Graph<K> g, K i, int k) {
		if (!g.isNode(i))
			return null;
		PQKImp<Double, K> q = new PQKImp<Double, K>(k);
		double com = 0.0;
		List<K> list = g.getNodes();
		list.findFirst();
		List<K> listN = g.neighb(i);
		for (int j = 0; j < list.size(); j++) {
			if (g.isEdge(list.retrieve(), i) || list.retrieve().equals(i)) {
				list.findNext();
				continue;
			}
			listN.findFirst();
			while (true) {
				K key = listN.retrieve();
				if (g.isEdge(list.retrieve(), key))
					com++;
				if(listN.last())
					break;
				listN.findNext();
			}
			q.enqueue(com, list.retrieve());
			list.findNext();
			com =0.0;
		}
		return q;
	}

	// Read graph from file. The file is a text file where each line contains an
	// edge. The end and start of the edge are separated by space(s) or tabs.
	public static Graph<Integer> read(String fileName) {

		try {
			Graph<Integer> g = new MGraph<Integer>();
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNextInt()) {
				int i = scanner.nextInt();
				g.addNode(i);
				int j = scanner.nextInt();
				g.addNode(j);
				g.addEdge(i, j);
			}
			scanner.close();
			return g;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
