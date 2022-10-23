package com.company;

import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;
import com.company.exceptions.FullStackException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Stack;

public class Inverter {
    @Contract("_ -> param1")
    public static <T> @NotNull IQueue<T> invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException {
        ArrayStack<T> stack = new ArrayStack<>(queue.size());
        while(!queue.isEmpty()){
            try {
                stack.push(queue.dequeue());
            } catch (FullStackException e) {
                e.printStackTrace();
            }
        }

        while (!stack.isEmpty()){
                queue.enqueue(stack.pop());
        }
        return queue;
    }
}

