package com.mauriciotogneri.mandy.util;

import java.util.Arrays;

public class Array<T>
{
    private Object[] array;
    private final IntStack emptyPlaces;
    private int length = 0;

    public Array(int initialCapacity)
    {
        this.array = new Object[initialCapacity];
        this.emptyPlaces = new IntStack(initialCapacity / 2);
    }

    @SuppressWarnings("unchecked")
    public T get(int index)
    {
        return (T) array[index];
    }

    public void add(T element)
    {
        if (array.length == length)
        {
            Object[] newArray = new Object[length * 2];
            System.arraycopy(array, 0, newArray, 0, length);
            array = newArray;
        }
        else if (!emptyPlaces.isEmpty())
        {
            array[emptyPlaces.pop()] = element;

            return;
        }

        array[length++] = element;
    }

    public void remove(int index)
    {
        array[index] = null;

        emptyPlaces.push(index);
    }

    public int length()
    {
        return length;
    }

    public void clear()
    {
        if (length != 0)
        {
            Arrays.fill(array, 0, length, null);
            length = 0;
        }

        emptyPlaces.clear();
    }
}