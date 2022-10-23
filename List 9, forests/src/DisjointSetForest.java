public class DisjointSetForest implements IDisjointSetStructure {
int[] rank, parent;
int size;

public DisjointSetForest(int size) {
		this.size = size;
		rank = new int[size];
		parent = new int[size];

		for (int i = 0; i < size; i++) {
				rank[i] = 0;
				parent[i] = i;
		}
}

@Override
public int findSet(int item) throws ItemOutOfRangeException {
		if (item < 0 || item >= parent.length)
				throw new ItemOutOfRangeException();

		return find(item);
}

private int find(int item){
	if (item == parent[item]) //x==parent x
		return item;
	else
		return find(parent[item]);
}

@Override
public void union(int item1, int item2) throws ItemOutOfRangeException {
		int head1 = findSet(item1);
		int head2 = findSet(item2);

		if (rank[head1] > rank[head2])
				parent[head2] = head1;
		else
				parent[head1] = head2;
		if (rank[head1] == rank[head2])
				rank[head2]++;

}
}
