package com.mauriciotogneri.mandy.util;

public class IntStack
{
    private int[] array;
    public int length = 0;

    public IntStack(int initialCapacity)
    {
        this.array = new int[initialCapacity];
    }

    public void push(int element)
    {
        if (array.length == length)
        {
            int[] newArray = new int[length * 2];
            System.arraycopy(array, 0, newArray, 0, length);
            array = newArray;
        }

        array[length++] = element;
    }

    public int pop()
    {
        return array[--length];
    }

    public int peek()
    {
        return array[length - 1];
    }

    public boolean isEmpty()
    {
        return (length == 0);
    }

    public void clear()
    {
        length = 0;
    }
}