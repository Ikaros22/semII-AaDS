package com.company;

import com.company.exceptions.*;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    void enqueue(T value) throws FullQueueException;
    T first() throws EmptyQueueException;
    T dequeue() throws EmptyQueueException;
    int size();
}
