package com.company;

import com.company.exceptions.*;

import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T>{
    T[] stack;
    int capacity;
    int roof;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        stack = (T[]) new Object[capacity];
        this.capacity=capacity;
        roof = -1;
    }

    @Override
    public boolean isEmpty() {
        return roof==-1;
    }

    @Override
    public boolean isFull() {
        return roof+1==capacity;
    }

    @Override
    public T top() throws EmptyStackException {
        if(roof>=0)
        return stack[roof];

        throw new EmptyStackException();
    }

    @Override
    public T pop() throws EmptyStackException {
        if (roof+1 <= 0)
            throw new EmptyStackException();

        T topElem = stack[roof];
        stack[roof] = null;
        roof--;
        return topElem;
    }

    @Override
    public void push(T value) throws FullStackException {
        if (roof+1 < capacity) {
            roof++;
            stack[roof] = value;
        } else {
            throw new FullStackException();
        }
    }

    @Override
    public int size() {
        return roof+1;
    }
}
