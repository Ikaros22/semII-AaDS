import java.util.ArrayList;

public class DisjointSetLinkedList {
ArrayList<Node> items;

private class Node {
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
    this.items = new ArrayList<Node>(size);

    for (nItems = 0; nItems < size; nItems++) {
        Node node = new Node(nItems, null, 0);
        node.parent = node;
        items.add(node);
    }
}


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


public void union(int item1, int item2) throws ItemOutOfRangeException {
    int itemX = findSet(item1);
    int itemY = findSet(item2);

    Node nodeX = items.get(itemX);
    Node nodeY = items.get(itemY);

    if (nodeX.rank > nodeY.rank)
        nodeY.parent = items.get(itemX);

     else
        nodeX.parent = items.get(itemY);

    if (nodeX.rank == nodeY.rank)
        nodeY.rank++;
}
}


