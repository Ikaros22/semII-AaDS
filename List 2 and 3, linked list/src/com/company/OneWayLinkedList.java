package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<T> implements IList<T> {
private  Node head;
private  Node tail;
private int size = 0;
private  OneWayLinkedListIterator iterator = new OneWayLinkedListIterator();

private class Node {
		public T data;
		public Node next;

		public Node(T data, Node next) {
				this.data = data;
				this.next = next;
		}
}


@Override
public void add(T value) {
		linkLast(value);
}

@Override
public void addAt(int index, T value) throws NoSuchElementException {
		ifValid(index);
		if (index == 0) {
				linkFirst(value);
		} else if (index == size) {
				linkLast(value);
		} else {
				linkBefore(value, index);
		}
}

@Override
public void clear() {
		for (Node node = head; node != null;) {
				Node next = node.next;
				node.data = null;
				node.next = null;
				node = next;
		}
		head = tail = null;
		size = 0;
}

@Override
public boolean contains(T value) {
		return findIndex(value) >= 0;
}

@Override
public T get(int index) throws NoSuchElementException {
		return findByIndex(index).data;
}

@Override
public void set(int index, T value) throws NoSuchElementException {
		findByIndex(index).data = value;
}

@Override
public int indexOf(T value) {
		return findIndex(value);
}

@Override
public boolean isEmpty() {
		return size == 0;
}

@Override
public T removeAt(int index) throws NoSuchElementException {
		ifValid(index);
		if (size == 1) {
				T temp = head.data;
				head = tail = null;
				return temp;
		}
		if (index == 0) {
				T temp = head.data;
				head = head.next;
				return temp;
		}
		if (size != 0) {
				Node toRemove = findByIndex(index);
				findByIndex(index - 1).next = toRemove.next;
				return toRemove.data;
		}
		return null;
}

@Override
public boolean remove(T value) {
		if (size != 0) {
//				int index = findIndex(value);
//
//				if (index < 0)
//						return false;
//
//				if (index == 0) {
//						head = head.next;
//						return true;
//				}
//				Node toRemove = findByIndex(index);
//				findByIndex(index - 1).next = toRemove.next;
				if(contains(value)) {
						removeAt(findIndex(value));
						return true;
				}
		}
		return false;
}

@Override
public int size() {
		return size;
}

@Override
public void print() {
		if(size==0){
				throw new Error("No data found, status 404");
		}
		if(size>0){
				System.out.print("[ ");
				for (Node current = head; current != null; current = current.next) {
						System.out.print(current.data+" ");
				}
				System.out.print("]");
		}
}

private void linkLast(T value) {
		Node last = tail;
		Node newNode = new Node(value, null);
		tail = newNode;

		if (last == null)
				head = newNode;
		else
				last.next = newNode;
		size++;
}

private void linkFirst(T value) {
		head = new Node(value, head);
		size++;
}

private void linkBefore(T value, int index) {
		Node temp = findByIndex(index - 1);
		temp.next = new Node(value, temp.next);
		size++;
}

private void ifValid(int index) {
		if (index < 0 || index>size)
				throw new NoSuchElementException();
}

private Node findByIndex(int index) {
		ifValid(index);
		Node node = head;

		for (int current = 0; current < index; current++)
				node = node.next;
		return node;
}

private int findIndex(T value) {
		int i = 0;

		for (Node node = head; node != null; node = node.next) {
				if (value.equals(node.data))
						return i;
				i++;
		}
		return -1;
}

@Override
public Iterator<T> iterator() {
		return new OneWayLinkedListIterator();
}

public T getLastReturned(){
		if(iterator.hasNext())
				return iterator.next();

		return null;
}

public boolean ifDone(){
		return iterator.hasNext();
}

public T value(){
		if(iterator.next!=null) {
				return iterator.next.data;
		}
		return null;
}

private class OneWayLinkedListIterator implements Iterator<T> {
		private Node lastReturned;
		private Node next;
		private int nextIndex = 0;

		public OneWayLinkedListIterator() {
				next = head;
		}

		@Override
		public boolean hasNext() {
				return nextIndex < size();
		}

		@Override
		public T next() {
				if (!hasNext())
						throw new NoSuchElementException();

				if(next==null){
						next = head;
						lastReturned =head;
						return lastReturned.data;
				}

				lastReturned = next;
				next = next.next;
				nextIndex++;

				return lastReturned.data;
		}

}
}
