package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> iterList;
    private int currentIndex;

    public BackwardIterator(Collection<T> collection) {
        this.iterList = List.copyOf(collection);
        this.currentIndex = iterList.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return iterList.get(currentIndex--);
    }
}
