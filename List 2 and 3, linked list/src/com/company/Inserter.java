package com.company;

import java.util.Iterator;

public class Inserter {
    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            String beforeIndex) {
		return insert(list1, list2, list1.indexOf(beforeIndex));
    }

	public static TwoWayLinkedList<String> insert(
			TwoWayLinkedList<String> list1,
			TwoWayLinkedList<String> list2,
			int index) {
		Iterator<String> iter2 = list2.iterator();

		while(iter2.hasNext()) {
			list1.addAt(index, iter2.next());
			index++;
		}

		return list1;
	}

public static void main(String[] args) {
		TwoWayLinkedList one = new TwoWayLinkedList();
		one.add("d");
		one.add("e");
		one.add("f");
		TwoWayLinkedList two = new TwoWayLinkedList();
		two.add("a");
		two.add("b");
		two.add("c");
		//insertByIndex(one, two, 1).print();
		insert(one, two, 1).print();
}
}
