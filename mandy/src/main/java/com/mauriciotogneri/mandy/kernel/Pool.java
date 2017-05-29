package com.mauriciotogneri.mandy.kernel;

import java.util.ArrayList;
import java.util.List;

public class Pool<T>
{
    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize)
    {
        this.freeObjects = new ArrayList<>();
        this.factory = factory;
        this.maxSize = maxSize;
    }

    public Pool(final Class<T> clazz, int maxSize)
    {
        this.freeObjects = new ArrayList<>();
        this.factory = new PoolObjectFactory<T>()
        {
            @Override
            public T object()
            {
                try
                {
                    return clazz.newInstance();
                }
                catch (Exception e)
                {
                    throw new RuntimeException();
                }
            }
        };
        this.maxSize = maxSize;
    }

    public T newObject()
    {
        if (freeObjects.isEmpty())
        {
            return factory.object();
        }
        else
        {
            return freeObjects.remove(freeObjects.size() - 1);
        }
    }

    public void free(T object)
    {
        if (freeObjects.size() < maxSize)
        {
            freeObjects.add(object);
        }
    }

    public interface PoolObjectFactory<T>
    {
        T object();
    }
}