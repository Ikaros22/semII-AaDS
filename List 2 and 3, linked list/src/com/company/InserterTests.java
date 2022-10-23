package com.company;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class InserterTests {
    @org.junit.jupiter.api.Test
    void insertList2BeforeList1ByIndex() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, 0);
        assertListContent(mergedList, new String[] {"a", "b", "c", "d", "e", "f"});
    }

    @org.junit.jupiter.api.Test
    void insertList2BeforeList1ByValue() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, "d");
        assertListContent(mergedList, new String[] {"a", "b", "c", "d", "e", "f"});
    }

    @org.junit.jupiter.api.Test
    void insertList2InMiddleOfList1ByIndex() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, 1);
        assertListContent(mergedList, new String[] {"d", "a", "b", "c", "e", "f"});
    }

    @org.junit.jupiter.api.Test
    void insertList2InMiddleOfList1ByValue() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        var mergedList = Inserter.insert(list1, list2, "e");
        assertListContent(mergedList, new String[] {"d", "a", "b", "c", "e", "f"});
    }

    @org.junit.jupiter.api.Test
    void insertBeforeNegativeIndex() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        assertThrows(NoSuchElementException.class, () -> {
            Inserter.insert(list1, list2, -2);
        });
    }

    @org.junit.jupiter.api.Test
    void insertBeforeTooBigIndex() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        assertThrows(NoSuchElementException.class, () -> {
            Inserter.insert(list1, list2, 5);
        });
    }

    @org.junit.jupiter.api.Test
    void insertBeforeInvalidValue() {
        var list1 = new TwoWayLinkedList<String>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        var list2 = new TwoWayLinkedList<String>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        assertThrows(NoSuchElementException.class, () -> {
            Inserter.insert(list1, list2, "g");
        });
    }

    private <T> void assertListContent(IList<T> list, T[] expectedContent) {
        var iterator = list.iterator();

        for (var i = 0; i < expectedContent.length; i++) {
            if (iterator.hasNext() == false) {
                fail("Didn't expect the list to end.");
            }

            assertEquals(expectedContent[i], iterator.next());
        }

        assertFalse(iterator.hasNext());
    }
}
