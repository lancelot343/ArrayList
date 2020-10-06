package com.epam.rd.java.basic.practice2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    public static void main(String[] args) {
        Array arr = new ArrayImpl(3);
        arr.add(3);
        arr.add(1);
        arr.add(2);
        System.out.println(arr.indexOf(2));
    }

    Object[] values;
    int size = 0;

    public ArrayImpl() {
        values = new Object[0];
    }

    public ArrayImpl(int size) {
        if (size > 0) {
            values = new Object[0];
        }
    }

    @Override
    public void clear() {
        Object[] tmp = new Object[0];
        values = tmp;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return values[index++];
        }
    }

    @Override
    public void add(Object element) {
        Object[] tmp = values;
        values = new Object[tmp.length + 1];
        System.arraycopy(
                tmp, 0, // source
                values, 0, // destination
                tmp.length // amount
        );
        values[values.length - 1] = element;
        size++;
    }

    @Override
    public void set(int index, Object element) {
        values[index] = element;
    }

    @Override
    public Object get(int index) {
        return values[index];
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < this.size(); i++) {
            if (values[i] == null && element == null) {
                return i;
            }
            if (values[i] != null && values[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        Object[] tmp = values;
        values = new Object[tmp.length - 1];
        System.arraycopy(
                tmp, 0,
                values, 0,
                index
        );
        int amountElements = tmp.length - index - 1;
        System.arraycopy(
                tmp, index + 1,
                values, index,
                amountElements
        );
        size--;
    }

    @Override
    public String toString() {
        int index = 0;
        StringBuilder toReturn = new StringBuilder();
        if (this.size() >= 0) {
            toReturn.append("[");

            for (Object obj : values) {
                index++;
                if (index == this.size()) {
                    toReturn.append(obj);
                } else {
                    toReturn.append(obj);
                    toReturn.append(", ");
                }
            }
            toReturn.append("]");
        }
        return toReturn.toString();
    }
}