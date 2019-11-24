package ua.edu.ucu.utils;

import java.util.Iterator;

public class Queue {
    private ImmutableLinkedList lst;

    public Queue() {
        this.lst = new ImmutableLinkedList();
    }

    public Object peek() {
        return lst.getFirst();
    }

    public Object dequeue() {
        Object res = lst.getFirst();
        lst = lst.removeFirst();
        return res;
    }

    public void enqueue(Object e) {
        lst = lst.addLast(e);
    }

    public Iterable<String> iterable() {
        return () -> new Iterator<String>() {
            private int currentIndex;

            @Override
            public boolean hasNext() {
                return currentIndex < lst.size();
            }

            @Override
            public String next() {
                currentIndex++;
                return (String) lst.get(currentIndex - 1);
            }
        };
    }
}

