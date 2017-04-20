package ru.otus.lesson3;

import java.util.*;
import java.util.function.Consumer;

public class NonNullArrayList<E> implements List<E> {
    Object[] items;

    private int size;

    public NonNullArrayList() {
        this.items = new Object[10];
    }

    public NonNullArrayList(int initialCapacity) {
        this.items = new Object[initialCapacity];
    }

    private NonNullArrayList(Object[] items) {
        this.items = items;
        size = items.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return o != null && indexOf(o) > 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIteratorImpl();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (int i = 0; i < size; i++) {
            action.accept(getItems()[i]);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(items, 0, arr, 0, size);
        return arr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E element) {
        Objects.requireNonNull(element);
        checkCapacity(size + 1);
        items[size++] = element;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return remove(indexOf(o)) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.contains(null))
            throw new NullPointerException();
        checkCapacity(size + c.size());
        Object[] arr = c.toArray();
        int length = arr.length;
        System.arraycopy(arr, 0, items, size, length);
        size = size + c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.contains(null))
            throw new NullPointerException();
        checkCapacity(size + c.size());
        Object[] arr = c.toArray();
        int length = arr.length;
        int toMove = size - index;
        if (toMove > 0)
            System.arraycopy(items, index, items, index + length, toMove);

        System.arraycopy(arr, 0, items, index, length);
        size = size + length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<E> iterator = listIterator();
        while (iterator.hasNext()) {
            if (c.contains(iterator.next()))
                iterator.remove();
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort(getItems(), Comparator.nullsLast(c));
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        return getItems()[index];
    }

    @Override
    public E set(int index, E element) {
        Objects.requireNonNull(element);
        return getItems()[index] = element;
    }

    @Override
    public void add(int index, E element) {
        checkCapacity(size + 1);
        Objects.requireNonNull(element);
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        E element = get(index);
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o))
                lastIndex = i;
        }
        return lastIndex;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        int subListSize = toIndex - fromIndex + 1;
        Object[] subArr = new Object[subListSize];
        System.arraycopy(getItems(), fromIndex, subArr, 0, subListSize);
        return new NonNullArrayList<>(subArr);
    }

    @SuppressWarnings("unchecked")
    private E[] getItems() {
        return (E[]) items;
    }

    private void checkCapacity(int needCapacity) {
        if (needCapacity - items.length > 0) {
            items = Arrays.copyOf(items, Math.round(needCapacity * 1.2f) + 1);
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }


    public class ListIteratorImpl implements ListIterator<E>  {
        private int index = -1;

        @Override
        public boolean hasNext() {
            return (index + 1) < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return getItems()[++index];
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
                throw new NoSuchElementException();
            return getItems()[--index];
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return Math.max(index - 1, -1);
        }

        @Override
        public void remove() {
            if (index == -1)
                throw new NoSuchElementException();
            NonNullArrayList.this.remove(index--);
        }

        @Override
        public void set(E e) {
            if(index == -1)
                throw new NoSuchElementException();
            NonNullArrayList.this.set(index, e);
        }

        @Override
        public void add(E e) {
            if(index == -1)
                throw new NoSuchElementException();
            checkCapacity(size + 1);
            NonNullArrayList.this.add(e);
        }
    }

}
