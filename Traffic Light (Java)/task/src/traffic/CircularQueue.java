package traffic;

import java.util.*;

public class CircularQueue<E> extends LinkedList<E> {
    private final int maxSize;
    private int currentSize = 0;
    private int head = 0;

    public CircularQueue(int maxSize) {
        this.maxSize = maxSize;
    }
    @Override
    public boolean add(E e) {
        if (size() == maxSize) {
            return false;
        }
        currentSize++;
        head %= currentSize;
        return super.add(e);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        currentSize--;
        if (head == currentSize) head = 0;
        if (head > 0 ) --head;
        return super.removeFirst();
    }

    public boolean enqueue(E element) {
        if (size() == maxSize) {
            return false;
        }
        currentSize++;
        if (head == 0) {
            addLast(element);
        } else {
            add(head, element);
            head = (head + 1) % currentSize;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        currentSize += c.size();
        return super.addAll(c);
    }
    public E dequeue () {
        if (isEmpty()) {
            return null;
        }
        E element = remove(head);
        currentSize--;
        if (currentSize == 0) {
            head = 0;
        } else {
            head %= currentSize;
        }
        return element;
    }

    public E getNext() {
        if (isEmpty()) {
            return null;
        }
        head = (head + 1) % currentSize;
        return get(head);
    }

    public E peekCurrent() {
        if (isEmpty()) {
            return null;
        }
        return get(head);
    }

    public List<E> getAllElements() {
        List<E> list = new ArrayList<>();
        Iterator<E> iterator = this.iterator();
        iterator.forEachRemaining(list::add);
        return list;
    }
}