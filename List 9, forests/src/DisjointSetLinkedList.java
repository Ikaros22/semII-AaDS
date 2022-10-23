import java.util.ArrayList;

public class DisjointSetLinkedList implements IDisjointSetStructure {
ArrayList<Node> items;
private static class Node {
    int rank;
    Node parent;
    int data;

    Node(int element, Node parent, int rank) {
        this.parent = parent;
        this.rank = rank;
        this.data = element;
    }
}
int nItems;
public DisjointSetLinkedList(int size) {
    this.items = new ArrayList<>(size);

    for (nItems = 0; nItems < size; nItems++) {
        Node node = new Node(nItems, null, 0);
        node.parent = node;
        items.add(node);
    }
}

@Override
public int findSet(int item) throws ItemOutOfRangeException {
    if (item < 0 || item >= items.size())
        throw new ItemOutOfRangeException();

    return find(items.get(item)).data;
}

private Node find(Node node) {
    if (node == null)
        return null;

    if (node != node.parent)
        node.parent = find(node.parent);

    return node.parent;
}

@Override
public void union(int item1, int item2) throws ItemOutOfRangeException {
    Node node1 = items.get(findSet(item1));
    Node node2 = items.get(findSet(item2));

    if (node1.rank > node2.rank)
        node2.parent = node1;
     else
        node1.parent = node2;

    if (node1.rank == node2.rank)
        node2.rank++;
}
}


