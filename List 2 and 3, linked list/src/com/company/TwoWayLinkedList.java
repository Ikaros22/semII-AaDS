package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> implements IList<T> {
private Node head;
private Node tail;
private int size = 0;

private class Node {
    public T data;
    public Node prev;
    public Node next;

    public Node(Node prev, T data, Node next) {
        this.prev = prev;
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
//        for (Node node = head; node != null; ) {
//            Node next = node.next;
//            node.prev = null;
//            node.data = null;
//            node.next = null;
//            node = next;
//        }
        head = tail = null;
        //System.gc(); //does it make any sens
        size = 0;
    }

    @Override
    public boolean contains(T value) {
        return findIndex(value)>=0;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getByIndex(index).data;
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        getByIndex(index).data = value;
    }

    @Override
    public int indexOf(T value) {
        return findIndex(value);
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        ifValid(index);
        if (size == 1) {
            T temp = head.data;
            head = tail = null;
            size--;
            return temp;
        }
        if (index == 0) {
            T temp = head.data;
            head = head.next;
            head.prev=null;
            size--;
            return temp;
        }
        if (size != 0) {
            Node toKick = getByIndex(index);
            toKick.next.prev = toKick.prev;
            toKick.prev.next = toKick.next;
            size--;
            return toKick.data;
        }
        return null;
    }

    @Override
    public boolean remove(T value) throws NoSuchElementException{

    if(contains(value))
        return null != removeAt(findIndex(value));

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
            for (Node current= head; current != null; current = current.next) {
                System.out.print(current.data+" ");
            }
            System.out.print("]");
        }
    }

    public void deleteCopies(){
        for(Node node=head.next; node!=null;) {
            if (node.prev.data.equals(node.data))
                remove(node.prev.data);
            node=node.next;
        }

    }

private void linkLast(T value) {
    Node last = tail;
    Node newNode = new Node(last,value, null);
    tail = newNode;

    if (last == null)
        head = newNode;
    else
        last.next = newNode;

    size++;
}

private void linkFirst(T value){
    Node headTemp = head;
    head = new Node(null,value, headTemp);
    headTemp.prev=head;

    size++;
}

private void linkBefore(T value, int index){
    Node temp = getByIndex(index - 1);
    Node toAdd = new Node(temp,value, temp.next);
    temp.next.prev=toAdd;
    temp.next = toAdd;

    size++;
}

private void ifValid(int index){
    if(index<0 || index>size){
        throw new NoSuchElementException();
    }
}

private Node getByIndex(int index){
    ifValid(index);
    Node node;

    if(index==0)
        return getFirst();

    if(index==size)
        return getLast();

    if(index-1<(size>>1)){
        node = head;
        for(int current = 0; current < index; current++)
            node=node.next;
    } else {
        node = tail;
        for(int current = size; current-1 > index; current--)
            node=node.prev;
    }
return node;
}

private Node getFirst(){
    if(size>0){
        return head;
    }
    return null;
}

private Node getLast(){
    if(size>0){
        return tail;
    }
    return null;
}

private int findIndex(T value) {
    int index = 0;

    for (Node node = head; node != null; node = node.next) {
        if (value.equals(node.data))
            return index;
        index++;
    }
    return -1;
}

    @Override
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        public Node lastReturned;
        private Node next;
        private int nextIndex;

       public TwoWayLinkedListIterator() {
            next=head;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.data;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? tail : next.prev;
            nextIndex--;
            return lastReturned.data;
        }
    }
}
