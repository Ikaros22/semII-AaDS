import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    class Node {
        Node left;
        Node right;
        T data;

        public Node(Node left, T data, Node right) {
            this.left = left;
            this.data = data;
            this.right = right;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    int size = 0;
    Node head;

    public void add(T value) throws DuplicateElementException {
        head = addIfNotZero(value, head);
        size++;
    }

    public boolean contains(T value) {
        return search(value, head);
    }

    private boolean search(T value, Node node){
        if(node ==null)
            return false;

        if(node.data.compareTo(value)==0)
            return true;

        if(node.data.compareTo(value)<0)
            return search(value, node.right);

        if(node.data.compareTo(value)>0)
            return search(value,node.left);

        return false;
    }

    public void delete(T value) {
        head = deleteCentral(head, value);
    }

    private Node deleteCentral(Node node, T value) {
        if (node == null)
            return null;

        if(node.data.compareTo(value) < 0)
            node.right = deleteCentral(node.right, value);

        else if(node.data.compareTo(value) > 0)
            node.left = deleteCentral(node.left, value);

        else
            if(node.left == null && node.right == null)
                node = null; //empty

            else if(node.right != null){
                node.data = previousValue(node);
                node.right = deleteCentral(node.right, node.data);
            }else{
                node.data = nextValue(node);
                node.left = deleteCentral(node.left, node.data);
            }

        return node;
    }
    private T previousValue(Node node){
        node = node.right;
        while(node.left != null){
            node = node.left;
        }
        return node.data;
    }

    private T nextValue(Node node){
        node = node.left;
        while(node.right != null){
            node = node.right;
        }
        return node.data;
    }

    public String toStringPreOrder() {
        ArrayList<String> result = new ArrayList<>();
        preOrder(head, result);

        return String.join(", ", result);
    }

    public List sort(){
        List<String> result = new LinkedList<>();
        inOrder(head, result);
        return result;
    }

    public String toStringInOrder() {
        ArrayList<String> result = new ArrayList<>();
        inOrder(head, result);

        return String.join(", ", result);
    }

    public String toStringPostOrder() {
        ArrayList<String> result = new ArrayList<>();
        postOrder(head, result);

        return String.join(", ", result);
    }

    private void postOrder(Node node, ArrayList<String> result){
        if (node == null) {
            return;
        }
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.data.toString());
    }

    private void inOrder(Node node, List<String> result){
        if (node == null) {
            return;
        }
        inOrder(node.left, result);
        result.add(node.data.toString());
        inOrder(node.right, result);
    }

    private void preOrder(Node node, ArrayList<String> result) {
        if (node == null) {
            return;
        }
        result.add(node.data.toString());
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    private Node addIfNotZero(T data, Node comparer) throws DuplicateElementException {
        if (comparer == null)
            return new Node(null, data, null);


        if (comparer.data.compareTo(data) == 0)
            throw new DuplicateElementException();

        if (comparer.data.compareTo(data) > 0)
            comparer.left = addIfNotZero(data, comparer.left);

        else if (comparer.data.compareTo(data) < 0)
            comparer.right = addIfNotZero(data, comparer.right);

        else
            return comparer;

        return comparer;
    }
}