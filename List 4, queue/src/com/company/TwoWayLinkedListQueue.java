package com.company;

import com.company.exceptions.*;

import java.util.LinkedList;
import java.util.Queue;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {
    LinkedList<T> queue;
    int capacity;

    public TwoWayLinkedListQueue(int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return queue.size()==0;
    }

    @Override
    public boolean isFull() {
        return queue.size()==capacity;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        if(queue.size()<capacity)
            queue.add(value);
        else
            throw new FullQueueException();
    }

    @Override
    public T first() throws EmptyQueueException {
        if(!isEmpty())
            return queue.getFirst();
        else
            throw new EmptyQueueException();
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(!isEmpty()) {
            T tempData = queue.getFirst();
            queue.removeFirst();
            return tempData;
        } else {
            throw new EmptyQueueException();
        }
    }

    @Override
    public int size() {
        return queue.size();
    }

    private void checkIfEmpty() throws EmptyQueueException {
        if(queue.size()==0)
            throw new EmptyQueueException();
    }
}
