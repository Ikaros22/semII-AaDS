import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Graph<T> {
ArrayList<ArrayList<Integer>> matrix;
ArrayList<String> elements;
private final int ANTYLOOP;

private int HOW_MANY;
int size;

public Graph(List<Edge<T>> edges) {
		//matrix
		HOW_MANY = -1;
		matrix = new ArrayList<>();
		elements = new ArrayList<>();
		ANTYLOOP = edges.size();
		size = edges.size();

		for (Edge edge : edges) {
				addElement((String) edge.getSource());
				addElement((String) edge.getDestination());

				int y = elements.indexOf((String) edge.getSource());
				int x = elements.indexOf((String) edge.getDestination());

				matrix.get(y).set(x, edge.getWeight());
		}
}

private void addElement(String element) {
		if (!elements.contains(element)) {
				elements.add(element);
				ArrayList<Integer> temp = new ArrayList();
				init(temp);
				matrix.add(temp);
		}
}

public String depthFirst(T startNode) throws NoSuchElementException {
		if (!elements.contains((String) startNode))
				throw new NoSuchElementException();

		ArrayList<String> result = new ArrayList<>();
		return String.join(", ", search(result, (String) startNode, 0));
}

private ArrayList<String> search(ArrayList list, String startNode, int nModified) {
		if (nModified > ANTYLOOP) //anty loop shield
				return list;

		if (!list.contains(startNode))
				list.add(startNode);

		int position = 0;
		for (Integer element : matrix.get(elements.indexOf(startNode))) {
				if (element != 0) {
						nModified++;
						search(list, elements.get(position), nModified);
				}
				position++;
		}
		return list;

}

private ArrayList<String> searchD(ArrayList list, String startNode, int nModified) {
		if (nModified > ANTYLOOP) //anty loop shield
				return list;

		int position = 0;
		for (Integer element : matrix.get(elements.indexOf(startNode))) {
				if (element != 0) {
						if (!list.contains(elements.get(position)))
								list.add(elements.get(position));
				}
				position++;
		}

		position = 0;
		for (Integer element : matrix.get(elements.indexOf(startNode))) {
				if (element != 0) {
						nModified++;
						searchD(list, elements.get(position), nModified);
				}
				position++;
		}
		return list;

}

public String breadthFirst(T startNode) throws NoSuchElementException {
		if (!elements.contains((String) startNode))
				throw new NoSuchElementException();

		ArrayList<String> result = new ArrayList<>();
		result.add((String) startNode);
		return String.join(", ", searchD(result, (String) startNode, 0));
}

public int connectedComponents() throws ItemOutOfRangeException {
		if(HOW_MANY!=-1) {
			return HOW_MANY;
		}

		DisjointSetLinkedList forest = new DisjointSetLinkedList(size);
		for (ArrayList<Integer> list : matrix) {
				int row = matrix.indexOf(list);
				for (int element = 0; element < elements.size(); element++) {
						if (list.get(element) != 0) {
								if (forest.findSet(row) != forest.findSet(element))
										forest.union(row, element);
						}
				}
		}
		ArrayList<Integer> graphToken = new ArrayList<>();

		for (int i = 0; i < size; i++) {
				int token = forest.findSet(i);

				if (!graphToken.contains(token) && token< elements.size())
						graphToken.add(token);
		}

		return HOW_MANY = graphToken.size();
}

private void init(ArrayList<Integer> temp){
		for (int tempy = 0; tempy < ANTYLOOP; tempy++)
				temp.add(0);
}
}
