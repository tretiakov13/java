package ua.khpi.oop.tretiakov13;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class LinkedContainer<E> implements Linked<E>, Iterable<E>, Serializable{
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public LinkedContainer() {
        last = new Node<E>(first,null, null);
        first = new Node<E>(null, null, last);
    }

    @Override
    public void addLast(E e) {
        Node<E> prev = last;
        prev.setItem(e);
        last = new Node<E>(prev,null, null);
        prev.setNext(last);
        size++;
    }

    @Override
    public void addFirst(E e) {
        Node<E> next = first;
        next.setItem(e);
        first = new Node<E>(null, null, next);
        next.setPrev(first);
        size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E getElementByIndex(int index) {
        Node<E> target = first.getNext();
        for (int i = 0; i < index; i++) {
            if (target == null) return null;
            target = target.getNext();
        }
        return target.getItem();
    }

    @Override
    public void removeByIndex(int index) {
        Node<E> target = first.getNext();
        for (int i = 0; i < index; i++) {
            if (target == null) return;
            target = target.getNext();
        }
        Node<E> PrevRemoved = target.prev;
        Node<E> NextRemoved = target.next;
        PrevRemoved.next = NextRemoved;
        NextRemoved.prev = PrevRemoved;
        target.setItem(null);
        target.setPrev(null);
        target.setNext(null);
        size--;
    }

    @Override
    public void clean() {
        Node<E> target = first.getNext();
        for (int i = 0; i < size; i++) {
            target.setItem(null);
            target = target.getNext();
        }
        last = new Node<E>(first,null, null);
        first = new Node<E>(null, null, last);
        size = 0;
    }



    ArrayList<E> toArray(){
        ArrayList<E> result = new ArrayList<E>();

        Node<E> target = first.getNext();
        for (int i = 0; i < size; i++) {
            result.add(target.getItem());
            target = target.getNext();
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> target = first.getNext();
        for (int i = 0; i < size; i++) {
            builder.append(target.item.toString());
            target = target.getNext();
        }
        return builder.toString();
    }

    boolean isEmpry(){
        if(first.next == last){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getElementByIndex(counter++);
            }
        };
        return iterator;
    }

    private static class Node<E> implements Serializable {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }
}
