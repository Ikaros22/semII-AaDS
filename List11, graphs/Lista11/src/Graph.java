import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Graph<T> {
    HashMap<T, Integer> vertices;
    int[][] matrix;

    private HashMap<T, Integer> result;
    private HashMap<T, Boolean> visited;

    public Graph(List<Edge<T>> edges) {
        this.vertices = new HashMap<>();
        AtomicInteger element = new AtomicInteger(0);
        AddNode<T> addNode = ((node, elem) -> {
            vertices.put(node, elem);
            element.set(vertices.size());
        });

        for (Edge<T> edge : edges) {
            if (!vertices.containsKey(edge.getNode1())) addNode.add(edge.getNode1(), element.get());
            if (!vertices.containsKey(edge.getNode2())) addNode.add(edge.getNode2(), element.get());
        }

        int size = vertices.size();
        matrix = new int[size][size];

        for (int row = 0; row < size; row++)
            Arrays.fill(matrix[row], Integer.MAX_VALUE);

        FillMatrix fillMatrix = (((indexX, indexY, edgeValue) -> matrix[indexX][indexY] = edgeValue));

        for (Edge<T> edge : edges) {
            int indexY = vertices.get(edge.getNode1());
            int indexX = vertices.get(edge.getNode2());

            fillMatrix.fill(indexX, indexY, edge.getDistance());
            fillMatrix.fill(indexY, indexX, edge.getDistance());
        }

    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        if (!vertices.containsKey(startNode))
            throw new NoSuchElementException();

        result = new HashMap<>();
        visited = new HashMap<>();
        int startIndex = vertices.get(startNode);

        for (T node : vertices.keySet()) {
            if (!node.equals(startNode)) {
                visited.put(node, false);
                result.put(node, matrix[startIndex][vertices.get(node)]);
            }
        }

        while (visited.containsValue(false)) {
            T nearestVertex = getNearestUnvisited();
            visited.put(nearestVertex, true);

            search(startNode, nearestVertex);
        }
        return result;
    }

    private T getNearestUnvisited() {
        T unvisited = null;
        int minDistance = Integer.MAX_VALUE;
        for (T node : visited.keySet()) {
            if (!visited.get(node) && result.get(node) < minDistance) {
                minDistance = result.get(node);
                unvisited = node;
            }
        }
        return unvisited;
    }
public Integer calculateShortestPath(T startNode, T endNode) throws NoSuchElementException {
    if(!vertices.containsKey(startNode) || !vertices.containsKey(endNode))
        throw new NoSuchElementException();

    result = new HashMap<>();
    visited = new HashMap<>();
    int startIndex = vertices.get(startNode);

    for(T node : vertices.keySet()){
        if(!node.equals(startNode)){
            visited.put(node, false);
            result.put(node, matrix[startIndex][vertices.get(node)]);
        }
    }

    boolean found = false;
    while(visited.containsValue(false) && !found){
        T nearestVertex = getNearestUnvisited();
        found = endNode.equals(nearestVertex);
        search(startNode, nearestVertex);
    }
    return result.get(endNode);
}

private void search(T startNode, T nearestVertex){

    visited.put(nearestVertex, true);

    for(T key : vertices.keySet()){
        if(!key.equals(startNode) && !visited.get(key)){
            int distance =matrix[vertices.get(nearestVertex)][vertices.get(key)];
            if(distance != Integer.MAX_VALUE)
                result.put(key, Math.min(result.get(nearestVertex) + distance, result.get(key)));
        }
    }
}


    interface AddNode<T> {
        void add(T node, int element);
    }

    interface FillMatrix {
        void fill(int indexX, int indexY, int edgeValue);
    }
}
