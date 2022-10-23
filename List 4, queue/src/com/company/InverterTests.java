package com.company;

import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InverterTests {
    @org.junit.jupiter.api.Test
    void emptyQueue() throws EmptyQueueException, FullQueueException {
        var queue = createQueue(1, 0);
        var invertedQueue = Inverter.invert(queue);

        assertEquals(true, invertedQueue.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void oneElementQueue() throws EmptyQueueException, FullQueueException {
        var queue = createQueue(1, 1);
        var invertedQueue = Inverter.invert(queue);

        assertEquals(1, invertedQueue.size());
        assertEquals(1, invertedQueue.dequeue());
    }

    @org.junit.jupiter.api.Test
    void threeElementQueue() throws EmptyQueueException, FullQueueException {
        var queue = createQueue(3, 3);
        var invertedQueue = Inverter.invert(queue);

        assertEquals(3, invertedQueue.size());
        assertEquals(3, invertedQueue.dequeue());
        assertEquals(2, invertedQueue.dequeue());
        assertEquals(1, invertedQueue.dequeue());
    }

    private IQueue<Integer> createQueue(int capacity, int elements) {
        var queue = new TwoWayLinkedListQueue<Integer>(capacity);

        try {
            for (var i = 1; i <= elements; i++) {
                queue.enqueue(i);
            }
        } catch (Exception exception) {
            fail(exception);
        }

        return queue;
    }
}
