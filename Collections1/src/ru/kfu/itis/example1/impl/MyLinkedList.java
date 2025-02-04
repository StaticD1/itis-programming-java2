package ru.kfu.itis.example1.impl;

import ru.kfu.itis.example1.base.MyList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<T> implements MyList<T> {
    static class MyLinkedListIterator<T> implements Iterator<T> {
        private Node current;

        public MyLinkedListIterator(Node first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return (T) current.value;
        }
    }

    private static class Node {
        Node next;
        Object value;

        public Node(Object value) {
            this.value = value;
            this.next = null;
        }
    }

    Node first;
    Node last;
    int size = 0;

    @Override
    public void add(T element) {
        if(first == null) {
            first = new Node(element);
            last = first;
        } else {
            last.next = new Node(element);
            last = last.next;
        }
        size++;
    }

	    public void remove() {
        Node current = first;
        first = current.next;
        size--;
    }

    public void remove(int index) {
        if (first == null) {
            return;
        }
        if (first.value == get(index)) {
            first = first.next;
            --size;
            return;
        }
        Node<T> current = first;
        while(current.next != null) {
            if (current.next.value == get(index)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }
    public void remove(T value) {
        if(first == null)
            return;
        if (first == last) {
            first = null;
            last = null;
            size--;
            return;
        }
        if (first.value == value) {
            first = first.next;
            size--;
            return;
        }
        Node current = first;
        while (current.next != null) {
            if (current.next.value == value) {
                if(last == current.next) {
                    last = current;
                }
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }

    @Override
    public T get(int index) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T) current.value;
    }

    @Override
    public int size() {
        return size;
    }

    // Iterable

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<T>(first);
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
