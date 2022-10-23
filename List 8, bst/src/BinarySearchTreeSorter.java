import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        BinarySearchTree tree = new BinarySearchTree();
        for (T element: list) {
            tree.add(element);
        }

        list.clear();
        list.addAll(tree.sort());
//        List temp = tree.sort();
//
//        for(int j= 0; j<temp.size(); j++){
//            list.add((T) temp.get(j));
//        }
    }
}
